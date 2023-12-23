package com.ladybookhouse.controller;

import com.ladybookhouse.model.RegisterUserDto;
import com.ladybookhouse.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


@RestController
@CrossOrigin
public class OrderController {




//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public void register(@Valid @RequestBody RegisterUserDto newUser) {
//        try {
//            User user = userDao.findByUsername(newUser.getUsername());
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Already Exists.");
//        } catch (UsernameNotFoundException e) {
//            userDao.create(newUser.getUsername(),newUser.getPassword(), newUser.getRole());
//        }
//    }
}
