package com.github.fsamin.services

import com.github.fsamin.dao.UserRepository
import com.github.fsamin.models.User
import com.github.fsamin.utils.Utils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by fsamin on 31/01/15.
 */
@Service
class UserService {

    @Autowired
    UserRepository userRepository;

    void createUser(User user) throws KnownUserException {
        List<User> users = userRepository.findByEmail(user.getEmail(), Utils.getSingle()).asList();
        User knownUser = users.isEmpty() ? null : users.first();

        println("KnownUser : " + knownUser + " for " + user.getEmail());
        if (knownUser != null) {
            println("Known User !");
            throw new KnownUserException();
        }
        println("Saving user " + user);
        userRepository.save(user);
    }
}

class KnownUserException extends Exception {}
