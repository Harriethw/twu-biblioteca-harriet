package com.twu.biblioteca.bookrepo;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private static List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }
}
