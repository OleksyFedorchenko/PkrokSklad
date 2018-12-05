package com.pkrok.service.impl;

import com.pkrok.config.jwt.JwtTokenProvider;
import com.pkrok.domain.SigninRequest;
import com.pkrok.domain.SignupRequest;
import com.pkrok.entity.RoleEntity;
import com.pkrok.entity.UserEntity;
import com.pkrok.exceptions.AlreadyExistsException;
import com.pkrok.exceptions.ResourceNotFoundException;
import com.pkrok.repository.RoleRepository;
import com.pkrok.repository.UserRepository;
import com.pkrok.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void signup(SignupRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AlreadyExistsException("User with name " + request.getUsername() + " already exists");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        RoleEntity role = roleRepository.findByName("USER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(role);
        userEntity.setRoles(roles);

        userRepository.save(userEntity);

    }

    @Override
    public String signin(SigninRequest request) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public List<UserEntity> findAllUsersOrderById() {
        return userRepository.findAllByOrderById();
    }

    @Override
    public void setUserById(Long id, String name, String role) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find user with this id"));
        user.setUsername(name);
        RoleEntity roles = roleRepository.findByName(role).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        user.getRoles().add(roles);
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
