package com.twu.biblioteca.repo;

import java.time.Year;

public class Movie extends Lendable {

    private String director;
    private int rating;

    public Movie(String title, String director, Year year, int rating) {
        super.setTitle(title);
        this.director = director;
        this.rating = rating;
        super.setYear(year);
    }

    public String getDirector() {
        return director;
    }

    public int getRating() {
        return rating;
    }
}
