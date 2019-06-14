package com.twu.biblioteca.repo;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    private static List<Movie> availableMovies = new ArrayList<>();

    public void addMovie(Movie movie) {
        availableMovies.add(movie);
    }

    public List<Movie> getAvailableMovies() {
        return availableMovies;
    }
}
