package com.ladybookhouse.security;


import com.ladybookhouse.dao.UserDao;
import com.ladybookhouse.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserModelDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserModelDetailsService.class);

    private final UserDao userDao;

    public UserModelDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating user '{}'", login);
        String lowercaseLogin = login.toLowerCase();
        return createSpringSecurityUser(lowercaseLogin, userDao.findByEmail(lowercaseLogin));
    }

    private MyUserDetail createSpringSecurityUser(String lowercaseLogin, User user) {
        if (!user.isActivated()) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
//        return new org.springframework.security.core.userdetails.User(user.getEmail(),
//                user.getPassword(),
//                grantedAuthorities);

        MyUserDetail userDetails = new MyUserDetail();
        userDetails.setEmail(user.getEmail()); // Assuming you have a setter for email
        userDetails.setPassword(user.getPassword()); // Assuming you have a setter for password
        userDetails.setAuthorities(grantedAuthorities); // Assuming you have a setter for authorities
        // Set additional properties as needed
        userDetails.setFirstName(user.getFirstName()); // Assuming user model has a firstName field
        userDetails.setLastName(user.getLastName()); // Assuming user model has a lastName field
        userDetails.setEnabled(user.isActivated()); // Assuming you have a setter for enabled status
        // Set accountNonExpired, credentialsNonExpired, accountNonLocked as needed

        return userDetails;
    }
}

