package com.twu.biblioteca;

import com.twu.biblioteca.model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {

    private User userUnderTest = new User("1234-12345", "password");

    @Test
    public void getLibraryId() {
        assertEquals("1234-12345", userUnderTest.getLibraryId());
    }

    @Test
    public void theOneWhereWeGetThePassword() {
        assertEquals("password", userUnderTest.getPassword());
    }

}
