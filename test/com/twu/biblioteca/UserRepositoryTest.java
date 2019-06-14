package com.twu.biblioteca;

import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repo.UserRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UserRepositoryTest {

    private UserRepository userRepositoryUnderTest = new UserRepository();

    private User mockUser = new User("1234-12345", "password");

    @Before
    public void addUser() {
        userRepositoryUnderTest.addUser(mockUser);
    }

    @Test
    public void theOneWhereWeAddAUser() {
        assertTrue(userRepositoryUnderTest.getUsers().contains(mockUser));
    }

}
