public class AdminRecord {
    private String username;
    private int rank;
    private int finalScore;

    public AdminRecord(String username, int rank, int finalScore) {
        this.username = username;
        this.rank = rank;
        this.finalScore = finalScore;
    }

    public String getUsername() {
        return username;
    }

    public int getRank() {
        return rank;
    }

    public int getFinalScore() {
        return finalScore;
    }
}