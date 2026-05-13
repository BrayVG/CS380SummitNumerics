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

    public LoginPage(JFrame previousPage) {
        this.previousPage = previousPage;

        setTitle("Registration/Login");
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(220, 220, 220));

        JLabel title = new JLabel("Registration/Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setOpaque(true);
        title.setBackground(new Color(160, 160, 160));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 0, 420, 60);
        add(title);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 22));
        nameLabel.setBounds(50, 90, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(160, 95, 180, 25);
        add(nameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 22));
        passwordLabel.setBounds(50, 140, 120, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 145, 160, 25);
        add(passwordField);

        JButton signUpButton = new JButton("Sign up");
        signUpButton.setBounds(50, 205, 90, 35);
        add(signUpButton);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(160, 205, 90, 35);
        add(loginButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(270, 205, 90, 35);
        add(backButton);

        signUpButton.addActionListener(e -> signUp());
        loginButton.addActionListener(e -> login());

        backButton.addActionListener(e -> {
            previousPage.setVisible(true);
            dispose();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void signUp() {
    	this.setData();

        if (name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter name and password.");
            return;
        }

        if (Database.users.containsKey(name)) {
            JOptionPane.showMessageDialog(this,
                    "This user already exists,\nPlease change the user name.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            Database.users.put(name, password);
            JOptionPane.showMessageDialog(this,
                    "Hello! Your account registration succeeded.",
                    "Welcome!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void login() {
    	this.setData();

        if (!Database.users.containsKey(name)) {
            JOptionPane.showMessageDialog(this,
                    "This user doesn't exist,\nplease make an account.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!Database.users.get(name).equals(password)) {
            JOptionPane.showMessageDialog(this,
                    "Wrong password.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Login successful!");
    }
    
    private void setData() {
    	this.user.setUsername(nameField.getText());
    	this.user.setPassword(new String(passwordField.getPassword()));
    }
}