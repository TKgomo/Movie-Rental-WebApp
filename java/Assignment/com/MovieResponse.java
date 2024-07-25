package Assignment.com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Path("/movies")
public class MovieResponse {
    static String title;
    static String word;
    public ViewAllServlet userData = new ViewAllServlet();

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getMovie() {
        try {
            List<ViewMovies> movies = userData.getAllUsers();
            return buildMovieHtml(movies);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving movie info: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/movies")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response Login(@Context HttpServletResponse response) {
        try {
            response.sendRedirect("/Assignment/ViewMovies/movies/");
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error collecting movie details: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/movies/rent")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response rentMovie(
        @FormParam("movie_id") int movie_id,
        @FormParam("title") String title,
        @FormParam("genre") String genre,
        @FormParam("release_year") String release_year,
        @FormParam("director") String director,
        @FormParam("rating") String rating,
        @FormParam("user_id") int user_id,
        @Context HttpServletResponse response
    ) {
        try {
            ViewAllServlet userData = new ViewAllServlet();
            userData.addRental(movie_id, title, genre, release_year, director, rating, user_id);
            response.sendRedirect("/Assignment/rentals/");
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error renting movie: " + e.getMessage())
                    .build();
        }
    }
    

  

	private Response buildMovieHtml(List<ViewMovies> movies) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html><head>");
        htmlBuilder.append("<style>");
        htmlBuilder.append("body {font-family: Arial, sans-serif; background-color: #f2f2f2;}");
        htmlBuilder.append("h1 {color: #333;}");
        htmlBuilder.append(".container {display: flex; justify-content: center; align-items: center; height: 110vh;}");
        htmlBuilder.append("table {width: 100%; border-collapse: collapse; margin: 20px 0;}");
        htmlBuilder.append("th, td {border: 1px solid #ddd; padding: 8px; text-align: left;}");
        htmlBuilder.append("th {background-color: #f2f2f2;}");
        htmlBuilder.append("tr:hover {background-color: #f1f1f1;}");
        htmlBuilder.append("input[type='text'] {width: 100%; padding: 12px 20px; margin: 8px 0; box-sizing: border-box;}");
        htmlBuilder.append("input[type='submit'] {background-color: #4CAF50; color: white; border: none; cursor: pointer;}");
        htmlBuilder.append("input[type='submit']:hover {background-color: #45a049;}");
        htmlBuilder.append("form {background-color: #f2f2f2; padding: 1px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);}");
        htmlBuilder.append("p {margin: 3px 0;}");
        htmlBuilder.append("a {color: #ff0000; text-decoration: none;}");
        htmlBuilder.append("a:hover {text-decoration: underline;}");
        htmlBuilder.append("</style>");
        htmlBuilder.append("</head><body>");

        htmlBuilder.append("<center><h1>Registered Movies</h1><hr color='red'>");
        htmlBuilder.append("<div>");
        htmlBuilder.append("<input type='text' id='myInput' onkeyup='myFunction()' placeholder='Search by name'>");
        htmlBuilder.append("</div>");
        htmlBuilder.append("<table border=1 id='myTable'>");
        htmlBuilder.append("<thead>");
        htmlBuilder.append("<tr><th>Movie Id</th><th>Title</th><th>Genre</th><th>Release Year</th><th>Director</th><th>Rating</th></tr>");
        htmlBuilder.append("</thead>");
        htmlBuilder.append("<tbody id='user-table-body'>");

        for (ViewMovies user : movies) {
            htmlBuilder.append("<tr>");
            htmlBuilder.append("<td>").append(user.getMovie_id()).append("</td>");
            htmlBuilder.append("<td>").append(user.getTitle()).append("</td>");
            htmlBuilder.append("<td>").append(user.getGenre()).append("</td>");
            htmlBuilder.append("<td>").append(user.getRealese_Year()).append("</td>");
            htmlBuilder.append("<td>").append(user.getDirector()).append("</td>");
            htmlBuilder.append("<td>").append(user.getRating()).append("</td>");
            htmlBuilder.append("</td>");
            htmlBuilder.append("</tr>");
        }

        htmlBuilder.append("</tbody>");
        htmlBuilder.append("</table></center>");
        
        htmlBuilder.append("<div class='container'>");
        htmlBuilder.append("<form action='http://localhost:8080/Assignment/RentRegistration/rent' method='post'>");
        htmlBuilder.append("<h1>Rent A Movie</h1>");
        htmlBuilder.append("<p>User ID: <input type='text' name='user_id' required></p>");
        htmlBuilder.append("<p>Movie ID: <input type='text' name='movie_id' required></p>");
        htmlBuilder.append("<p>Genre: <input type='text' name='genre' required></p>");
        htmlBuilder.append("<p>Title: <input type='text' name='title' required></p>");
        htmlBuilder.append("<p>Release Year: <input type='text' name='release_year' required></p>");
        htmlBuilder.append("<p>Director: <input type='text' name='director' required></p>");
        htmlBuilder.append("<p>Rating: <input type='text' name='rating' required></p>");
        htmlBuilder.append("<p>Rental Date: <input type='text' name='rental_date' required></p>");
        htmlBuilder.append("<p>Return Date: <input type='text' name='return_date' required></p>");
        htmlBuilder.append("<p><input type='submit' name='submit' value='Rent'></p>");
        htmlBuilder.append("</form>");
        htmlBuilder.append("</div>");

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
