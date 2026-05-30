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

/**
 * It will display 5 questions, answer fields, the player's score,
 * and a countdown timer.
 * Users can answer the questions, and press next button for
 * keeping answer more questions(infinite).
 * The quit button will navigate users back to mainPage.
 */
class GamePage extends JFrame {

    JLabel[] questionLabels = new JLabel[5]; // display 5 questions
    JTextField[] answerFields = new JTextField[5]; // display 5 answer fields

    // Stores the five question objects used in the game.
    Question[] questions = {
            new BaseQuestion(),
            new BaseQuestion(),
            new BaseQuestion(),
            new BaseQuestion(),
            new BaseQuestion()
    };

    int score = 0; // Stores the player's current score.
    JLabel scoreLabel; // display scores
    JLabel timerLabel; // display timer

    int timeLeft = 300;
    Timer timer;

    /**
     * The game starts and starts the timer.
     * Timer 300 second -> 5:00.
     * score label starts from 0.
     * There is 5 questions and 5 answer fields.
     * Every time, user press the next button will check
     * correctness of the user's answers and bring a new set of questions.
     */

    public GamePage() {

        setTitle("TEST YOUR BRAIN LIMIT!");
        setSize(650, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(220, 220, 220));

        JLabel title = new JLabel("Summit Numerics", SwingConstants.CENTER);
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

        JLabel interfaceLabel = new JLabel("easy right?");
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

    /**
     * Generates a new set of questions and displays them on the screen.
     * Cleans the answerField.
     */
    private void generateQuestions() {
        for (int i = 0; i < questions.length; i++) {
            questions[i].generateQuestion();
            questionLabels[i].setText(questions[i].getQuestionText());
            answerFields[i].setText("");
        }
    }

    /***
     * Checks the player's answers for all questions.
     * If an answer is correct, the player's score increases by 10 points.
     * The score label is updated after checking all answers.
     */

    private void checkAnswers() {
        for (int i = 0; i < questions.length; i++) {
            if (questions[i].answerCheck(answerFields[i].getText())) {
                score = score + 10;
            }
        }

        scoreLabel.setText("Score: " + score);
    }

    /**
     * Starts the countdown timer for the game.
     * The timer updates every second.
     * When time reaches zero, the game stops, the final score is saved
     * to the database, the player's rank is calculated, and the user
     * returns to the main page.
     */
    private void startTimer() {
        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(1000, e -> { // 1000ms = 1 sec
            timeLeft--;

            timerLabel.setText(String.format("%02d:%02d", timeLeft / 60, timeLeft % 60));

            if (timeLeft <= 0) {
                timer.stop();
                Database db = new Database();
                int scoreId = db.addScore(score, UserSession.getInstance().getLoggedInUsername());
                int rank = db.getRank(scoreId);

                JOptionPane.showMessageDialog(this, "Time is up!\nFinal score: " + score + "\nrank: " + rank);
                dispose();
                new MainPage();
            }
        });

        timer.start();
    }

    /**
     * Allows the player to quit the game early.
     * If the player confirms quitting, the timer stops and the game
     * returns to the main page without saving points.
     */

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