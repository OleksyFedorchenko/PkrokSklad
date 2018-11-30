package com.pkrok.Service;

import com.pkrok.Domain.SigninRequest;
import com.pkrok.Domain.SignupRequest;
import com.pkrok.Entity.UserEntity;

import java.util.List;

public interface AuthService {
    void signup(SignupRequest request);

    String signin(SigninRequest request);

    List<UserEntity> findAllUsersOrderById();
}
