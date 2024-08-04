package com.agendapro.challenge.controller;

import com.agendapro.challenge.bean.comunication.JwtAuthenticationResponse;
import com.agendapro.challenge.bean.comunication.SignUpRequest;
import com.agendapro.challenge.bean.comunication.SigninRequest;
import com.agendapro.challenge.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignUpRequest request){
        JwtAuthenticationResponse response = authenticationService.signup(request);
        if (response.getToken() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}