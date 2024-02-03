package com.ladybookhouse.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ladybookhouse.model.User;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
//    public int findIdByUsername(String username) {
//        if (username == null) throw new IllegalArgumentException("Username cannot be null");
//
//        int userId;
//        try {
//            userId = jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
//        } catch (EmptyResultDataAccessException e) {
//            throw new UsernameNotFoundException("User " + username + " was not found.");
//        }
//
//        return userId;
//    }

	@Override
	public User getUserById(int userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if (results.next()) {
			return mapRowToUser(results);
		} else {
			return null;
		}
	}

    @Override
    public User findByEmail(String email) {

              if (email== null) throw new IllegalArgumentException("Email cannot be null");

     for (User user : this.findAll()) {
         if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User with this" + email+ " was not found.");
    }

    @Override
    public int findIdByEmail(String email) {
        if(email == null) throw new IllegalArgumentException("Email cannot be null");

        int userId;
            try {
                userId = jdbcTemplate.queryForObject("select user_id from users where email= ?", int.class, email);
            } catch (EmptyResultDataAccessException e) {
                throw new UsernameNotFoundException("User with this email" + email + "not found");

            }
        return userId;
    }

    @Override
    public boolean create(String name, String lastName, String email, String password, String role) {
        String insertUserSql = "insert into users (firstName, lastName, email, password_hash,role) values (?,?,?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = role.toUpperCase().startsWith("ROLE_") ? role.toUpperCase() : "ROLE_" + role.toUpperCase();

        return jdbcTemplate.update(insertUserSql, email, password_hash, ssRole) == 1;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

//    @Override
//    public User findByUsername(String username) {
//        if (username == null) throw new IllegalArgumentException("Username cannot be null");
//
//        for (User user : this.findAll()) {
//            if (user.getUsername().equalsIgnoreCase(username)) {
//                return user;
//            }
//        }
//        throw new UsernameNotFoundException("User " + username + " was not found.");
//    }

//    @Override
//    public boolean create(String username, String password, String role) {
//        String insertUserSql = "insert into users (username,password_hash,role) values (?,?,?)";
//        String password_hash = new BCryptPasswordEncoder().encode(password);
//        String ssRole = role.toUpperCase().startsWith("ROLE_") ? role.toUpperCase() : "ROLE_" + role.toUpperCase();
//
//        return jdbcTemplate.update(insertUserSql, username, password_hash, ssRole) == 1;
//    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        return user;
    }
}
