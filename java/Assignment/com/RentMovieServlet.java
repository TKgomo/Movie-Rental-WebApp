package Assignment.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.Path;


public class RentMovieServlet {
	static String dbURL = "jdbc:mysql://localhost:3306/movie_rental_platform";
	static String userN = "root";
	static String pass= "";
	
	public void CreateRental(RentalResponse rentals) throws SQLException, ClassNotFoundException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(dbURL,userN,pass);
			String sql ="INSERT INTO rentals (user_id,movie_id,title,genre,release_year,director,rating,rental_date,return_date)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, rentals.getUser_id());
			ps.setNString(2, rentals.getMovie_id());
			ps.setString(3, rentals.getTitle());
			ps.setString(4,rentals.getGenre());
			ps.setString(5, rentals.getRelease_year());
			ps.setString(6, rentals.getDirector());
			ps.setString(7, rentals.getRating());
			ps.setString(8, rentals.getRental_date());
			ps.setString(9, rentals.getReturn_date());
			ps.executeUpdate();
		}
		finally {
		}
	}

}
