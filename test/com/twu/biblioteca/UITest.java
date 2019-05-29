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

import static org.junit.Assert.assertThat;

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
        mockBooks.add(new Book("Winnie the Pooh", "AA Milne", "1234", Year.of(1666)));
        Mockito.when(bookRepository.getBooks()).thenReturn(mockBooks);
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
        assertThat(getOutput(), containsString(bookRepository.getBooks().get(0).getAuthor()));
        assertThat(getOutput(), containsString(bookRepository.getBooks().get(0).getYear().toString()));
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
        userInterface.menu();
        //then I should see the books displayed
        assertThat(getOutput(), containsString(bookRepository.getBooks().get(0).getAuthor()));
        assertThat(getOutput(), containsString(bookRepository.getBooks().get(0).getYear().toString()));
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
        userInterface.menu();
        //then I see an error message
        assertThat(getOutput(), containsString("Sorry"));
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
}
