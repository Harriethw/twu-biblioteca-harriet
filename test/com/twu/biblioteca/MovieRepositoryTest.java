package com.twu.biblioteca;

import com.twu.biblioteca.repo.Book;
import com.twu.biblioteca.repo.BookRepository;
import com.twu.biblioteca.repo.Movie;
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
        movieRepository.addBook(testMovie);
    }

    @Test
    public void theOneWhereWeAddAMovieToTheInventory() {
        assertTrue(movieRepository.getAvailableBooks().contains(testMovie));
    }
//
//    @Test
//    public void theOneWhereWeCheckoutABook() {
//        movieRepository.checkOutBook("1234");
//        assertFalse(movieRepository.getAvailableBooks().contains(testMovie));
//        assertTrue(movieRepository.getCheckedOutBooks().contains(testMovie));
//    }
//
//    @Test
//    public void theOneWhereWeCantCheckoutABook() {
//        assertFalse(movieRepository.checkOutBook("9999"));
//    }
//
//    @Test
//    public void theOneWhereWeReturnABook() {
//        Book book = new Book("Jane Eyre", "Charlotte Bronte", "1235", Year.of(1666));
//        movieRepository.addBook(book);
//        assertTrue(movieRepository.getAvailableBooks().contains(book));
//        movieRepository.checkOutBook("1235");
//        assertFalse(movieRepository.getAvailableBooks().contains(book));
//        movieRepository.returnBook("1235");
//        assertTrue(movieRepository.getAvailableBooks().contains(book));
//        assertFalse(movieRepository.getCheckedOutBooks().contains(book));
//    }
//
//    @Test
//    public void theOneWhereWeCantReturnABook() {
//        assertFalse(movieRepository.returnBook("9999"));
//    }

}
