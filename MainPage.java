import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * Create the main menu window for users to interact
 * This window will show the Start button, Register/Login, AdminPage
 * and Quit button
 */
class MainPage extends JFrame {
    /**
     * Create MainPage constructor and set up all labels, buttons
     * ,and actions.
     * Start button will depend on users login status.
     * Admin button will depend on users status( if admin or not )
     * If users not login first and interact with start button,
     * it will show message for warning( Will not open the game)
     * Quit button will quit the game, nothing too fancy in here.
     */
    public MainPage() {
        setTitle("Summit Numerics!| TEST YOUR BRAIN LIMIT!");
        setSize(420, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(220, 220, 220));

        JLabel title = new JLabel("Welcome", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setOpaque(true);
        title.setBackground(new Color(160, 160, 160));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 0, 420, 70);
        add(title);

        JButton accountButton = new JButton("R/L");
        accountButton.setBounds(20, 15, 60, 40);
        add(accountButton);

        /*
         * Yuna updated :
         * If user is Admin, the Start Button won't show up.
         * Chader: next time, please leave some messages for your modifying.
         * ちょっと怖いね、もしコメントしてなかったら！
         * Overall, great job!
         *  */

        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 28));
        startButton.setBounds(90, 180, 220, 55);
        add(startButton);
        if (UserSession.getInstance().isAdmin()) {
            startButton.setVisible(false);
        }

        /*
         * Yuna updated:
         * If user is Admin,the Admin Button will show up, and
         * replace the position with Start Button.
         * */

        JButton adminButton = new JButton("Admin");
        adminButton.setFont(new Font("Arial", Font.BOLD, 28));
        adminButton.setBounds(90, 180, 220, 55);
        add(adminButton);
        if (UserSession.getInstance().isAdmin()) {
            adminButton.setVisible(true);
        }

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Arial", Font.BOLD, 28));
        quitButton.setBounds(90, 330, 220, 55);
        add(quitButton);

        accountButton.addActionListener(e -> {
            new RegistrationSystem(this);
            setVisible(false);
        });

        /*
         * Detecting if the user is logging or not
         * If not show the message
         * */
        startButton.addActionListener(e -> {
            if (UserSession.getInstance().isLoggedIn()) {
                new GamePage();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Need to be logged in.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        adminButton.addActionListener(e -> {
            new AdminPage(this);
            setVisible(false);
        });

        quitButton.addActionListener(e -> System.exit(0)); // quit system.

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
