package com.twu.biblioteca;

import com.twu.biblioteca.repo.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Year;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MovieTest {


    private Movie movieUnderTest = new Movie("Clueless", "AA Milne", Year.of(1999));


    @Test
    public void getTitle() {
        assertTrue(movieUnderTest.getTitle().equals("Clueless"));
    }

    @Test
    public void getDirector() {
        assertTrue(movieUnderTest.getDirector().equals("AA Milne"));
    }

    @Test
    public void getYear() {
        assertTrue(movieUnderTest.getYear().equals(Year.of(1999)));
    }

}
