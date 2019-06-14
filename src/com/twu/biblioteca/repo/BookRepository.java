package com.twu.biblioteca.repo;

import com.twu.biblioteca.model.Book;

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

    public boolean checkOutBook(String isbn, String userID) {
        Book chosenBook = availableBooks.stream().filter(book -> book.getIsbn().equals(isbn))
                .findFirst().orElse(null);
        if (chosenBook != null) {
            chosenBook.setCurrentUserId(userID);
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

    public boolean returnBook(String isbn) {
        Book returnBook = checkedOutBooks.stream().filter(book -> book.getIsbn().equals(isbn))
                .findFirst().orElse(null);
        if (returnBook != null) {
            returnBook.setCurrentUserId(null);
            availableBooks.add(returnBook);
            checkedOutBooks.remove(returnBook);
            return true;
        } else {
            return false;
        }
    }
}
