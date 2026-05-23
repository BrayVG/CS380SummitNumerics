public class UserSession {
	private static UserSession userSession;
	private String loggedInUsername;
	private boolean isAdmin;

	/**
	 * unable to create a new instance
	 */
    private UserSession() {}

    /**
     * @return session instance
     */
    public static UserSession getInstance() {
        if (userSession == null) {
        	userSession = new UserSession();
        }
        return userSession;
    }

    /**
     * holds login status
     * @param User
     */
    public void login(User userData) {
        this.loggedInUsername = userData.getUsername();
        this.isAdmin = userData.isAdmin();
    }

    /**
     * checks if user is logged in
     * @return true if logged in
     * 			else, false
     */
    public boolean isLoggedIn() {
        return this.loggedInUsername != null;
    }
    
    /**
     * @return true if admin
     * 			else, false
     */
    public boolean isAdmin() {
        return this.isAdmin;
    }

    /**
     * @return username
     */
    public String getLoggedInUsername() {
        return loggedInUsername;
    }
}