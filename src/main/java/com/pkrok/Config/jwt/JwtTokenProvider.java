package com.pkrok.Config.jwt;

import com.pkrok.Config.SecurityConstants;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    public String generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();
        Date validity = new Date(now.getTime() + SecurityConstants.ACCESS_TOKEN_VALIDITY_SECONDS);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(SecurityConstants.AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.SIGNIN_KEY)
                .setIssuedAt(now)
                .setExpiration(validity)
                .compact();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token, Authentication existngAuth, UserDetails userDetails) {
        JwtParser jwtParser = Jwts.parser().setSigningKey(SecurityConstants.SIGNIN_KEY);
        Jws<Claims> claimsJwt = jwtParser.parseClaimsJws(token);
        Claims claims = claimsJwt.getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(SecurityConstants.AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);

    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SecurityConstants.SIGNIN_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
}
