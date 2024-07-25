package Assignment.com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Path("/users")
public class LoginResponse {
    static String name;
    static String word;
    public LoginServlet userData = new LoginServlet();

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getUsers() {
        try {
            List<ViewUser> users = userData.getAllUsers();
            return buildUsersHtml(users);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving users info: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response Login(
        @FormParam("username") String username,
        @FormParam("password") String password,
        @Context HttpServletResponse response) {
    	String usern = "";
    	String passw ="";
    	try {
    		List<users> logi = userData.getLogin();
    		
    		for(users log : logi) {
    		usern = log.getUsername();
    			 passw = log.getPassword();		
    		}
    		if (username.equals(usern) && password.equals(passw)) {
    		response.sendRedirect("/Assignment/Main.html");
    		return Response.ok().build();
    		}
    		
    		else {
    			return Response.ok().entity("Incorrect username or password : ").build();
    			
    		}
    	}
    	catch (Exception e) {
    		 return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                     .entity("Error collecting user details: " + e.getMessage())
                     .build();
    	}
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response deleteUser(@PathParam("id") int id) {
        try {
            userData.deleteUser(id);
            List<ViewUser> users = userData.getAllUsers();
            return buildUsersHtml(users);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error deleting user info: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/search")
    @Produces(MediaType.TEXT_HTML)
    public Response searchUsers(@QueryParam("name") String name) {
        try {
            List<ViewUser> users = userData.searchUsers(name);
            return buildUsersHtml(users);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error searching users: " + e.getMessage())
                    .build();
        }
    }

    private Response buildUsersHtml(List<ViewUser> users) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html><body>");
        htmlBuilder.append("<center><h1>Registered users</h1><hr color='red'>");
        htmlBuilder.append("<div>");
        htmlBuilder.append("<input type='text' id='myInput' onkeyup='myFunction()' placeholder='Search by name'>");
        htmlBuilder.append("</div>");
        htmlBuilder.append("<table border=1 id='myTable'>");
        htmlBuilder.append("<thead>");
        htmlBuilder.append("<tr><th>User ID</th><th>Username</th><th>Email</th><th>Full Name</th><th>Is Admin</th><th>Action</th></tr>");
        htmlBuilder.append("</thead>");
        htmlBuilder.append("<tbody id='user-table-body'>");

        for (ViewUser user : users) {
            htmlBuilder.append("<tr><td>").append(user.getID())
                    .append("</td><td>").append(user.getUsername())
                    .append("</td><td>").append(user.getEmail())
                    .append("</td><td>").append(user.getFull_name())
                    .append("</td><td>").append(user.getIs_admin())
                    .append("</td><td><a href='/Assignment/users/").append(user.getID()).append("'>Delete</a> | ")
                    .append("<a href='/Assignment/users/edit/").append(user.getID()).append("'>Edit</a></td></tr>");
        }

        htmlBuilder.append("</tbody>");
        htmlBuilder.append("</table></center>");

        htmlBuilder.append("<script>")
                .append("function myFunction() {")
                .append("var input, filter, table, tr, td, i, txtValue;")
                .append("input = document.getElementById('myInput');")
                .append("filter = input.value.toUpperCase();")
                .append("table = document.getElementById('myTable');")
                .append("tr = table.getElementsByTagName('tr');")
                .append("for (i = 0; i < tr.length; i++) {")
                .append("td = tr[i].getElementsByTagName('td')[1];")
                .append("if (td) {")
                .append("txtValue = td.textContent || td.innerText;")
                .append("if (txtValue.toUpperCase().indexOf(filter) > -1) {")
                .append("tr[i].style.display = '';")
                .append("} else {")
                .append("tr[i].style.display = 'none';")
                .append("}}}}")
                .append("</script>");
        htmlBuilder.append("</body></html>");
        return Response.ok(htmlBuilder.toString()).build();
    }
}
