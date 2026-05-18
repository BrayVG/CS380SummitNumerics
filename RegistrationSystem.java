import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

public class RegistrationSystem extends JFrame {
	private JFrame previousPage;
	
	RegistrationSystem(JFrame previousPage) {
		this.previousPage = previousPage;
		
		setTitle("Ultimate Registration System");
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(220, 220, 220));
		
        JLabel title = new JLabel("Ultimate Registration System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setOpaque(true);
        title.setBackground(new Color(160, 160, 160));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 0, 420, 60);
        getContentPane().add(title);
        		
		JButton signUpButton = new JButton("Sign up");
		signUpButton.setBounds(52, 127, 90, 35);
		this.getContentPane().add(signUpButton);
		
		signUpButton.addActionListener(e -> {
            new RegistrationPage(this);
            setVisible(false);
        });
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(162, 127, 90, 35);
		this.getContentPane().add(loginButton);
		
		loginButton.addActionListener(e -> {
            new LoginPage(this);
            setVisible(false);
        });
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(271, 127, 90, 35);
		this.getContentPane().add(backButton);
		
		backButton.addActionListener(e -> {
            previousPage.setVisible(true);
            dispose();
        });
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
