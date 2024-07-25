package Assignment.com;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
@Path("/register")
public class UserResourceAss {
 
	RegistrationServlet db = new RegistrationServlet();
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response registerUser(
		@FormParam("username") String username,
		@FormParam("password") String password,
		@FormParam("email") String email,
		@FormParam("full_name") String full_name,
		@FormParam("is_admin") String is_admin ){
		try {
			RegistrationResponse users = new RegistrationResponse(username,password,email,full_name,is_admin);
			db.CreateUsers(users);
			return Response.ok("Registration successful").build();
		}
		catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST). entity("Registration failed " + e.getMessage()).build();
		}
	}
}