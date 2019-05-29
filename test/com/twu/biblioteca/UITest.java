package com.twu.biblioteca;

import com.twu.biblioteca.bookrepo.Book;
import com.twu.biblioteca.bookrepo.BookRepository;
import com.twu.biblioteca.userinterface.UserInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
        //given
        mockBooks.add(new Book("Winnie the Pooh", "AA Milne", "1234", Year.of(1666)));
        Mockito.when(bookRepository.getBooks()).thenReturn(mockBooks);
        //when
        userInterface.displayBooks();
        //then
        assertThat(getOutput(), containsString(bookRepository.getBooks().get(0).getAuthor()));
        assertThat(getOutput(), containsString(bookRepository.getBooks().get(0).getYear().toString()));
    }

    @Test
    public void theOneWhereTheMenuGivesOptionToBrowseCatalogue() {
        //given there are books in the catalogue
        mockBooks.add(new Book("Winnie the Pooh", "AA Milne", "1234", Year.of(1666)));
        Mockito.when(bookRepository.getBooks()).thenReturn(mockBooks);
        //when I press the option to see them
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(bookRepository, mockScanner);
        userInterface.menu();
        //then I should see the books displayed
        assertThat(getOutput(), containsString(bookRepository.getBooks().get(0).getAuthor()));
        assertThat(getOutput(), containsString(bookRepository.getBooks().get(0).getYear().toString()));
    }
}
