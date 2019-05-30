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
        System.out.println("Enter 2 to check out a book");
        System.out.println("Enter 3 to return a book");
        System.out.println("Enter q to quit the application");
        input = scanner.next();
        switch (input) {
            case "1":
                displayBooks();
                menu();
            case "2":
                checkOutBook();
                menu();
            case "3":
                returnBook();
                menu();
            case "q":
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                System.out.println("Please select a valid option!");
                menu();
        }
    }

    private void returnBook() {
        String input;
        System.out.println("What is the ISBN of the book you would like to check out?");
        input = scanner.next();
        bookRepository.returnBook(input);
    }

    private void checkOutBook() {
        String input;
        System.out.println("What is the ISBN of the book you would like to check out?");
        input = scanner.next();
        System.out.println(bookRepository.checkOutBook(input) ? "Thank you! Enjoy the book." : "Sorry, that book is not available.");
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
