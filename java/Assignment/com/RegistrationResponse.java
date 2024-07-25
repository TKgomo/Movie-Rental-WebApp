package Assignment.com;

public class RegistrationResponse {
String username, password, email,full_name,is_admin;

 
public RegistrationResponse(String username, String password, String email, String full_name, String is_admin) {
	
	 this.username = username;
	 this.password = password;
	 this.email = email;
	 this.full_name = full_name;
	 this.is_admin = is_admin;

}

public String getUsername() {
	 return username;
	
}
public String getPassword() {
	 return password;
}	

public String getEmail() {
		 return email;
	
}

public String getFull_name() {
	 return full_name;
}
public String getIs_admin() {
	 return is_admin;
	
}
}