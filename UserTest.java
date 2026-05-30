
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	public void getUsernameTest() {
		System.out.println("getUsernameTest");
		User instance = new User("username", "password", false);
		String expResult = "username";
		String result = instance.getUsername();
        assertEquals(expResult, result);
	}
	
	@Test
	public void setUsernameTest() {
		System.out.println("setUsernameTest");
		User instance = new User("username", "password", false);
		String expResult = "usernameNew";
		instance.setUsername(expResult);
        assertEquals(expResult, instance.getUsername());
	}
	
	@Test
	public void getPasswordTest() {
		System.out.println("getPasswordeTest");
		User instance = new User("username", "password", false);
		String expResult = "password";
		String result = instance.getPassword();
        assertEquals(expResult, result);
	}
	
	@Test
	public void setPasswordTest() {
		System.out.println("setPasswordTest");
		User instance = new User("username", "password", false);
		String expResult = "passwordNew";
		instance.setPassword(expResult);
        assertEquals(expResult, instance.getPassword());
	}
	
	@Test
	public void isAdminTest() {
		System.out.println("isAdminTest");
		User instance = new User("username", "password", false);
		boolean expResult = false;
		boolean result = instance.isAdmin();
        assertEquals(expResult, result);
	}
	
	@Test
	public void setAdminTest() {
		System.out.println("setAdminTest");
		User instance = new User("username", "password", false);
		boolean expResult = true;
		instance.setAdmin(expResult);
        assertEquals(expResult, instance.isAdmin());
	}

}
