package com.twu.biblioteca;

import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.repo.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Year;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class MovieRepositoryTest {
    private MovieRepository movieRepository = new MovieRepository();

    private Movie testMovie = new Movie("Clueless", "AA Milne", Year.of(1999), 10);

    @Before
    public void addBooks() {
        movieRepository.addMovie(testMovie);
    }

    @Test
    public void theOneWhereWeAddAMovieToTheInventory() {
        assertTrue(movieRepository.getAvailableMovies().contains(testMovie));
    }

    @Test
    public void theOneWhereWeCheckoutAMovie() {
        movieRepository.checkOutMovie("Clueless");
        assertFalse(movieRepository.getAvailableMovies().contains(testMovie));
        assertTrue(movieRepository.getCheckedOutMovies().contains(testMovie));
    }
//
//    @Test
//    public void theOneWhereWeCantCheckoutABook() {
//        assertFalse(movieRepository.checkOutBook("9999"));
//    }
//
//    @Test
//    public void theOneWhereWeReturnABook() {
//        Book book = new Book("Jane Eyre", "Charlotte Bronte", "1235", Year.of(1666));
//        movieRepository.addMovie(book);
//        assertTrue(movieRepository.getAvailableMovies().contains(book));
//        movieRepository.checkOutBook("1235");
//        assertFalse(movieRepository.getAvailableMovies().contains(book));
//        movieRepository.returnBook("1235");
//        assertTrue(movieRepository.getAvailableMovies().contains(book));
//        assertFalse(movieRepository.getCheckedOutBooks().contains(book));
//    }
//
//    @Test
//    public void theOneWhereWeCantReturnABook() {
//        assertFalse(movieRepository.returnBook("9999"));
//    }

}
