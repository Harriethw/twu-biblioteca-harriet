package com.twu.biblioteca;

import com.twu.biblioteca.repo.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Year;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MovieTest {


    private Movie movieUnderTest = new Movie("Clueless", "AA Milne", Year.of(1999), 10);


    @Test
    public void getTitle() {
        assertEquals("Clueless", movieUnderTest.getTitle());
    }

    @Test
    public void getDirector() {
        assertEquals("AA Milne", movieUnderTest.getDirector());
    }

    @Test
    public void getYear() {
        assertEquals(Year.of(1999), movieUnderTest.getYear());
    }

    @Test
    public void getRating() {
        assertEquals(10, movieUnderTest.getRating());
    }
}
