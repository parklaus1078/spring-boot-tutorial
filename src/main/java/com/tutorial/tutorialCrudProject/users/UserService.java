package com.tutorial.tutorialCrudProject.users;

// Service runs business logics

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private final BCrypt.Hasher bcrypt = BCrypt.withDefaults();
    private final Integer hashCost = 12;

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String email) {
        User target =  userRepository.findByEmail(email);

        if (target == null) {
            throw new NullPointerException("An user with email : " + email + " is not found.");
        }

        return target;
    }

    public User addUser(User user) {
        String hashedPassword = bcrypt.hashToString(hashCost, user.getPassword().toCharArray());

        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    public Boolean loginUser(UserLogin userLogin) {
        User target = userRepository.findByEmail(userLogin.getEmail());

        return BCrypt.verifyer().verify(userLogin.getPassword().toCharArray(), target.getPassword()).verified;
    }

    public User updateUser(User userAfter) {
        return userRepository.save(userAfter);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
