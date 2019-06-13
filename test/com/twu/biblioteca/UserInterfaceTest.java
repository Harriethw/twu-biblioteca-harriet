package com.twu.biblioteca;

import com.twu.biblioteca.repo.Book;
import com.twu.biblioteca.repo.Repository;
import com.twu.biblioteca.userinterface.UserInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.containsString;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class UserInterfaceTest {
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream testOut;

    @Mock
    private Repository repository;

    @InjectMocks
    private UserInterface userInterface;

    private ArrayList<Book> mockBooks = new ArrayList<>();
    private Book mockBook = new Book("Winnie the Pooh", "AA Milne", "1234", Year.of(1666));

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Before
    public void addBooks() {
        mockBooks.add(mockBook);
        Mockito.when(repository.getAvailableBooks()).thenReturn(mockBooks);
    }

    @After
    public void restoreSystemInputOutput() {
        System.setOut(systemOut);
    }

    @Test
    public void theOneWhereWeGetAWelcomeMessage() {
        userInterface.welcomeMessage();
        assertThat(getOutput(), containsString("Welcome"));
    }

    @Test
    public void theOneWhereAllTheBooksAreDisplayed() {
        userInterface.displayBooks();
        assertThat(getOutput(), containsString(repository.getAvailableBooks().get(0).getAuthor()));
        assertThat(getOutput(), containsString(repository.getAvailableBooks().get(0).getYear().toString()));
    }

    @Test
    public void theOneWhereTheMenuGivesOptionToBrowseCatalogue() {
        String input = "1 q";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(repository, mockScanner);
        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() -> {
            Mockito.verify(repository, times(1)).getAvailableBooks();
            assertThat(getOutput(), containsString(repository.getAvailableBooks().get(0).getAuthor()));
            assertThat(getOutput(), containsString(repository.getAvailableBooks().get(0).getYear().toString()));
        });
        userInterface.menu();
    }

    @Test
    public void theOneWhereAnUnrecognizedInputIsGivenInTheMenu() {
        String input = "z q";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(repository, mockScanner);
        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() -> assertThat(getOutput(), containsString("valid")));
        userInterface.menu();
    }

    @Test
    public void theOneWhereWeQuit() {
        String input = "q";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(repository, mockScanner);
        exit.expectSystemExit();
        userInterface.menu();
    }

    @Test
    public void theOneWhereWeCheckOutABookSuccessfully() {
        String input = "2 1234 q";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(repository, mockScanner);
        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() -> {
            Mockito.verify(repository, times(1)).checkOutBook("1234");
            assertFalse(repository.getCheckedOutBooks().contains(mockBook));
        });
        userInterface.menu();
    }

    @Test
    public void theOneWhereWeCheckOutABookUnsuccessfully() {
        String input = "2 1238 q";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(repository, mockScanner);
        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() -> {
            Mockito.verify(repository, times(1)).checkOutBook("1238");
            assertFalse(repository.getCheckedOutBooks().contains(mockBook));
            assertThat(getOutput(), containsString("Sorry, that book is not available."));
        });
        userInterface.menu();
    }

    @Test
    public void theOneWhereWeSuccessfullyReturnABook() {
        String input = "3 1234 q";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(repository, mockScanner);
        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() -> {
            Mockito.verify(repository, times(1)).returnBook("1234");
            assertFalse(repository.getCheckedOutBooks().contains(mockBook));
        });
        userInterface.menu();
    }

    @Test
    public void theOneWhereWeUnsuccessfullyReturnABook() {
        String input = "3 1274 q";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(repository, mockScanner);
        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() -> {
            Mockito.verify(repository, times(1)).returnBook("1274");
            assertThat(getOutput(), containsString("That is not a valid book to return."));
        });
        userInterface.menu();
    }
}
