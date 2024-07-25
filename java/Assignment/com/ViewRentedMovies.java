package Assignment.com;

public class ViewRentedMovies {
	String title;
	String genre;
	String release_year;
	String director;
	String rating;
	String rental_date;
	String return_date;
	
	int movie_id;
	int rental_id;
	int user_id;

	public ViewRentedMovies(int movie_id,String title, String genre, String release_year, String director,String rating,String rental_date,	String return_date,int rental_id,int user_id) {
		this.title = title;
		this.genre = genre;
		this.release_year = release_year;
		this.director = director;
		this.rating = rating;
		this.movie_id = movie_id;
		this.rental_id =rental_id;
		this.user_id =user_id;
		this.rental_date=rental_date;
		this.return_date=return_date;
		
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public String getRealese_Year() {
		return release_year;
	}
	
	public String getDirector() {
		return director;
	}
	
	public String getRating() {
		return rating;
	}
	
	public String getRental_date() {
		return rental_date;
	}
	
	public String getReturn_date() {
		return return_date;
	}
		
	public int getUser_id() {
		return user_id;
	}
	
	public int getMovie_id() {
		return movie_id;
	}
	
	public int getRental_id() {
		return rental_id;
	}
	
	

}
