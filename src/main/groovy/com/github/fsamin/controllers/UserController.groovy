package com.github.fsamin.controllers

import com.github.fsamin.dao.UserRepository
import com.github.fsamin.models.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * Created by fsamin on 01/02/15.
 */

@RestController
@RequestMapping(value="/admin/user")
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