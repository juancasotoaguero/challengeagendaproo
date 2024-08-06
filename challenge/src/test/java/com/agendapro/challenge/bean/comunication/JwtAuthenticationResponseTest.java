package com.agendapro.challenge.bean.comunication;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class JwtAuthenticationResponseTest {
    @Test
    public void testGettersAndSetters() {
        JwtAuthenticationResponse test = new JwtAuthenticationResponse();
        test.setToken("1sd56as1d56a1sda61sda");

        assertNotNull(test);
        assertNotNull(test.getToken());
    }
}
