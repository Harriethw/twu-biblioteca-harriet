package com.twu.biblioteca.bookrepo;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private static List<Book> availableBooks = new ArrayList<>();

    private static List<Book> checkedOutBooks = new ArrayList<>();

    public List<Book> getAvailableBooks() {
        return availableBooks;
    }

    public void addBook(Book book) {
        availableBooks.add(book);
    }

    public boolean checkOutBook(String isbn) {
        Book chosenBook = availableBooks.stream().filter(book -> book.getIsbn().equals(isbn))
                .findFirst().orElse(null);
        if (chosenBook != null) {
            availableBooks.remove(chosenBook);
            checkedOutBooks.add(chosenBook);
            return true;
        } else {
            return false;
        }
    }

    public List<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }
}
