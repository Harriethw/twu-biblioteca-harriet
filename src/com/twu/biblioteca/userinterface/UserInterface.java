package com.twu.biblioteca.userinterface;

import com.twu.biblioteca.repo.Book;
import com.twu.biblioteca.repo.Repository;

import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;

    private Repository repository;

    public UserInterface(Repository repository, Scanner scanner) {
        this.repository = repository;
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
        handleMenuInput(input);
        menu();
    }

    private void handleMenuInput(String input) {
        switch (input) {
            case "1":
                displayBooks();
                break;
            case "2":
                checkOutBook();
                break;
            case "3":
                returnBook();
                break;
            case "q":
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                System.out.println("Please select a valid option!");
                break;
        }
    }

    private void returnBook() {
        String input;
        System.out.println("What is the ISBN of the book you would like to check out?");
        input = scanner.next();
        System.out.println(repository.returnBook(input) ? "Thank you for returning the book." : "That is not a valid book to return.");
    }

    private void checkOutBook() {
        String input;
        System.out.println("What is the ISBN of the book you would like to check out?");
        input = scanner.next();
        System.out.println(repository.checkOutBook(input) ? "Thank you! Enjoy the book." : "Sorry, that book is not available.");
    }

    public void displayBooks() {
        System.out.printf("%-30s%-30s%-30s%-30s%n", "** Title **", "** Author **", "** Year **", "** ISBN **");
        for (Book book :
                repository.getAvailableBooks()) {
            System.out.printf("%-20s%-10s%-20s%-10s%-20s%-10s%-20s%-10s%n", book.getTitle(), "|",
                    book.getAuthor(), "|", book.getYear(), "|", book.getIsbn(), "|");
        }
    }

}
