package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.repo.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Year;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class BookRepositoryTest {
    private BookRepository bookRepository = new BookRepository();

    private Book testBook = new Book("Winnie the Pooh", "AA Milne", "1234", Year.of(1666));

    @Before
    public void addBooks() {
        bookRepository.addBook(testBook);
    }

    @Test
    public void theOneWhereWeAddABookToTheInventory() {
        assertTrue(bookRepository.getAvailableBooks().contains(testBook));
    }

    @Test
    public void theOneWhereWeCheckoutABook() {
        bookRepository.checkOutBook("1234", "userId");
        assertEquals("userId", testBook.getCurrentUserId());
        assertFalse(bookRepository.getAvailableBooks().contains(testBook));
        assertTrue(bookRepository.getCheckedOutBooks().contains(testBook));
    }

    @Test
    public void theOneWhereWeCantCheckoutABook() {
        assertFalse(bookRepository.checkOutBook("9999", "userId"));
    }

    @Test
    public void theOneWhereWeReturnABook() {
        Book book = new Book("Jane Eyre", "Charlotte Bronte", "1235", Year.of(1666));
        bookRepository.addBook(book);
        assertTrue(bookRepository.getAvailableBooks().contains(book));
        bookRepository.checkOutBook("1235", "userId");
        assertFalse(bookRepository.getAvailableBooks().contains(book));
        bookRepository.returnBook("1235");
        assertEquals(null, testBook.getCurrentUserId());
        assertTrue(bookRepository.getAvailableBooks().contains(book));
        assertFalse(bookRepository.getCheckedOutBooks().contains(book));
    }

    @Test
    public void theOneWhereWeCantReturnABook() {
        assertFalse(bookRepository.returnBook("9999"));
    }

}
