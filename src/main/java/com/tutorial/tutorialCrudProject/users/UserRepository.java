package com.tutorial.tutorialCrudProject.users;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    User findIdByEmail(String email);
}