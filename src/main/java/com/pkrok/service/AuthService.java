package com.pkrok.service;

import com.pkrok.domain.SigninRequest;
import com.pkrok.domain.SignupRequest;
import com.pkrok.entity.UserEntity;

import java.util.List;

public interface AuthService {
    void signup(SignupRequest request);

    String signin(SigninRequest request);

    List<UserEntity> findAllUsersOrderById();

    void deleteUserById(Long id);

    void setUserById(UserEntity userEntity);
}
