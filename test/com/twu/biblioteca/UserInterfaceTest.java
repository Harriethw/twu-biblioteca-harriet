package com.twu.biblioteca;

import com.twu.biblioteca.repo.Book;
import com.twu.biblioteca.repo.BookRepository;
import com.twu.biblioteca.repo.Movie;
import com.twu.biblioteca.repo.MovieRepository;
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
    private BookRepository bookRepository;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private UserInterface userInterface;

    private ArrayList<Book> mockBooks = new ArrayList<>();
    private Book mockBook = new Book("Winnie the Pooh", "AA Milne", "1234", Year.of(1666));

    private ArrayList<Movie> mockMovies = new ArrayList<>();
    private Movie mockMovie = new Movie("Clueless", "Director Name", Year.of(1999), 10);

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

    @Before
    public void addMovies() {
        mockMovies.add(mockMovie);
        Mockito.when(movieRepository.getAvailableMovies()).thenReturn(mockMovies);
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
        assertThat(getOutput(), containsString(bookRepository.getAvailableBooks().get(0).getAuthor()));
        assertThat(getOutput(), containsString(bookRepository.getAvailableBooks().get(0).getYear().toString()));
    }

    @Test
    public void theOneWhereAllTheMoviesAreDisplayed() {
        userInterface.displayMovies();
        assertThat(getOutput(), containsString(movieRepository.getAvailableMovies().get(0).getDirector()));
        assertThat(getOutput(), containsString(movieRepository.getAvailableMovies().get(0).getYear().toString()));
    }

    @Test
    public void theOneWhereTheMenuGivesOptionToBrowseBookCatalogue() {
        String input = "1 q";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(bookRepository, movieRepository, mockScanner);
        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() -> {
            Mockito.verify(bookRepository, times(1)).getAvailableBooks();
            assertThat(getOutput(), containsString(bookRepository.getAvailableBooks().get(0).getAuthor()));
            assertThat(getOutput(), containsString(bookRepository.getAvailableBooks().get(0).getYear().toString()));
        });
        userInterface.menu();
    }

    @Test
    public void theOneWhereTheMenuGivesOptionToBrowseMovieCatalogue() {
        String input = "4 q";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(bookRepository, movieRepository, mockScanner);
        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() -> {
            Mockito.verify(movieRepository, times(1)).getAvailableMovies();
            assertThat(getOutput(), containsString(movieRepository.getAvailableMovies().get(0).getDirector()));
            assertThat(getOutput(), containsString(movieRepository.getAvailableMovies().get(0).getYear().toString()));
        });
        userInterface.menu();
    }

    @Test
    public void theOneWhereAnUnrecognizedInputIsGivenInTheMenu() {
        String input = "z q";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(bookRepository, movieRepository, mockScanner);
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
        UserInterface userInterface = new UserInterface(bookRepository, movieRepository, mockScanner);
        exit.expectSystemExit();
        userInterface.menu();
    }

    @Test
    public void theOneWhereWeCheckOutABookSuccessfully() {
        String input = "2 1234 q";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(bookRepository, movieRepository, mockScanner);
        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() -> {
            Mockito.verify(bookRepository, times(1)).checkOutBook("1234");
            assertFalse(bookRepository.getCheckedOutBooks().contains(mockBook));
        });
        userInterface.menu();
    }

    @Test
    public void theOneWhereWeCheckOutABookUnsuccessfully() {
        String input = "2 1238 q";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(bookRepository, movieRepository, mockScanner);
        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() -> {
            Mockito.verify(bookRepository, times(1)).checkOutBook("1238");
            assertFalse(bookRepository.getCheckedOutBooks().contains(mockBook));
            assertThat(getOutput(), containsString("Sorry, that book is not available."));
        });
        userInterface.menu();
    }


    @Test
    public void theOneWhereWeCheckOutAMovieSuccessfully() {
        String input = "5 Clueless q";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(bookRepository, movieRepository, mockScanner);
        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() -> {
            Mockito.verify(movieRepository, times(1)).checkOutMovie("Clueless");
            assertFalse(movieRepository.getCheckedOutMovies().contains(mockMovie));
        });
        userInterface.menu();
    }

    @Test
    public void theOneWhereWeSuccessfullyReturnABook() {
        String input = "3 1234 q";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(bookRepository, movieRepository, mockScanner);
        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() -> {
            Mockito.verify(bookRepository, times(1)).returnBook("1234");
            assertFalse(bookRepository.getCheckedOutBooks().contains(mockBook));
        });
        userInterface.menu();
    }

    @Test
    public void theOneWhereWeUnsuccessfullyReturnABook() {
        String input = "3 1274 q";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(bookRepository, movieRepository, mockScanner);
        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() -> {
            Mockito.verify(bookRepository, times(1)).returnBook("1274");
            assertThat(getOutput(), containsString("That is not a valid book to return."));
        });
        userInterface.menu();
    }
}
