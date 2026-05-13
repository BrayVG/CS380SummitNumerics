import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lab3.GradeData;

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
		
		String url = "jdbc:mysql://localhost:3306/exampledb";
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
	 * @param sql
	 * @return true if the procedure succeeded
	 * 			false if not
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
		// need to be changed
		String sql = String.format("INSERT INTO 380_grades VALUES (%d, '%s', '%s', %d)", data.getStudentId(), data.getFirstName(), data.getLastName(), data.getGrade());
        return executeUpdateQuery(sql);
	}
	
	public User getUser() {
        try {
            Statement st = con.createStatement();
            // need to be changed
            ResultSet rs = st.executeQuery("SELECT * FROM 380_grades");
            User userData = null;
            
            if (rs.next()) {
            	userData = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("isAdmin")
                    );
            }
            
            return userData;
        } catch (Exception e) {
        	System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
	
	public boolean addScore(Score scoreData) {
		// need to be changed
		String sql = String.format("INSERT INTO 380_grades VALUES (%d, '%s', '%s', %d)", data.getStudentId(), data.getFirstName(), data.getLastName(), data.getGrade());
        return executeUpdateQuery(sql);
	}
	
	public List<Score> getScores() {
        try {
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
	
	public int getRank() {
        try {
            Statement st = con.createStatement();
            // need to be changed
            ResultSet rs = st.executeQuery("SELECT * FROM 380_grades");
            int rank = -1;
            
            if (rs.next()) {
            	rank = rs.getInt("rank");
            }
            
            return rank;
        } catch (Exception e) {
        	System.out.println("Error: " + e.getMessage());
            return -1;
        }
    }
	
	// need to be changed all over
	public List<Score> getUsersAndScores() {
        try {
            Statement st = con.createStatement();
            // need to be changed
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
}