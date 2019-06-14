package com.twu.biblioteca;

import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repo.UserRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserRepositoryTest {

    private UserRepository userRepositoryUnderTest = new UserRepository();

    private User mockUser = new User("1234-12345", "password", "Jane",
            "email@email.com", "1234-576885");

    @Before
    public void addUser() {
        userRepositoryUnderTest.addUser(mockUser);
    }

    @Test
    public void theOneWhereWeAddAUser() {
        assertTrue(userRepositoryUnderTest.getUsers().contains(mockUser));
    }

    @Test
    public void theOneWhereWeGetAUser() {
        assertEquals(userRepositoryUnderTest.getUser("1234-12345").toString(), mockUser.toString());
    }
}
