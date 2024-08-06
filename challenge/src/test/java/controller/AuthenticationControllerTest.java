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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    private SignUpRequest signUpRequest;
    private SigninRequest signinRequest;
    private JwtAuthenticationResponse jwtAuthenticationResponse;

    @BeforeEach
    void setUp() {
        signUpRequest = new SignUpRequest();
        signinRequest = new SigninRequest();
        jwtAuthenticationResponse = new JwtAuthenticationResponse();
    }

    @Test
    void testSignup() {
        jwtAuthenticationResponse.setToken("adlksjdlaj");
        when(authenticationService.signup(any(SignUpRequest.class))).thenReturn(jwtAuthenticationResponse);

        ResponseEntity<JwtAuthenticationResponse> responseEntity = authenticationController.signup(signUpRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(jwtAuthenticationResponse, responseEntity.getBody());
    }

    @Test
    void testSignupNotAcceptable() {
        jwtAuthenticationResponse.setToken(null);
        when(authenticationService.signup(any(SignUpRequest.class))).thenReturn(jwtAuthenticationResponse);

        ResponseEntity<JwtAuthenticationResponse> responseEntity = authenticationController.signup(signUpRequest);

        assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
    }

    @Test
    void testSignin() {
        when(authenticationService.signin(any(SigninRequest.class))).thenReturn(jwtAuthenticationResponse);

        ResponseEntity<JwtAuthenticationResponse> responseEntity = authenticationController.signin(signinRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(jwtAuthenticationResponse, responseEntity.getBody());
    }
}