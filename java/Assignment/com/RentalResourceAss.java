package Assignment.com;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rent")
public class RentalResourceAss {
	RentMovieServlet db = new RentMovieServlet();
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response registerUser(
			@FormParam("user_id") int user_id,
		@FormParam("movie_id") String movie_id,
		@FormParam("title") String title,
		@FormParam("genre") String genre,
		@FormParam("release_year") String release_year,
		@FormParam("director") String director,
		@FormParam("rating") String rating,
		@FormParam("rental_date") String rental_date,
	    @FormParam("return_date") String return_date ){
		try {
			RentalResponse rentals = new RentalResponse(user_id,movie_id,title,genre,director,release_year,rating,rental_date,return_date);
			db.CreateRental(rentals);
			return Response.ok("Rental successful").build();
		}
		catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST). entity("Rental failed " + e.getMessage()).build();
		}
	}

}
