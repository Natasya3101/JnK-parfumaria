package com.parfumaria.be.services.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.parfumaria.be.dto.user.LoginRequest;
import com.parfumaria.be.dto.user.LoginResponse;
import com.parfumaria.be.dto.user.Register;
import com.parfumaria.be.dto.user.UserRequest;
import com.parfumaria.be.dto.user.UserResponse;
import com.parfumaria.be.jwt.JwtUtil;
import com.parfumaria.be.models.User;
import com.parfumaria.be.repositorys.UsersRepository;
import com.parfumaria.be.services.image.ImageService;

import jakarta.servlet.http.Cookie;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    ImageService imageService;

    @Override
    @Transactional
    public Register register(Register request) {
        User user = usersRepository.findByEmail(request.getEmail()).orElse(null);
        if (user == null) {
            User newUser = new User();
            newUser.setFullName(request.getFullName());
            newUser.setGender(request.getGender());
            newUser.setPhoneNumber(request.getPhoneNumber());
            newUser.setEmail(request.getEmail());
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            usersRepository.save(newUser);
            return request;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email Sudah Terdaftar");
    }

    public UserRequest editProfile(UserRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = usersRepository.findUserByEmail(auth.getName());
        User emailUser = usersRepository.findByEmail(request.getEmail()).orElse(null);
        if (emailUser != null && request.getEmail().equals(emailUser.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email Sudah Terdaftar");
        } else if (emailUser != null && user.getEmail().equals(request.getEmail())) {
            user.setEmail(request.getEmail());
        }
        user.setFullName(request.getFullName());
        user.setGender(request.getGender());
        user.setPhoneNumber(request.getPhoneNumber());
        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        usersRepository.save(user);
        return request;
    }

    @Override
    public UserResponse profile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = usersRepository.findUserByEmail(auth.getName());
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = usersRepository.findUserByEmail(request.getEmail());
        if (user != null) {
            Boolean isMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());
            if (isMatch) {
                LoginResponse response = new LoginResponse();
                response.setEmail(user.getEmail());

                Map<String, Object> claims = new HashMap<>();
                Cookie cookie = new Cookie("token", jwtUtil.generateToken(user.getId().toString(), claims));
                cookie.setHttpOnly(true);
                cookie.setMaxAge(60 * 60 * 24 * 7);
                cookie.setPath("/");
                response.setToken(cookie.getValue());
                return response;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Username or Password");
    }
}
