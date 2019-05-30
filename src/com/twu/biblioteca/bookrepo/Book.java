package com.twu.biblioteca.bookrepo;

import java.time.Year;

public class Book {

    private String title;
    private String author;
    private String isbn;
    private Year year;

    public Book(String title, String author, String isbn, Year year) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public Year getYear() {
        return year;
    }
}
