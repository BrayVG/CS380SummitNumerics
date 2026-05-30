import org.mindrot.jbcrypt.BCrypt;

/**
 * The PasswordHasher class is used to hash and check user passwords.
 * It uses BCrypt to protect the password before saving it to the database.
 */
public class PasswordHasher {
    /**
     * Hashes a plain text password using BCrypt.
     *
     * @param plainTextPassword the password entered by the user
     * @return the hashed password
     */
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(12));
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        /**
         * Checks if the plain text password matches the hashed password.
         *
         * @param plainTextPassword the password entered by the user
         * @param hashedPassword the hashed password stored in the database
         * @return true if the password matches, otherwise false
         */
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}