package com.twu.biblioteca;

import com.twu.biblioteca.bookrepo.Book;
import com.twu.biblioteca.bookrepo.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Year;


@RunWith(MockitoJUnitRunner.class)
public class BookRepositoryTest {
    private BookRepository bookRepository = new BookRepository();

    private Book book = new Book("Winnie the Pooh", "AA Milne", "1234", Year.of(1666));

    @Before
    public void addBooks() {
        bookRepository.addBook(book);
    }

    @Test
    public void theOneWhereWeAddABookToTheInventory() {
        assertTrue(bookRepository.getAvailableBooks().contains(book));
    }

    @Test
    public void theOneWhereWeCheckoutABook() {
        bookRepository.checkOutBook("1234");
        assertFalse(bookRepository.getAvailableBooks().contains(book));
        assertTrue(bookRepository.getCheckedOutBooks().contains(book));
        bookRepository.returnBook("1234");
    }

    @Test
    public void theOneWhereWeCantCheckoutABook() {
        assertFalse(bookRepository.checkOutBook("9999"));
    }

    @Test
    public void theOneWhereWeReturnABook() {
        Book book = new Book("Jane Eyre", "Charlotte Bronte", "1235", Year.of(1666));
        bookRepository.addBook(book);
        assertTrue(bookRepository.getAvailableBooks().contains(book));
        bookRepository.checkOutBook("1235");
        assertFalse(bookRepository.getAvailableBooks().contains(book));
        bookRepository.returnBook("1235");
        assertTrue(bookRepository.getAvailableBooks().contains(book));
        assertFalse(bookRepository.getCheckedOutBooks().contains(book));
    }

    @Test
    public void theOneWhereWeCantReturnABook() {
        assertFalse(bookRepository.returnBook("9999"));
    }

}
