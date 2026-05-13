import java.time.LocalDateTime;

public class Score {
	private int scoreId;
	private String username;
	private int finalScore;
	private LocalDateTime  dateTimePlayed;
	
	/**
	 * constructor
	 * @param scoreId
	 * @param username
	 * @param finalScore
	 * @param dateTimePlayed
	 */
	public Score(int scoreId, String username, int finalScore, LocalDateTime dateTimePlayed) {
		super();
		this.scoreId = scoreId;
		this.username = username;
		this.finalScore = finalScore;
		this.dateTimePlayed = dateTimePlayed;
	}

	// getter and setter
	public int getScoreId() {
		return scoreId;
	}

	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getFinalScore() {
		return finalScore;
	}
	
	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}
	
	public LocalDateTime getDateTimePlayed() {
		return dateTimePlayed;
	}
	
	public void setDateTimePlayed(LocalDateTime dateTimePlayed) {
		this.dateTimePlayed = dateTimePlayed;
	}
}