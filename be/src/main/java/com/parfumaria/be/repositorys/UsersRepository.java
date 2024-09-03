package com.parfumaria.be.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parfumaria.be.models.User;

public interface UsersRepository extends JpaRepository<User, String>{
    User findUserByEmail(String email);
}
