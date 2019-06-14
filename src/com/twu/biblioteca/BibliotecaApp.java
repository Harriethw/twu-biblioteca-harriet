package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repo.BookRepository;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.repo.MovieRepository;
import com.twu.biblioteca.repo.UserRepository;
import com.twu.biblioteca.userinterface.UserInterface;

import java.time.Year;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        MovieRepository movieRepository = new MovieRepository();
        UserRepository userRepository = new UserRepository();
        bookRepository.addBook(new Book("Jane Eyre", "Charlotte Bronte", "1234", Year.of(1745)));
        bookRepository.addBook(new Book("Orlando", "Virginia Woolf", "1235", Year.of(1985)));
        bookRepository.addBook(new Book("Frankenstein", "Mary Shelley", "126", Year.of(1995)));
        movieRepository.addMovie(new Movie("Casablanca", "a director", Year.of(1954), 7));
        movieRepository.addMovie(new Movie("Java: The Movie", "Oracle", Year.of(1969), 1));
        movieRepository.addMovie(new Movie("Hackers", "Angelina Jolie", Year.of(1996), 7));
        userRepository.addUser(new User("1234", "password", "Suzy",
                "suzy@suzy.com", "444-67896312"));
        userRepository.addUser(new User("5678", "password", "Bob",
                "bob@bob.com", "555-6789634532"));
        userRepository.addUser(new User("666", "password", "Lucy",
                "lll@gggg.com", "555-6456476345652"));
        UserInterface userInterface = new UserInterface(bookRepository, movieRepository, userRepository,
                new Scanner(System.in));
        userInterface.welcomeMessage();
        userInterface.login();
    }
}
