package com.github.fsamin.controllers

import java.util.List;
import com.github.fsamin.dao.UserRepository
import com.github.fsamin.models.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * Created by fsamin on 01/02/15.
 */

@RestController
@RequestMapping(value="/user")
class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/all", method=RequestMethod.GET, produces="application/json")
    @ResponseBody List<User> getALlUsers() {
        return userRepository.findAll().asList();
    }

    @RequestMapping(value="/{userId}", method=RequestMethod.GET, produces="application/json")
    @ResponseBody User getUser(@PathVariable String userId) {
        User u = userRepository.findById(userId);
        if (u!=null) return u;
        else throw new UserNotFoundException();
    }

    @RequestMapping(value="/new", method = RequestMethod.POST, consumes="application/json", produces="application/json")
    @ResponseBody User newUser(@RequestBody User user){
        userRepository.save(user);
        return user;
    }
}

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such user")
class UserNotFoundException extends RuntimeException {}