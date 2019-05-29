package com.twu.biblioteca.bookrepo;

public class Book {

    private String title;
    private String author;
    private String isbn;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return title + ", " + author + ", " + isbn;
    }

    public String getAuthor() {
        return author;
    }

}
