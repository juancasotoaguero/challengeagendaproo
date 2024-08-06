package entity;

import com.agendapro.challenge.bean.domain.Role;
import com.agendapro.challenge.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Test
    public void testGettersAndSetters() {
        User test = new User();
        test.setId(1);
        test.setFirstName("Test");
        test.setLastName("Test");
        test.setEmail("test@test.com");
        test.setPassword("password");
        test.setRole(Role.ADMIN);


        assertNotNull(test);
        assertNotNull(test.getId());
        assertNotNull(test.getFirstName());
        assertNotNull(test.getLastName());
        assertNotNull(test.getEmail());
        assertNotNull(test.getPassword());
        assertNotNull(test.getRole());
    }
}
