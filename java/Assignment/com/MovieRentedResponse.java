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

@Path("/rentals")
public class MovieRentedResponse {
	static String title;
    static String word;
    public ViewAllRentedMovies userData = new ViewAllRentedMovies();

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getAllMovies() {
        try {
            List<ViewRentedMovies> rentals = userData.getAllUsers();
            return buildMovieRentalHtml(rentals);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving movie info: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/rentals")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response Login(
       
        @Context HttpServletResponse response) {
    	
    	try {
    		{
    		response.sendRedirect("/Assignment/rentals/rentals");
    		return Response.ok().build();
    		}
    		
  
    	}
    	catch (Exception e) {
    		 return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                     .entity("Error collecting movie details: " + e.getMessage())
                     .build();
    	}
    }
    
    @Path("/{user_id}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response DeleteUser(@PathParam("user_id") int id ) {
    	try {
    		userData.deleteUser(id);
    		List<ViewRentedMovies> rentals = userData.getAllUsers();
            return buildMovieRentalHtml(rentals);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error deleting a user rental info: " + e.getMessage())
                    .build();
    	}
    }
    

    private Response buildMovieRentalHtml(List<ViewRentedMovies> rentals) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html><head>");
        htmlBuilder.append("<style>");
        htmlBuilder.append("body {font-family: Arial, sans-serif;}");
        htmlBuilder.append("h1 {color: #333;}");
        htmlBuilder.append("table {width: 100%; border-collapse: collapse; margin: 20px 0;}");
        htmlBuilder.append("th, td {border: 1px solid #ddd; padding: 8px; text-align: left;}");
        htmlBuilder.append("th {background-color: #f2f2f2;}");
        htmlBuilder.append("tr:hover {background-color: #f1f1f1;}");
        htmlBuilder.append("input[type='text'] {width: 100%; padding: 12px 20px; margin: 8px 0; box-sizing: border-box;}");
        htmlBuilder.append("a {color: #ff0000; text-decoration: none;}");
        htmlBuilder.append("a:hover {text-decoration: underline;}");
        htmlBuilder.append("</style>");
        htmlBuilder.append("</head><body>");
        
        htmlBuilder.append("<center><h1>Movies Rented</h1><hr color='red'>");
        htmlBuilder.append("<div>");
        htmlBuilder.append("<input type='text' id='myInput' onkeyup='myFunction()' placeholder='Search by Title'>");
        htmlBuilder.append("</div>");
        htmlBuilder.append("<table border=1 id='myTable'>");
        htmlBuilder.append("<thead>");
        htmlBuilder.append("<tr><th>Rental ID</th><th>User ID</th><th>Movie ID</th><th>Title</th><th>Genre</th><th>Release Year</th><th>Director</th><th>Rating</th><th>Rental Date</th><th>Return Date</th><th>Action</th></tr>");
        htmlBuilder.append("</thead>");
        htmlBuilder.append("<tbody id='user-table-body'>");
        
        for (ViewRentedMovies mov : rentals) {
            htmlBuilder.append("<tr>");
            htmlBuilder.append("<td>").append(mov.getRental_id()).append("</td>");
            htmlBuilder.append("<td>").append(mov.getUser_id()).append("</td>");
            htmlBuilder.append("<td>").append(mov.getMovie_id()).append("</td>");
            htmlBuilder.append("<td>").append(mov.getTitle()).append("</td>");
            htmlBuilder.append("<td>").append(mov.getGenre()).append("</td>");
            htmlBuilder.append("<td>").append(mov.getRealese_Year()).append("</td>");
            htmlBuilder.append("<td>").append(mov.getDirector()).append("</td>");
            htmlBuilder.append("<td>").append(mov.getRating()).append("</td>");
            htmlBuilder.append("<td>").append(mov.getRental_date()).append("</td>");
            htmlBuilder.append("<td>").append(mov.getReturn_date()).append("</td>");
            htmlBuilder.append("<td><a href='/Assignment/rentals/rentals/").append(mov.getUser_id()).append("'>Delete</a></td>");
            htmlBuilder.append("</tr>");
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
