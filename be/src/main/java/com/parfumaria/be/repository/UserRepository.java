package com.parfumaria.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.parfumaria.be.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
    User findUserById(Integer id);
}
