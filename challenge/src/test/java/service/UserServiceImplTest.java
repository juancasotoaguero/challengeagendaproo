package service;

import com.agendapro.challenge.bean.domain.Role;
import com.agendapro.challenge.entity.User;
import com.agendapro.challenge.repository.UserRepository;
import com.agendapro.challenge.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
    }

    @Test
    public void testLoadUserByUsername_ExistingUser() {
        String email = "test@example.com";
        User user = new User(1,"John", "Doe", email, "encodedPassword", Role.USER);

        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));

        assertNotNull(userService.userDetailsService().loadUserByUsername(email));

    }

    @Test
    public void testLoadUserByUsername_NonExistingUser() {
        String email = "nonexisting@example.com";

        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> userService.userDetailsService().loadUserByUsername(email));
    }
}