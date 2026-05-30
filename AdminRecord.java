/**
 * AdminRecord is used to store one row of data for the AdminPage table.
 * This class is mainly used by AdminPage to display:
 * username, rank, and final score.
 */
public class AdminRecord {
    private String username;
    private int rank;
    private int finalScore;

    /**
     * Constructor for AdminRecord.
     *
     * @param username   the player's username
     * @param rank       the player's rank
     * @param finalScore the player's final score
     */
    public AdminRecord(String username, int rank, int finalScore) {
        this.username = username;
        this.rank = rank;
        this.finalScore = finalScore;
    }

    /**
     * Get the player's username.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the player's rank.
     *
     * @return rank
     */

    public int getRank() {
        return rank;
    }

    /**
     * Get the player's final score.
     *
     * @return finalscore
     */

    public int getFinalScore() {
        return finalScore;
    }
}