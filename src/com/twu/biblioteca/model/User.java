package com.twu.biblioteca.model;


public class User {

    private String libraryId;

    private String password;

    private String name;

    private String email;

    private String phoneNumber;

    public User(String libraryId, String password, String name, String email, String phoneNumber) {
        this.libraryId = libraryId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "library id: " + libraryId + "name: " + name + " email: " + email + " phoneNumber: " + phoneNumber;
    }
}
