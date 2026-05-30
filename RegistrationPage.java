import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The Registration Page, it will help user to register their accounts.
 * The account will need user's name, password, and password re-enter.
 * Press Sign-up button after typing the information, and
 * it will show the message.
 * The back button will navigate users back to the ReregistrationSystem page.
 */

public class RegistrationPage extends JFrame {
    private JTextField nameField;
    private JPasswordField passwordField;
    private JPasswordField passwordReenterField;
    private User user = new User();
    private Database db = new Database();

    RegistrationPage(JFrame previousPage) {

        setTitle("Registration");
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(220, 220, 220));

        JLabel title = new JLabel("Registration", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setOpaque(true);
        title.setBackground(new Color(160, 160, 160));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 0, 420, 60);
        getContentPane().add(title);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 22));
        nameLabel.setBounds(50, 70, 100, 30);
        getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(160, 75, 180, 25);
        getContentPane().add(nameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 22));
        passwordLabel.setBounds(50, 120, 120, 30);
        getContentPane().add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 126, 160, 25);
        getContentPane().add(passwordField);

        JButton signUpButton = new JButton("Sign up");
        signUpButton.setBounds(99, 218, 90, 35);
        getContentPane().add(signUpButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(213, 218, 90, 35);
        getContentPane().add(backButton);

        JLabel lblPasswordReenter = new JLabel("Password re-enter:");
        lblPasswordReenter.setFont(new Font("Arial", Font.BOLD, 20));
        lblPasswordReenter.setBounds(10, 165, 191, 30);
        getContentPane().add(lblPasswordReenter);

        passwordReenterField = new JPasswordField();
        passwordReenterField.setBounds(198, 165, 160, 25);
        getContentPane().add(passwordReenterField);
        signUpButton.addActionListener(e -> signUp());

        backButton.addActionListener(e -> {
            previousPage.setVisible(true);
            dispose();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Sign-up validation checks:
     * 1. If users leave empty for their information(name or password or re-enter password),
     * it will show message for warning.
     * 2. If users' password not match with their re-enter password,
     * it will show message for warning.
     * 3. If users' user-name already exists in the database,
     * it will show message for warning.
     * 4. If users pass registration, it will show message
     * and return to the mainPage.
     */
    private void signUp() {
        this.setData();

        // empty
        if (nameField.getText().isEmpty() || passwordField.getPassword().length == 0 || passwordReenterField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this,
                    "Please input username, password, and confirm password.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        // password not match
        if (!new String(passwordField.getPassword()).matches(new String(passwordReenterField.getPassword()))) {
            JOptionPane.showMessageDialog(this,
                    "password and confirm password do not match.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        // exist
        if (db.getUser(this.user) != null) {
            JOptionPane.showMessageDialog(this,
                    "This username already exists,\nplease make a different username.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // registration success
        if (db.addUser(this.user)) {
            JOptionPane.showMessageDialog(this,
                    "Hello! Your account registration succeeded.",
                    "Welcome!",
                    JOptionPane.INFORMATION_MESSAGE);

            MainPage mainPage = new MainPage();
            mainPage.setVisible(true);
            this.dispose();
        }
    }

    /**
     * Save nameField -> user. username.
     * Password -> hash -> save to passwordField.
     */
    private void setData() {
        this.user.setUsername(nameField.getText());
        this.user.setPassword(PasswordHasher.hashPassword(new String(passwordField.getPassword())));
    }
}
