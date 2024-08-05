package com.agendapro.challenge.bean.comunication;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class SignUpRequestTest {

    @Test
    public void testGettersAndSetters() {
        SigninRequest test = new SigninRequest();
        test.setEmail("aklsjd@ks.com");
        test.setPassword("password");

        assertNotNull(test);
        assertNotNull(test.getPassword());
        assertNotNull(test.getEmail());
    }
}
