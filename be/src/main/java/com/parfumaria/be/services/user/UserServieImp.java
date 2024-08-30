package com.parfumaria.be.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.parfumaria.be.dto.user.LoginRequest;
import com.parfumaria.be.dto.user.LoginResponse;
import com.parfumaria.be.dto.user.Register;
import com.parfumaria.be.model.User;
import com.parfumaria.be.repository.UserRepository;

@Service
public class UserServieImp implements UserService{
    @Autowired
    UserRepository userRepository;
    // @Autowired
    // PasswordEncoder passwordEncoder;
    @Override
    public Register register(Register req) {
        User user = userRepository.findUserByUsername(req.getUsername());
        System.out.println(user);
        if(user == null){
            User newUser = new User();
            newUser.setUsername(req.getUsername());
            newUser.setPassword(req.getPassword());
            userRepository.save(newUser);
            return req;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username Sudah Terdaftar");
    }

    @Override
    public LoginResponse login(LoginRequest req) {
        
        return null;
    }
}
