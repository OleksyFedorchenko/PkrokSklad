package com.pkrok.Service;

import com.pkrok.Domain.SigninRequest;
import com.pkrok.Domain.SignupRequest;

public interface AuthService {
    void signup(SignupRequest request);

    String signin(SigninRequest request);
}
