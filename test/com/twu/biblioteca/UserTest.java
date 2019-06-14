package com.twu.biblioteca;

import com.twu.biblioteca.model.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User userUnderTest = new User("1234-12345", "password", "jane",
            "email@email.com", "1234-576885");

    @Test
    public void getLibraryId() {
        assertEquals("1234-12345", userUnderTest.getLibraryId());
    }

    @Test
    public void getThePassword() {
        assertEquals("password", userUnderTest.getPassword());
    }

    @Test
    public void testToString() {
        String userString = userUnderTest.toString();
        assertTrue(userString.contains("jane"));
    }

}
