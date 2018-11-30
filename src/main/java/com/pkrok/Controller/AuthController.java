package com.pkrok.Controller;

import com.pkrok.Domain.SigninRequest;
import com.pkrok.Domain.SigninResponse;
import com.pkrok.Domain.SignupRequest;
import com.pkrok.Entity.UserEntity;
import com.pkrok.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest request) {
        String token = authService.signin(request);
        return new ResponseEntity<>(new SigninResponse(token), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsersById() {
        return ResponseEntity.ok(authService.findAllUsersOrderById());
    }
}
