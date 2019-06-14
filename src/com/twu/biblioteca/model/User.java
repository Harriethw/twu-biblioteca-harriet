package com.twu.biblioteca.model;


public class User {

    private String libraryId;

    private String password;

    public User(String libraryId, String password) {
        this.libraryId = libraryId;
        this.password = password;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public String getPassword() {
        return password;
    }
}
