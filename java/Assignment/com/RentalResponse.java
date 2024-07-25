package Assignment.com;

public class RentalResponse {
String movie_id, title, genre,release_year,director,rating,rental_date,return_date;
int user_id;

 
public RentalResponse(int user_id,String movie_id, String title, String genre, String release_year,String director,String rating,String rental_date,String return_date) {
	
	 this.movie_id = movie_id;
	 this.title = title;
	 this.genre = genre;
	 this.release_year = release_year;
	 this.director = director;
	 this.rating = rating;
	 this.user_id = user_id;
	 this.rental_date = rental_date;
	 this.return_date =return_date;

}

public int getUser_id() {
	return user_id;
}

public String getMovie_id() {
	 return movie_id;
	
}
public String getTitle() {
	 return title;
}	

public String getGenre() {
		 return genre;
	
}

public String getRelease_year() {
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


}