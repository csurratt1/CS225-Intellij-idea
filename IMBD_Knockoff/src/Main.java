import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Actor> actors = new ArrayList<>();
    private static List<Movie> movies = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeData();

        while (true) {
            displayMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    enterActorName();
                    break;
                case "2":
                    enterMovieTitle();
                    break;
                case "3":
                    displayAllActors();
                    break;
                case "4":
                    displayAllMovies();
                    break;
                case "q":
                    System.out.println("Thank you for using IMDB Knockoff. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void initializeData() {
        // Initialize with at least 4 movies and 3 actors per movie
        Actor actor1 = new Actor("Tom Hanks");
        Actor actor2 = new Actor("Meryl Streep");
        Actor actor3 = new Actor("Leonardo DiCaprio");
        Actor actor4 = new Actor("Emma Stone");
        Actor actor5 = new Actor("Denzel Washington");

        Movie movie1 = new Movie("Forrest Gump");
        Movie movie2 = new Movie("The Post");
        Movie movie3 = new Movie("Inception");
        Movie movie4 = new Movie("La La Land");

        addActorToMovie(actor1, movie1);
        addActorToMovie(actor2, movie1);
        addActorToMovie(actor3, movie1);

        addActorToMovie(actor1, movie2);
        addActorToMovie(actor2, movie2);
        addActorToMovie(actor5, movie2);

        addActorToMovie(actor3, movie3);
        addActorToMovie(actor4, movie3);
        addActorToMovie(actor5, movie3);

        addActorToMovie(actor4, movie4);
        addActorToMovie(actor1, movie4);
        addActorToMovie(actor2, movie4);

        actors.addAll(Arrays.asList(actor1, actor2, actor3, actor4, actor5));
        movies.addAll(Arrays.asList(movie1, movie2, movie3, movie4));
    }

    private static void addActorToMovie(Actor actor, Movie movie) {
        actor.addMovie(movie);
        movie.addActor(actor);
    }

    private static void displayMenu() {
        System.out.println("\nIMDB Knockoff Menu:");
        System.out.println("1. Enter an Actor's Full Name");
        System.out.println("2. Enter a Movie Title");
        System.out.println("3. Display All Actors");
        System.out.println("4. Display All Movies");
        System.out.println("q. Quit");
        System.out.print("Enter your choice: ");
    }

    private static void enterActorName() {
        System.out.print("Enter actor's full name: ");
        String name = scanner.nextLine();
        Actor actor = findActor(name);
        if (actor != null) {
            System.out.println("Movies " + name + " has been in:");
            for (Movie movie : actor.getMovies()) {
                System.out.println("- " + movie.getTitle());
            }
        } else {
            System.out.println("Actor not found.");
        }
    }

    private static void enterMovieTitle() {
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        Movie movie = findMovie(title);
        if (movie != null) {
            System.out.println("Cast of " + title + ":");
            for (Actor actor : movie.getActors()) {
                System.out.println("- " + actor.getName());
            }
        } else {
            System.out.println("Movie not found.");
        }
    }

    private static void displayAllActors() {
        System.out.println("All Actors:");
        for (Actor actor : actors) {
            System.out.println(actor.getName() + ":");
            for (Movie movie : actor.getMovies()) {
                System.out.println("  - " + movie.getTitle());
            }
        }
    }

    private static void displayAllMovies() {
        System.out.println("All Movies:");
        for (Movie movie : movies) {
            System.out.println(movie.getTitle() + ":");
            for (Actor actor : movie.getActors()) {
                System.out.println("  - " + actor.getName());
            }
        }
    }

    private static Actor findActor(String name) {
        for (Actor actor : actors) {
            if (actor.getName().equalsIgnoreCase(name)) {
                return actor;
            }
        }
        return null;
    }

    private static Movie findMovie(String title) {
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }
        return null;
    }
}


