package com.github.fsamin.services

import com.github.fsamin.dao.UserRepository
import com.github.fsamin.models.User
import com.github.fsamin.utils.Utils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

/**
 * Created by fsamin on 31/01/15.
 */
@Service
class UserService {

    @Autowired
    UserRepository userRepository;

    void createUser(User user) throws KnownUserException {
        /*
        Page<User> allUsers = userRepository.findAll();
        println(allUsers.totalElements);
        allUsers.each {u -> println("--> " + u)}
        User knownUser = userRepository.findByEmail(user.getEmail(), Utils.getSingle()).first;

        println("KnownUser : " + knownUser + " for " + user.getEmail());
        if (knownUser != null || knownUser.id == null) {
            println("Known User !");
            throw new KnownUserException();
        }
        println("Saving user " + user);
        userRepository.save(user);
        */
    }
}

class KnownUserException extends Exception {}
