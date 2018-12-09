package com.pkrok.controller;

import com.pkrok.domain.SigninRequest;
import com.pkrok.domain.SigninResponse;
import com.pkrok.domain.SignupRequest;
import com.pkrok.entity.UserEntity;
import com.pkrok.service.AuthService;
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
    public AuthController(AuthService authService) {
        this.authService = authService;
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

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") Long id) {
        authService.deleteUserById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("{id}/{name}/{role}")
    public ResponseEntity<?> setUserById(@PathVariable ("id") Long id, @PathVariable("name") String name, @PathVariable ("role") String role ){
        authService.setUserById(id,name,role);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("userId") Long id) {
        return ResponseEntity.ok(authService.findUserById(id));
    }
}
