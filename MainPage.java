import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


class MainPage extends JFrame {
    public MainPage() {
        setTitle("this is a game");
        setSize(400, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(220, 220, 220));

        JLabel title = new JLabel("Game", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setOpaque(true);
        title.setBackground(new Color(160, 160, 160));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 0, 400, 70);
        add(title);

        JButton accountButton = new JButton("R/L");
        accountButton.setBounds(20, 15, 45, 40);
        add(accountButton);

        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 28));
        startButton.setBounds(90, 180, 220, 55);
        add(startButton);
        
        JButton adminButton = new JButton("Admin");
        adminButton.setFont(new Font("Arial", Font.BOLD,28));
        add(adminButton);
        

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Arial", Font.BOLD, 28));
        quitButton.setBounds(90, 330, 220, 55);
        add(quitButton);

        accountButton.addActionListener(e -> {
            new RegistrationSystem(this);
            setVisible(false);
        });

        startButton.addActionListener(e -> {
            new GamePage();
            dispose();
        });
        
        adminButton.addActionListener(e -> { // add the admin button in MainPage,
            // this will show the Admin button between the Start and the Quit
            new AdminPage(this);
            setVisible(false);
        });

        quitButton.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
