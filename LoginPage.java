import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class LoginPage extends JFrame {
    JTextField nameField;
    JPasswordField passwordField;
    JFrame previousPage;
    private User user = new User();
    private Database db = new Database();

    public LoginPage(JFrame previousPage) {
        this.previousPage = previousPage;

        setTitle("Registration/Login");
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(220, 220, 220));

        JLabel title = new JLabel("Registration/Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setOpaque(true);
        title.setBackground(new Color(160, 160, 160));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 0, 420, 60);
        getContentPane().add(title);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 22));
        nameLabel.setBounds(50, 90, 100, 30);
        getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(160, 95, 180, 25);
        getContentPane().add(nameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 22));
        passwordLabel.setBounds(50, 140, 120, 30);
        getContentPane().add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 145, 160, 25);
        getContentPane().add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(103, 204, 90, 35);
        getContentPane().add(loginButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(213, 204, 90, 35);
        getContentPane().add(backButton);
        loginButton.addActionListener(e -> login());

        backButton.addActionListener(e -> {
            previousPage.setVisible(true);
            dispose();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void login() {
    	this.setData();
    	
    	// validation checks
    	if (nameField.getText().isEmpty() || passwordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this,
                    "Please input username and password.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
    	
        if (db.getUser(this.user) == null) {
            JOptionPane.showMessageDialog(this,
                    "This user doesn't exist,\nplease make an account.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!PasswordHasher.checkPassword(new String(passwordField.getPassword()), db.getUser(this.user).getPassword())) {
            JOptionPane.showMessageDialog(this,
                    "Username and password do not match.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // set status to user session
        User userData = db.getUser(user);
        UserSession userSession = UserSession.getInstance();
        userSession.login(userData);
        
        JOptionPane.showMessageDialog(this, "Login successful!");
        MainPage mainPage = new MainPage();
        mainPage.setVisible(true);
        this.dispose();
    }
    
    /**
     * sets field data to User 
     */
    private void setData() {
    	this.user.setUsername(nameField.getText());
    	this.user.setPassword(PasswordHasher.hashPassword(new String(passwordField.getPassword())));
    }
}