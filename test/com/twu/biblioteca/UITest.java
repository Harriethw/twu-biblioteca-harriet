package com.twu.biblioteca;

import com.twu.biblioteca.userinterface.UserInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;

public class UITest {
    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream testOut;

    private UserInterface userInterface;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreSystemInputOutput() {
        System.setOut(systemOut);
    }
    
    @Test
    public void theOneWhereWeGetAWelcomeMessage() {
        userInterface = new UserInterface();
        assertThat(getOutput(), containsString("Welcome"));
    }
}
