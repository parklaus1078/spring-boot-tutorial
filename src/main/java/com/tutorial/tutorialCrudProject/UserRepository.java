package com.tutorial.tutorialCrudProject;

import org.springframework.data.repository.CrudRepository;

import com.tutorial.tutorialCrudProject.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}