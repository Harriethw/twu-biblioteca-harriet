package com.twu.biblioteca;

import com.twu.biblioteca.bookrepo.Book;
import com.twu.biblioteca.bookrepo.BookRepository;
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
public class UITest {
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream testOut;

    @Mock
    private BookRepository bookRepository;

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
        Mockito.when(bookRepository.getAvailableBooks()).thenReturn(mockBooks);
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
        //given there are books in the catalogue
        //when they are displayed
        userInterface.displayBooks();
        //then the author and year appear
        assertThat(getOutput(), containsString(bookRepository.getAvailableBooks().get(0).getAuthor()));
        assertThat(getOutput(), containsString(bookRepository.getAvailableBooks().get(0).getYear().toString()));
    }

    @Test
    public void theOneWhereTheMenuGivesOptionToBrowseCatalogue() {
        //given there are books in the catalogue and I have opened the app
        //when I press the option to see the books
        String input = "1 2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(bookRepository, mockScanner);
        exit.expectSystemExit();
        //then I should see the books displayed
        exit.checkAssertionAfterwards(() -> {
            Mockito.verify(bookRepository, times(1)).getAvailableBooks();
            assertThat(getOutput(), containsString(bookRepository.getAvailableBooks().get(0).getAuthor()));
            assertThat(getOutput(), containsString(bookRepository.getAvailableBooks().get(0).getYear().toString()));
        });
        userInterface.menu();
    }

    @Test
    public void theOneWhereAnUnrecognizedInputIsGivenInTheMenu() {
        //given I am on the menu of the app
        //when I press an incorrect button
        String input = "z 2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(bookRepository, mockScanner);
        exit.expectSystemExit();
        //then I see an error message
        exit.checkAssertionAfterwards(() -> assertThat(getOutput(), containsString("valid")));
        userInterface.menu();
    }

    @Test
    public void theOneWhereWeQuit() {
        //given I am on the menu of the app
        //when I press the option to quit
        String input = "2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(bookRepository, mockScanner);
        //then the app quits
        exit.expectSystemExit();
        userInterface.menu();
    }

    @Test
    public void theOneWhereWeCheckOutABookSuccessfully() {
        //given I am on the menu option to check out a book
        //And I enter an existing isbn
        String input = "3 1234 2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(bookRepository, mockScanner);
        exit.expectSystemExit();
        //then the book is checked out
        exit.checkAssertionAfterwards(() -> {
            Mockito.verify(bookRepository, times(1)).checkOutBook("1234");
            assertFalse(bookRepository.getCheckedOutBooks().contains(mockBook));
        });
        userInterface.menu();
    }

    @Test
    public void theOneWhereWeCheckOutABookUnsuccessfully() {
        //given I am on the menu option to check out a book
        //And I enter an existing isbn
        String input = "3 1238 2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(bookRepository, mockScanner);
        exit.expectSystemExit();
        //then the book is checked out
        exit.checkAssertionAfterwards(() -> {
            Mockito.verify(bookRepository, times(1)).checkOutBook("1238");
            assertFalse(bookRepository.getCheckedOutBooks().contains(mockBook));
            assertThat(getOutput(), containsString("Sorry, that book is not available."));
        });
        userInterface.menu();
    }
}
