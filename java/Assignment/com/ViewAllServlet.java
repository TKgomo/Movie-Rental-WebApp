package Assignment.com;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ViewAllServlet {
	static String dbURL = "jdbc:mysql://localhost:3306/movie_rental_platform";
    static String userN = "root";
    static String pass = "";
    
    public List<ViewMovies> getAllUsers() throws SQLException, ClassNotFoundException {
        List<ViewMovies> movies = new ArrayList<>();
 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbURL, userN, pass);
            String sql = "SELECT * FROM movies";
 
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
 
            while (rs.next()) {
                int movie_id = rs.getInt("Movie_id");
                String title = rs.getString("Title");
                String genre = rs.getString("Genre");
                String release_year = rs.getString("Release_Year");
                String director = rs.getString("Director");
                String rating = rs.getString("Rating");
                movies.add(new ViewMovies(movie_id, title, genre, release_year, director, rating));
            }
        } finally {
            // Close resources
        }
        return movies;
    }
    
 public List<movies> getLogin() throws ClassNotFoundException, SQLException{
    	
    	List<movies> log = new ArrayList();
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbURL, userN, pass);
    	String sql = "SELECT * FROM movies";
    	  PreparedStatement st = con.prepareStatement(sql);
    	  
    	  ResultSet rs = st.executeQuery();
    	  
    	  while(rs.next()) {
    		  String tit = rs.getString("title");
    		  String genr = rs.getString("genre");
    		  
    		  log.add(new movies(tit,genr));
    	  }
    		
    	}
    	
    	finally {
    		
    		
    	}
    	return log;
    	
    }
 public void addRental(int movie_id, String title, String genre, String release_year, String director, String rating, int user_id) throws SQLException, ClassNotFoundException {
     Connection con = null;
     PreparedStatement st = null;

     try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         con = DriverManager.getConnection(dbURL, userN, pass);
         String sql = "INSERT INTO rentals (Movie_id, Title, Genre, Release_Year, Director, Rating, User_id, Rental_date) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
         st = con.prepareStatement(sql);
         st.setInt(1, movie_id);
         st.setString(2, title);
         st.setString(3, genre);
         st.setString(4, release_year);
         st.setString(5, director);
         st.setString(6, rating);
         st.setInt(7, user_id);
         st.executeUpdate();
     } finally {
         if (st != null) try { st.close(); } catch (SQLException ignore) {}
         if (con != null) try { con.close(); } catch (SQLException ignore) {}
     }
 }
 
/* public List<ViewMovies> searchMovies(String title) throws SQLException, ClassNotFoundException {
     List<ViewMovies> movies = new ArrayList<>();

     try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection con = DriverManager.getConnection(dbURL, userN, pass);
         String sql = "SELECT * FROM movies where title=?";

         PreparedStatement st = con.prepareStatement(sql);
         st.setString(1, title);
         ResultSet rs = st.executeQuery();

         while (rs.next()) {
             int movie_id = rs.getInt("Movie_id");
             String title1 = rs.getString("Title");
             String genre = rs.getString("Genre");
             String release_year = rs.getString("Release_Year");
             String director = rs.getString("Director");
             String rating = rs.getString("Rating");
             movies.add(new ViewMovies(movie_id, title1, genre, release_year, director, rating));
         }
     } finally {
         // Close resources
     }
     return movies;
 } */
 
 
 
 public void deleteMovie(int id) throws SQLException, ClassNotFoundException{
	 
     try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection con = DriverManager.getConnection(dbURL, userN, pass);
 	String sql = "DELETE FROM movies WHERE id =?";
 	  PreparedStatement st = con.prepareStatement(sql);
 	  st.setInt(1, id);
 	  st.executeUpdate();
 }
     finally {
     }


 }

	public List<ViewMovies> searchUsers(String title) {
		// TODO Auto-generated method stub
		return null;
	}
}
