
public class User {
	private String username;
	private String password;
	private boolean isAdmin;
	
	/**
	 * constructor
	 * @param username
	 * @param password
	 * @param isAdmin
	 */
	public User(String username, String password, boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	/**
	 * constructor
	 */
	public User() {
		this.username = null;
		this.password = null;
		this.isAdmin = false;
	}

	// getters and setters
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
