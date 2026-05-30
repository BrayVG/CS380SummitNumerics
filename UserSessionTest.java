import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserSessionTest {
	private UserSession session;

    @BeforeEach
    void setUp() throws Exception {
        Field instanceField = UserSession.class.getDeclaredField("userSession");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
        session = UserSession.getInstance();
    }

    @Test
    void testGetInstance_ShouldAlwaysReturnTheSameInstance() {
        UserSession secondSession = UserSession.getInstance();
        assertNotNull(session, "Session instance should not be null");
        assertSame(session, secondSession, "GetInstance should return the exact same instance");
    }

    @Test
    void testInitialState_ShouldNotBeLoggedInByDefault() {
        assertFalse(session.isLoggedIn(), "Initial login status should be false");
        assertFalse(session.isAdmin(), "Initial admin status should be false");
        assertNull(session.getLoggedInUsername(), "Initial username should be null");
    }

    @Test
    void testLogin_WithNormalUser() {
        User normalUser = new User();
        normalUser.setUsername("john_doe");
        normalUser.setAdmin(false);

        session.login(normalUser);

        assertTrue(session.isLoggedIn(), "Login status should be true");
        assertFalse(session.isAdmin(), "IsAdmin should be false for a normal user");
        assertEquals("john_doe", session.getLoggedInUsername(), "Username should match the logged-in user");
    }

    @Test
    void testLogin_WithAdminUser() {
        User adminUser = new User();
        adminUser.setUsername("admin_boss");
        adminUser.setAdmin(true);

        session.login(adminUser);

        assertTrue(session.isLoggedIn(), "Login status should be true");
        assertTrue(session.isAdmin(), "IsAdmin should be true for an admin user");
        assertEquals("admin_boss", session.getLoggedInUsername(), "Username should match the admin user");
    }
}
