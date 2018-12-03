package com.pkrok.service.impl;

import com.pkrok.entity.UserEntity;
import com.pkrok.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
private UserRepository userRepository;

@Autowired
public UserDetailsServiceImpl(UserRepository userRepository){
    this.userRepository=userRepository;
}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User with username [" + username + "] not found")
        );

        return User
                .builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .authorities(getAuthority(entity))
                .build();
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserEntity entity) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        entity.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())); 	// ROLE_USER ROLE_ADMIN
        });
        return authorities;
    }
}
