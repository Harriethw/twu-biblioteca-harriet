package com.twu.biblioteca;

import com.twu.biblioteca.repo.Book;
import com.twu.biblioteca.repo.Repository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Year;


@RunWith(MockitoJUnitRunner.class)
public class RepositoryTest {
    private Repository repository = new Repository();

    private Book testBook = new Book("Winnie the Pooh", "AA Milne", "1234", Year.of(1666));

    @Before
    public void addBooks() {
        repository.addBook(testBook);
    }

    @Test
    public void theOneWhereWeAddABookToTheInventory() {
        assertTrue(repository.getAvailableBooks().contains(testBook));
    }

    @Test
    public void theOneWhereWeCheckoutABook() {
        repository.checkOutBook("1234");
        assertFalse(repository.getAvailableBooks().contains(testBook));
        assertTrue(repository.getCheckedOutBooks().contains(testBook));
    }

    @Test
    public void theOneWhereWeCantCheckoutABook() {
        assertFalse(repository.checkOutBook("9999"));
    }

    @Test
    public void theOneWhereWeReturnABook() {
        Book book = new Book("Jane Eyre", "Charlotte Bronte", "1235", Year.of(1666));
        repository.addBook(book);
        assertTrue(repository.getAvailableBooks().contains(book));
        repository.checkOutBook("1235");
        assertFalse(repository.getAvailableBooks().contains(book));
        repository.returnBook("1235");
        assertTrue(repository.getAvailableBooks().contains(book));
        assertFalse(repository.getCheckedOutBooks().contains(book));
    }

    @Test
    public void theOneWhereWeCantReturnABook() {
        assertFalse(repository.returnBook("9999"));
    }

}
