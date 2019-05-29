package com.twu.biblioteca;

import com.twu.biblioteca.bookrepo.Book;
import com.twu.biblioteca.bookrepo.BookRepository;
import com.twu.biblioteca.userinterface.UserInterface;

import java.time.Year;

public class BibliotecaApp {

    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        bookRepository.addBook(new Book("Jane Eyre", "Charlotte Bronte", "1234", Year.of(1745)));
        bookRepository.addBook(new Book("Orlando", "Virginia Woolf", "1235", Year.of(1985)));
        bookRepository.addBook(new Book("Frankenstein", "Mary Shelley", "126", Year.of(1995)));
        UserInterface userInterface = new UserInterface(bookRepository);
        userInterface.welcomeMessage();
        userInterface.displayBooks();
    }
}
