package com.agendapro.challenge.service;

import com.agendapro.challenge.bean.comunication.JwtAuthenticationResponse;
import com.agendapro.challenge.bean.comunication.SignUpRequest;
import com.agendapro.challenge.bean.comunication.SigninRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}