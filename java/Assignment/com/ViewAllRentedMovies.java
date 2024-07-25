package Assignment.com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewAllRentedMovies {
	static String dbURL = "jdbc:mysql://localhost:3306/movie_rental_platform";
    static String userN = "root";
    static String pass = "";
    
    public List<ViewRentedMovies> getAllUsers() throws SQLException, ClassNotFoundException {
        List<ViewRentedMovies> rentals = new ArrayList<>();
 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbURL, userN, pass);
            String sql = "SELECT * FROM rentals";
 
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
 
            while (rs.next()) {
                int rental_id = rs.getInt("Rental_id");
                int user_id = rs.getInt("User_id");
                int movie_id = rs.getInt("Movie_id");
                String title = rs.getString("Title");
                String genre = rs.getString("Genre");
                String release_year = rs.getString("Release_Year");
                String director = rs.getString("Director");
                String rating = rs.getString("Rating");
                String rental_date = rs.getString("Rental_date");
                String return_date = rs.getString("Return_date");
    
                
                rentals.add(new ViewRentedMovies(movie_id, title, genre, release_year, director, rating,rental_date,return_date,rental_id,user_id));
            }
        } finally {
            // Close resources
        }
        return rentals;
    }
    
public List<rentals> getLogin() throws ClassNotFoundException, SQLException{
    	
    	List<rentals> log = new ArrayList();
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbURL, userN, pass);
    	String sql = "SELECT * FROM rentals";
    	  PreparedStatement st = con.prepareStatement(sql);
    	  
    	  ResultSet rs = st.executeQuery();
    	  
    	  while(rs.next()) {
    		  String tit = rs.getString("title");
    		  String genr = rs.getString("genre");
    		  
    		  log.add(new rentals(tit,genr));
    	  }
    		
    	}
    	
    	finally {
    		
    		
    	}
    	return log;
    	
    }
 
 public void deleteUser(int id) throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection(dbURL, userN, pass);
			String sql = "DELETE FROM rentals WHERE user_id=? ";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		finally{
			
		}
	}

	public List<ViewRentedMovies> searchUsers(String title) {
		// TODO Auto-generated method stub
		return null;
	}
}
