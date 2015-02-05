package com.github.fsamin.services

import com.github.fsamin.dao.UserRepository
import com.github.fsamin.models.Role
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
        if (knownUser != null) {
            println("Known User !");
            throw new KnownUserException();
        }
        userRepository.save(user);
    }

    void createAdmin(User user) throws KnownUserException {
        List<User> admins = userRepository.findByAuthorities(new Role(name:"ROLE_ADMIN"), Utils.getSingle()).asList();
        if (admins.size() > 0) {
            throw new KnownUserException();
        }
        List<User> users = userRepository.findByEmail(user.getEmail(), Utils.getSingle()).asList();
        User knownUser = users.isEmpty() ? null : users.first();
        if (knownUser != null) {
            throw new KnownUserException();
        }
        user.getAuthorities().add(new Role(name:"ROLE_ADMIN"));
        userRepository.save(user);
    }

}

class KnownUserException extends Exception {}
