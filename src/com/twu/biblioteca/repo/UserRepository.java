package com.twu.biblioteca.repo;

import com.twu.biblioteca.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser(String id) {
        return users.stream().filter(user -> user.getLibraryId().equals(id)).findFirst().orElse(null);
    }
}
