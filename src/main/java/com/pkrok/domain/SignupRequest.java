package com.pkrok.domain;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private String image;
}
