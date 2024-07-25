package Assignment.com;

public class ViewMovies {
	String title;
	String genre;
	String release_year;
	String director;
	String rating;
	
	int movie_id;
	
	public ViewMovies(int movie_id,String title, String genre, String release_year, String director,String rating) {
		this.title = title;
		this.genre = genre;
		this.release_year = release_year;
		this.director = director;
		this.rating = rating;
		this.movie_id = movie_id;
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
	
	public int getMovie_id() {
		return movie_id;
	}

}
