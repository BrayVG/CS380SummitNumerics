
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {
    private static Connection con = null;

    /**
     * constructor
     */
    public Database() {
        this.connect();
    }

    /**
     * connect to the db
     */
    public void connect() {

        String url = "jdbc:mysql://localhost:3306/summitNumerics";
        String userName = "root";
        String password = "password";

        try {
            con = DriverManager.getConnection(url, userName, password);
            System.out.println("connected");
        } catch (Exception e) {
            System.out.println("exception " + e.getMessage());
        }
    }

    /**
     * general procedures for updating the table
     *
     * @param sql
     * @return true if the procedure succeeded
     * false if not
     */
    private boolean executeUpdateQuery(String sql) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean addUser(User userData) {
        String sql = String.format("INSERT INTO user (username, password, isAdmin) VALUES ('%s', '%s', FALSE)", userData.getUsername(), userData.getPassword());
        return executeUpdateQuery(sql);
    }

    public User getUser(User userData) {
        try {
            String sql = "SELECT * FROM user WHERE username = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userData.getUsername());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                userData = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("isAdmin")
                );
                return userData;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public int addScore(int score, String username) {
        String sql = "INSERT INTO score (username, finalScore, dateTimePlayed) VALUES (?, ?, NOW())";
        int scoreId = -1;
        try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, score);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        scoreId = rs.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return scoreId;
    }

    public List<Score> getScores() {
        try {
            // need to be changed
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM 380_grades");

            List<Score> list = new ArrayList<>();

            while (rs.next()) {
                Score score = new Score(
                        rs.getInt("scoreID"),
                        rs.getString("username"),
                        rs.getInt("finalScore"),
                        rs.getTimestamp("dateTimePlayed").toLocalDateTime()
                );
                list.add(score);
            }

            return list;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public int getRank(int scoreId) {
        int rank = -1;
        try {
            String sql = "SELECT RANK() OVER (ORDER BY finalScore DESC) AS ranking FROM score WHERE scoreId = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, scoreId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                rank = rs.getInt("ranking");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rank;
    }

    // need to be changed all over
    //This method needs to be updated before using it for AdminPage.
    //Also, Score does not store rank, so AdminPage should use AdminRecord.
    public List<Score> getUsersAndScores() {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT username, finalScore, RANK() OVER (ORDER BY finalScore DESC) AS ranking FROM score");

            List<Score> list = new ArrayList<>();

            while (rs.next()) {
                Score score = new Score(
                        rs.getInt("scoreID"),
                        rs.getString("username"),
                        rs.getInt("finalScore"),
                        rs.getTimestamp("dateTimePlayed").toLocalDateTime()
                );
                list.add(score);
            }

            return list;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /*
     * This method is used by the AdminPage "List in order" button.
     * It gets each user's highest score from the score table,
     * ranks users by highest score from high to low,
     * and returns the result as a list of AdminRecord objects.
     */
    public List<AdminRecord> getAdminRecordsInOrder() {
        List<AdminRecord> list = new ArrayList<>();

        String sql = "SELECT username, finalScore, RANK() OVER (ORDER BY finalScore DESC) AS ranking " +
                "FROM (" +
                "   SELECT username, MAX(finalScore) AS finalScore " +
                "   FROM score " +
                "   GROUP BY username " +
                ") best_scores " +
                "ORDER BY ranking";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                AdminRecord record = new AdminRecord(
                        rs.getString("username"),
                        rs.getInt("ranking"),
                        rs.getInt("finalScore")
                );

                list.add(record);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return list;
    }

    /* Search users by username for AdminPage.
     * First, get each user's highest score.
     * Then, rank users by their highest score.
     * Finally, filter the result by the username keyword from the search field.
     */
    public List<AdminRecord> searchAdminRecords(String usernameKeyword) {
        List<AdminRecord> list = new ArrayList<>();
        String sql = "SELECT username, finalScore, ranking FROM (" +
                "   SELECT username, finalScore, RANK() OVER (ORDER BY finalScore DESC) AS ranking " +
                "   FROM (" +
                "       SELECT username, MAX(finalScore) AS finalScore " +
                "       FROM score " +
                "       GROUP BY username " +
                "   ) best_scores " +
                ") ranked " +
                "WHERE username LIKE ? " +
                "ORDER BY ranking";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + usernameKeyword + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                AdminRecord record = new AdminRecord(
                        rs.getString("username"),
                        rs.getInt("ranking"),
                        rs.getInt("finalScore")
                );

                list.add(record);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return list;
    }

}