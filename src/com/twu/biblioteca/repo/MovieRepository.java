package com.twu.biblioteca.repo;

import com.twu.biblioteca.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    private static List<Movie> availableMovies = new ArrayList<>();
    private static List<Movie> checkedOutMovies = new ArrayList<>();

    public void addMovie(Movie movie) {
        availableMovies.add(movie);
    }

    public List<Movie> getAvailableMovies() {
        return availableMovies;
    }

    public boolean checkOutMovie(String title) {
        Movie chosenMovie = availableMovies.stream().filter(movie -> movie.getTitle().equals(title))
                .findFirst().orElse(null);
        if (chosenMovie != null) {
            availableMovies.remove(chosenMovie);
            checkedOutMovies.add(chosenMovie);
            return true;
        } else {
            return false;
        }
    }

    public List<Movie> getCheckedOutMovies() {
        return checkedOutMovies;
    }
}
