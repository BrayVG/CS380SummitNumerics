import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import java.util.List;

class AdminPage extends JFrame{

	JFrame previousPage;
	private JTextField TitlePanel;
	private JTextField SearchField;
	private Database db = new Database();
	private JLabel[] userRows = new JLabel[7]; // Up to 7 rows.
	private JLabel[] rankRows = new JLabel[7];
	private JLabel[] scoreRows = new JLabel[7];
//    JButton backButton;
//    JButton listButton;
	
	public AdminPage(JFrame previousPage) {
		this.previousPage = previousPage; // Save the previous page.
		setResizable(false);
		setSize(570,800);
		setTitle("Admin Page");
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(192, 192, 192));
		
		JPanel TopPanel = new JPanel();
		TopPanel.setBackground(new Color(128, 128, 128));
		TopPanel.setBounds(0, 0, 554, 81);
		getContentPane().add(TopPanel);
		TopPanel.setLayout(null);
		
		TitlePanel = new JTextField();
		TitlePanel.setBounds(10, 10, 414, 60);
		TitlePanel.setHorizontalAlignment(SwingConstants.CENTER);
		TitlePanel.setFont(new Font("Arial", Font.BOLD, 42));
		TitlePanel.setBackground(new Color(128, 128, 128));
		TitlePanel.setForeground(new Color(255, 255, 255));
		TitlePanel.setText("Admin Table");
		TopPanel.add(TitlePanel);
		TitlePanel.setColumns(12);
		/*
		 * ListButton shows all users in rank order.
		 * It calls Database.getAdminRecordsInOrder().
		 */
		JButton ListButton = new JButton("List in order");
		ListButton.setBackground(new Color(240, 240, 240));
		ListButton.setBounds(433, 27, 111, 23);
		ListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<AdminRecord> records = db.getAdminRecordsInOrder();
				displayRecords(records);
			}
		});
		TopPanel.add(ListButton);
		ListButton.setFont(new Font("Arial", Font.PLAIN, 12));
		ListButton.setForeground(new Color(0, 0, 0));
		
		JPanel CentralPanel = new JPanel();
		CentralPanel.setBackground(new Color(192, 192, 192));
		CentralPanel.setBounds(0, 79, 554, 668);
		getContentPane().add(CentralPanel);
		CentralPanel.setLayout(null);
		/*
		 * Search Button searches for users by username.
		 */
		JButton SearchButton = new JButton("Search");
		SearchButton.setForeground(Color.BLACK);
		SearchButton.setFont(new Font("Arial", Font.PLAIN, 25));
		SearchButton.setBounds(431, 27, 113, 39);
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = SearchField.getText().toLowerCase().trim();
				if (keyword.isEmpty()){ // If the search field is empty, show the warning.
					JOptionPane.showMessageDialog(AdminPage.this, "Please enter a username");
					return;
				}
				List<AdminRecord> records = db.searchAdminRecords(keyword); //Search matching users from the database.
				// This calls Database.searchAdminRecords().
				if (records.isEmpty()){
					clearTable();
					JOptionPane.showMessageDialog(AdminPage.this, "No user found");

				}
				else{
					displayRecords(records);
				}
			}
		});

		CentralPanel.add(SearchButton);
		
		SearchField = new JTextField();
		SearchField.setBounds(10, 27, 411, 39);
		CentralPanel.add(SearchField);
		SearchField.setColumns(15);
		
		JPanel TablePanel = new JPanel();
		TablePanel.setBackground(new Color(255, 255, 255));
		TablePanel.setBounds(10, 82, 534, 537);
		CentralPanel.add(TablePanel);
		TablePanel.setLayout(null);
		
		JLabel UserLabel = new JLabel("user_name");
		UserLabel.setFont(new Font("Arial", Font.BOLD, 20));
		UserLabel.setForeground(new Color(0, 0, 0));
		UserLabel.setBounds(35, 10, 96, 41);
		TablePanel.add(UserLabel);
		UserLabel.setBackground(new Color(128, 128, 128));
		
		JLabel RankLabel = new JLabel("Rank");
		RankLabel.setForeground(Color.BLACK);
		RankLabel.setFont(new Font("Arial", Font.BOLD, 20));
		RankLabel.setBackground(Color.GRAY);
		RankLabel.setBounds(242, 10, 48, 41);
		TablePanel.add(RankLabel);
		
		JLabel ScoreLabel = new JLabel("Score");
		ScoreLabel.setForeground(Color.BLACK);
		ScoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
		ScoreLabel.setBackground(Color.GRAY);
		ScoreLabel.setBounds(414, 10, 64, 41);
		TablePanel.add(ScoreLabel);

		int startY = 80;
		int gap = 55;

		for (int i = 0; i < 7; i++) {
			userRows[i] = createDataLabel();
			userRows[i].setBounds(25, startY + i * gap, 130, 30);
			TablePanel.add(userRows[i]);

			rankRows[i] = createDataLabel();
			rankRows[i].setBounds(205, startY + i * gap, 120, 30);
			TablePanel.add(rankRows[i]);

			scoreRows[i] = createDataLabel();
			scoreRows[i].setBounds(370, startY + i * gap, 120, 30);
			TablePanel.add(scoreRows[i]);
		}
		// The Back button will return to previous page (MainPage).

		JButton BackButton = new JButton("Back");
		BackButton.setFont(new Font("Arial", Font.PLAIN, 15));
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previousPage.setVisible(true);
				dispose();
			}
		});
		BackButton.setBounds(431, 635, 93, 23);
		CentralPanel.add(BackButton);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	/*
	 * Create one data label for the table.
	 * This method is used to create the empty labels for username, rank,
	 * and score rows. Later, displayRecords() will set text into them.
	 * */
	private JLabel createDataLabel() {
		JLabel label = new JLabel("", SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		label.setForeground(Color.BLACK);
		label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		return label;
	}
	/*
	 * Display database records in the admin table.
	 * records contains AdminRecord objects.
	 * Each AdminRecord has:
	 * username
	 * rank
	 * finalScore
	 * */
	private void displayRecords(List<AdminRecord> records) {
		clearTable();

		int size = Math.min(records.size(), 7); // only fill 7 records

		for (int i = 0; i < size; i++) {
			AdminRecord record = records.get(i);

			userRows[i].setText(record.getUsername());
			rankRows[i].setText(String.valueOf(record.getRank()));
			scoreRows[i].setText(String.valueOf(record.getFinalScore()));
		}
	}
/*
 *  Clear all table data.
 * This is used before displaying new search results,
 * or when no user is found.
 * */
	private void clearTable() {
		for (int i = 0; i < 7; i++) {
			userRows[i].setText("");
			rankRows[i].setText("");
			scoreRows[i].setText("");
		}
	}


}