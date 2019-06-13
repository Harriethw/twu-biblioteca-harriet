package com.twu.biblioteca;

import com.twu.biblioteca.bookrepo.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

import java.time.Year;

@RunWith(MockitoJUnitRunner.class)
public class BookTest {


    private Book bookUnderTest = new Book("Winnie the Pooh", "AA Milne", "1234", Year.of(1666));


    @Test
    public void getTitle() {
        assertTrue(bookUnderTest.getTitle().equals("Winnie the Pooh"));
    }

    @Test
    public void getAuthor() {
        assertTrue(bookUnderTest.getAuthor().equals("AA Milne"));
    }

    @Test
    public void getIsbn() {
        assertTrue(bookUnderTest.getIsbn().equals("1234"));
    }

    @Test
    public void getYear() {
        assertTrue(bookUnderTest.getYear().equals(Year.of(1666)));
    }

}
