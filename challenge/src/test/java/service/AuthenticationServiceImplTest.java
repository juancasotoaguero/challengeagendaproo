package service;

import com.agendapro.challenge.bean.comunication.JwtAuthenticationResponse;
import com.agendapro.challenge.bean.comunication.SignUpRequest;
import com.agendapro.challenge.bean.comunication.SigninRequest;
import com.agendapro.challenge.bean.domain.Role;
import com.agendapro.challenge.entity.User;
import com.agendapro.challenge.repository.UserRepository;
import com.agendapro.challenge.service.JwtService;
import com.agendapro.challenge.service.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceImplTest {

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(authenticationService, "userRepository", userRepository);
        ReflectionTestUtils.setField(authenticationService, "jwtService", jwtService);
        ReflectionTestUtils.setField(authenticationService, "passwordEncoder", passwordEncoder);
        ReflectionTestUtils.setField(authenticationService, "authenticationManager", authenticationManager);
    }

    @Test
    public void testSignup() {
        SignUpRequest request = new SignUpRequest("John", "Doe", "john.doe@example.com", "password");
        User user = new User(1, "Doe","Smith", "john.doe@example.com", "encodedPassword", Role.USER);;

        when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any())).thenReturn(user);
        when(jwtService.generateToken(any())).thenReturn("token");

        assertNotNull(authenticationService.signup(request));
    }

    @Test
    public void testSignin(){
        SigninRequest request = new SigninRequest("johndoe@example.com", "password");
        User user = new User(1, "Doe","Smith", "johndoe@example.com", "encodedPassword", Role.USER);
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(Mockito.any(UserDetails.class))).thenReturn("token");

        JwtAuthenticationResponse result = authenticationService.signin(request);

        assertNotNull(result);
        assertEquals("token", result.getToken());
    }
}