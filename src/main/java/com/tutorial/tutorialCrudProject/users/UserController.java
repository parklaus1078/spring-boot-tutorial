package com.tutorial.tutorialCrudProject.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "{email}", method = RequestMethod.GET)
    public @ResponseBody User getUser(@PathVariable("email") String email) {
        User target =  userRepository.findByEmail(email);

        if (target == null) {
            throw new NullPointerException("An user with email : " + email + " is not found.");
        }

        return target;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody User patchUser(@RequestBody User userAfter) {
        User target = userRepository.findIdByEmail(userAfter.getEmail());

        userAfter.setId(target.getId());

        return userRepository.save(userAfter);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);

        return "Deleted!";
    }

    @ExceptionHandler(NullPointerException.class)   // Exception I want to handle
    @ResponseStatus(HttpStatus.NOT_FOUND)           // Status code I want to return
    public ResponseEntity<String> handleNullPointerException(
            NullPointerException exception
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}