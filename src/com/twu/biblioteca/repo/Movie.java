package com.twu.biblioteca.repo;

import java.time.Year;

public class Movie extends Lendable {

    private String director;

    public Movie(String title, String director, Year year) {
        super.setTitle(title);
        this.director = director;
        super.setYear(year);
    }

    public String getDirector() {
        return director;
    }

}
