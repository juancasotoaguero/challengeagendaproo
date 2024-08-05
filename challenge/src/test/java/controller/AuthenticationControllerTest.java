package controller;

import com.agendapro.challenge.bean.comunication.JwtAuthenticationResponse;
import com.agendapro.challenge.bean.comunication.SignUpRequest;
import com.agendapro.challenge.bean.comunication.SigninRequest;
import com.agendapro.challenge.controller.AuthenticationController;
import com.agendapro.challenge.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignup() {
        SignUpRequest signUpRequest = new SignUpRequest("name", "lastName", "email", "password");
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse("token");

        when(authenticationService.signup(signUpRequest)).thenReturn(jwtAuthenticationResponse);

        ResponseEntity<JwtAuthenticationResponse> responseEntity = authenticationController.signup(signUpRequest);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(jwtAuthenticationResponse, responseEntity.getBody());
    }

    @Test
    public void testSignin() {
        SigninRequest signinRequest = new SigninRequest("username", "password");
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse("token");

        when(authenticationService.signin(signinRequest)).thenReturn(jwtAuthenticationResponse);

        ResponseEntity<JwtAuthenticationResponse> responseEntity = authenticationController.signin(signinRequest);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(jwtAuthenticationResponse, responseEntity.getBody());
    }
}
