package Assignment.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
public class RegistrationServlet {
 
	static String dbURL = "jdbc:mysql://localhost:3306/movie_rental_platform";
	static String userN = "root";
	static String pass= "";
	public void CreateUsers(RegistrationResponse users) throws SQLException, ClassNotFoundException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(dbURL,userN,pass);
			String sql ="INSERT INTO users (username,password,email,full_name,is_admin)VALUES(?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setNString(1, users.getUsername());
			ps.setString(2, users.getPassword());
			ps.setString(3,users.getEmail());
			ps.setString(4, users.getFull_name());
			ps.setString(5, users.getIs_admin());
			ps.executeUpdate();
		}
		finally {
		}
	}
	public void CreateUser(RegistrationResponse users) {
		// TODO Auto-generated method stub
		
	}
}