import java.util.HashSet;
import java.util.Set;

public class Actor {

    private String name;
    private Set<Movie> movies;

    public Actor(String name) {
        this.name = name;
        this.movies = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public Set<Movie> getMovies() {
        return movies;
    }
}

