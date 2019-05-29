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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

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
        mockBooks.add(new Book("Winnie the Pooh", "AA Milne", "1234"));
        Mockito.when(bookRepository.getBooks()).thenReturn(mockBooks);
        //when
        userInterface.displayBooks();
        //then
        assertThat(getOutput(), containsString(bookRepository.getBooks().get(0).getAuthor()));
    }
}
