import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

class GamePage extends JFrame {
    JLabel[] questionLabels = new JLabel[5];
    JTextField[] answerFields = new JTextField[5];

    Question[] questions = {
            new BaseQuestion(),
            new BaseQuestion(),
            new BaseQuestion(),
            new BaseQuestion(),
            new BaseQuestion()
    };

    int score = 0;
    JLabel scoreLabel;
    JLabel timerLabel;

    int timeLeft = 300;
    Timer timer;

    public GamePage() {
        setTitle("Game Page");
        setSize(650, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(220, 220, 220));

        JLabel title = new JLabel("Game Title", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setOpaque(true);
        title.setBackground(new Color(160, 160, 160));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 0, 650, 60);
        add(title);

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(null);
        questionPanel.setBackground(Color.WHITE);
        questionPanel.setBounds(30, 90, 380, 380);
        add(questionPanel);

        JLabel interfaceLabel = new JLabel("Game Interface");
        interfaceLabel.setFont(new Font("Arial", Font.BOLD, 28));
        interfaceLabel.setBounds(30, 10, 300, 40);
        questionPanel.add(interfaceLabel);

        for (int i = 0; i < 5; i++) {
            questionLabels[i] = new JLabel();
            questionLabels[i].setFont(new Font("Arial", Font.BOLD, 22));
            questionLabels[i].setBounds(40, 70 + i * 55, 180, 35);
            questionPanel.add(questionLabels[i]);

            JLabel equalLabel = new JLabel("=");
            equalLabel.setFont(new Font("Arial", Font.BOLD, 24));
            equalLabel.setBounds(220, 70 + i * 55, 30, 35);
            questionPanel.add(equalLabel);

            answerFields[i] = new JTextField();
            answerFields[i].setBounds(260, 75 + i * 55, 70, 30);
            questionPanel.add(answerFields[i]);
        }

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(Color.WHITE);
        scoreLabel.setBounds(455, 90, 120, 100);
        add(scoreLabel);

        timerLabel = new JLabel("05:00", SwingConstants.CENTER);
        timerLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        timerLabel.setBounds(465, 230, 100, 35);
        add(timerLabel);

        JButton nextButton = new JButton("Next");
        nextButton.setBounds(465, 330, 100, 35);
        add(nextButton);

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(465, 430, 100, 35);
        add(quitButton);

        generateQuestions();

        nextButton.addActionListener(e -> {
            checkAnswers();
            generateQuestions();
        });

        quitButton.addActionListener(e -> quitGame());

        startTimer();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generateQuestions() {
        for (int i = 0; i < questions.length; i++) {
            questions[i].generateQuestion();
            questionLabels[i].setText(questions[i].getQuestionText());
            answerFields[i].setText("");
        }
    }

    private void checkAnswers() {
        for (int i = 0; i < questions.length; i++) {
            if (questions[i].answerCheck(answerFields[i].getText())) {
                score = score + 10;
            }
        }

        scoreLabel.setText("Score: " + score);
    }

    private void startTimer() {
        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(1000, e -> {
            timeLeft--;

            timerLabel.setText(String.format("%02d:%02d", timeLeft / 60, timeLeft % 60));

            if (timeLeft <= 0) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "Time is up!\nFinal score: " + score);
                dispose();
                new MainPage();
            }
        });

        timer.start();
    }

    private void quitGame() {
        int result = JOptionPane.showConfirmDialog(this,
                "Want to quit?\nYou will not receive any points.",
                "Warning",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            timer.stop();
            dispose();
            new MainPage();
        }
    }
}