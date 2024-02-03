package com.ladybookhouse.dao;

import com.ladybookhouse.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(int userId);

//    User findByUsername(String username);
    User findByEmail(String email);

    int findIdByEmail(String email);

//    boolean create(String username, String password, String role);
    boolean create(String name, String lastName, String  email, String password, String role);
}
