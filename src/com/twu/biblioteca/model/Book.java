package com.twu.biblioteca.model;

import java.time.Year;

public class Book extends Lendable {

    private String author;
    private String isbn;
    private String currentUserId;

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

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }
}
