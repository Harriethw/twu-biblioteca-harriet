package com.twu.biblioteca.model;

import java.time.Year;

public class Lendable {

    private String title;
    private Year year;

    public String getTitle() {
        return title;
    }

    public Year getYear() {
        return year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(Year year) {
        this.year = year;
    }
}
