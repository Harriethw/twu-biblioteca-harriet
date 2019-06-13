package com.twu.biblioteca;

import com.twu.biblioteca.repo.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

import java.time.Year;

@RunWith(MockitoJUnitRunner.class)
public class BookTest {


    private Book bookUnderTest = new Book("Winnie the Pooh", "AA Milne", "1234", Year.of(1666));


    @Test
    public void getTitle() {
        assertEquals("Winnie the Pooh", bookUnderTest.getTitle());
    }

    @Test
    public void getAuthor() {
        assertEquals("AA Milne", bookUnderTest.getAuthor());
    }

    @Test
    public void getIsbn() {
        assertEquals("1234", bookUnderTest.getIsbn());
    }

    @Test
    public void getYear() {
        assertEquals(Year.of(1666), bookUnderTest.getYear());
    }

}
