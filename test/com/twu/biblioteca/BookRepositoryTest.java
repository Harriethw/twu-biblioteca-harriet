package com.twu.biblioteca;

import com.twu.biblioteca.bookrepo.Book;
import com.twu.biblioteca.bookrepo.BookRepository;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Year;

public class BookRepositoryTest {
    private BookRepository bookRepository = new BookRepository();

    @Test
    public void theOneWhereWeCheckoutABook() {
        //given I have the isbn of a book that is available
        //when I pass that isbn to the repo
        Book book = new Book("Winnie the Pooh", "AA Milne", "1234", Year.of(1666));
        bookRepository.addBook(book);
        bookRepository.checkOutBook("1234");
        //then that book should be removed from the available books and added to checked out
        assertFalse(bookRepository.getAvailableBooks().contains(book));
        assertTrue(bookRepository.getCheckedOutBooks().contains(book));
    }

}
