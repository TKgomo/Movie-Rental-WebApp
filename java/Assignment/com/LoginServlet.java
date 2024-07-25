package Assignment.com;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LoginServlet {
	static String dbURL = "jdbc:mysql://localhost:3306/movie_rental_platform";
    static String userN = "root";
    static String pass = "";
    
    public List<ViewUser> getAllUsers() throws SQLException, ClassNotFoundException {
        List<ViewUser> users = new ArrayList<>();
 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbURL, userN, pass);
            String sql = "SELECT * FROM users";
 
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
 
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
                String full_name = rs.getString("full_name");
                String is_admin = rs.getString("is_admin");
                users.add(new ViewUser(userId, username, password, email, full_name, is_admin));
            }
        } finally {
            // Close resources
        }
        return users;
    }
    
 public List<users> getLogin() throws ClassNotFoundException, SQLException{
    	
    	List<users> log = new ArrayList();
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbURL, userN, pass);
    	String sql = "SELECT * FROM users";
    	  PreparedStatement st = con.prepareStatement(sql);
    	  
    	  ResultSet rs = st.executeQuery();
    	  
    	  while(rs.next()) {
    		  String usern = rs.getString("username");
    		  String pass = rs.getString("password");
    		  
    		  log.add(new users(usern,pass));
    	  }
    		
    	}
    	
    	finally {
    		
    		
    	}
    	return log;
    	
    }
 
 public void deleteUser(int id) throws SQLException, ClassNotFoundException{
	 
     try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection con = DriverManager.getConnection(dbURL, userN, pass);
 	String sql = "DELETE FROM users WHERE id =?";
 	  PreparedStatement st = con.prepareStatement(sql);
 	  st.setInt(1, id);
 	  st.executeUpdate();
 }
     finally {
     }


 }

	public List<ViewUser> searchUsers(String name) {
		// TODO Auto-generated method stub
		return null;
	}  

}
