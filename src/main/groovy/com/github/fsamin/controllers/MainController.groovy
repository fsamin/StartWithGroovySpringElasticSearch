package com.github.fsamin.controllers

import com.github.fsamin.models.User
import com.github.fsamin.services.KnownUserException
import com.github.fsamin.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

import javax.servlet.http.HttpServletRequest

@RestController
@ComponentScan(['com.github.fsamin', 'com.github.fsamin.controllers', 'com.github.fsamin.dao', 'com.github.fsamin.services'])
class MainController {


    @Autowired
    UserService userService;

    @RequestMapping("/")
    String home(HttpServletRequest request) {

        return "Hello";
    }
    @RequestMapping("/about")
    String about() {
        return "About";
    }

    @RequestMapping(value = "/signin", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    User signin(@RequestParam String email,@RequestParam String password) {
        User user = new User(
                email: email,
                password: password
        );
        try {
            userService.createUser(user);
        } catch (KnownUserException e) {
            throw new SigninException();
        }
        return user;
    }

    @RequestMapping(value = "/declareAdmin", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    User declareAdmin(@RequestParam String email,@RequestParam String password) {
        User user = new User(
                email: email,
                password: password
        );
        try {
            userService.createAdmin(user);
        } catch (KnownUserException e) {
            throw new SigninException();
        }
        return user;
    }
}

@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="Signin not allowed")
class SigninException extends RuntimeException {}
