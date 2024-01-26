package com.tutorial.tutorialCrudProject.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Pattern;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "{email}", method = RequestMethod.GET)
    public @ResponseBody User getUser(@PathVariable("email") String email) {
        return userService.getUser(email);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody User addUser(@RequestBody User user) {

        String regexPattern = "^(.+)@(\\S)+$";

        if (!Pattern.compile(regexPattern).matcher(user.getEmail()).matches()) {
            throw new IllegalArgumentException("Email is in wrong format.");
        }

        return userService.addUser(user);
    }

    @RequestMapping(path = "login", method = RequestMethod.POST)
    public @ResponseBody Boolean loginUser(@RequestBody UserLogin userLogin) {
        String regexPattern = "^(.+)@(\\S)+$";

        if (!Pattern.compile(regexPattern).matcher(userLogin.getEmail()).matches()) {
            throw new IllegalArgumentException("Email is in wrong format.");
        }

        return userService.loginUser(userLogin);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody User patchUser(@RequestBody User userAfter) {
        return userService.updateUser(userAfter);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return "Deleted!";
    }

    @ExceptionHandler(NullPointerException.class)   // Exception I want to handle
    @ResponseStatus(HttpStatus.NOT_FOUND)           // Status code I want to return
    public ResponseEntity<String> handleNullPointerException(
            NullPointerException exception
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleSQLIntegrityConstraintViolationException(
            SQLIntegrityConstraintViolationException exception
    ) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(
            IllegalArgumentException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}