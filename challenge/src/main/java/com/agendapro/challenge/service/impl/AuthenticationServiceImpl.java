package com.agendapro.challenge.service.impl;

import com.agendapro.challenge.bean.comunication.JwtAuthenticationResponse;
import com.agendapro.challenge.bean.domain.Role;
import com.agendapro.challenge.bean.comunication.SignUpRequest;
import com.agendapro.challenge.bean.comunication.SigninRequest;
import com.agendapro.challenge.entity.User;
import com.agendapro.challenge.repository.UserRepository;
import com.agendapro.challenge.service.AuthenticationService;
import com.agendapro.challenge.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request){
        LOGGER.info("Starting Signup request: {}", request);
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        //verificar que no exista ya usuario con mismo email
        if(userRepository.findByEmail(request.getEmail()).isPresent())
            return new JwtAuthenticationResponse();

        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        LOGGER.info("FINISHED Signup request");
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        LOGGER.info("Starting Signin request: {}", request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        LOGGER.info("JWT generated: {}", jwt);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}