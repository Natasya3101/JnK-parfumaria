package com.parfumaria.be.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parfumaria.be.models.User;

public interface UsersRepository extends JpaRepository<User, String>{
    Optional<User> findByEmail(String email);
    User findUserByEmail(String email);
}
