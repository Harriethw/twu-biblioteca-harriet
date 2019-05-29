package com.twu.biblioteca.userinterface;

import com.twu.biblioteca.bookrepo.Book;
import com.twu.biblioteca.bookrepo.BookRepository;

public class UserInterface {

    private BookRepository bookRepository;

    public UserInterface(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void welcomeMessage() {
        System.out.println("Welcome to Biblioteca! Your one-stop-shop for great book titles in Bangalore!");
    }

    public void displayBooks() {
        for (Book book :
                bookRepository.getBooks()) {
            System.out.println(book.toString());
        }
    }
}
