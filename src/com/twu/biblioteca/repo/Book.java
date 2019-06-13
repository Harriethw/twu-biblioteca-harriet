package com.twu.biblioteca.repo;

import java.time.Year;

public class Book extends Lendable {

    private String author;
    private String isbn;

    public Book(String title, String author, String isbn, Year year) {
        super.setTitle(title);
        this.author = author;
        this.isbn = isbn;
        super.setYear(year);
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

}
