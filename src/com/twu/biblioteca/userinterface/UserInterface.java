package com.twu.biblioteca.userinterface;

import com.twu.biblioteca.bookrepo.Book;
import com.twu.biblioteca.bookrepo.BookRepository;

import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;

    private BookRepository bookRepository;

    public UserInterface(BookRepository bookRepository, Scanner scanner) {
        this.bookRepository = bookRepository;
        this.scanner = scanner;
    }

    public void welcomeMessage() {
        System.out.println("Welcome to Biblioteca! Your one-stop-shop for great book titles in Bangalore!");
    }

    public void menu() {
        String input;
        System.out.println("What would you like to do?");
        System.out.println("Enter 1 to see the list of books");
        System.out.println("Enter 2 to quit the application");
        System.out.println("Enter 3 to check out a book");
        input = scanner.next();
        if (input.equals("1")) {
            displayBooks();
            menu();
        } else if (input.equals("2")) {
            System.out.println("Goodbye!");
            System.exit(0);
        } else if (input.equals("3")) {
            checkOutBook();
            menu();
        } else {
            System.out.println("Please select a valid option!");
            menu();
        }
    }

    private void checkOutBook() {
        String input;
        System.out.println("What is the ISBN of the book you would like to check out?");
        input = scanner.next();
        bookRepository.checkOutBook(input);
        System.out.println("Thank you! Enjoy the book.");
    }

    public void displayBooks() {
        System.out.printf("%-30s%-30s%-30s%-30s%n", "** Title **", "** Author **", "** Year **", "** ISBN **");
        for (Book book :
                bookRepository.getAvailableBooks()) {
            System.out.printf("%-20s%-10s%-20s%-10s%-20s%-10s%-20s%-10s%n", book.getTitle(), "|",
                    book.getAuthor(), "|", book.getYear(), "|", book.getIsbn(), "|");
        }
    }

}
