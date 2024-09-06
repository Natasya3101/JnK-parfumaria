package com.parfumaria.be.services.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.parfumaria.be.repositorys.CartItemsRepository;
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
    @Autowired
    CartItemsRepository cartItemsRepository;

    @Override
    @Transactional
    public Register register(Register request) {
        User user = usersRepository.findUserByEmail(request.getEmail());
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
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User emailUser = usersRepository.findUserByEmail(request.getEmail());
        // Cek apakah email yang di-request sudah ada di database dan bukan milik
        // pengguna yang sedang login
        if (emailUser != null && !emailUser.getEmail().equals(currentUser.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email Sudah Terdaftar");
        }
        // Jika email yang di-request sama dengan email pengguna yang sedang login,
        // tidak perlu mengupdate email
        if (emailUser != null && emailUser.getEmail().equals(currentUser.getEmail())) {
            currentUser.setFullName(request.getFullName());
            currentUser.setDateOfBirth(request.getDateOfBirth());
            currentUser.setAddress(request.getAddress());
            currentUser.setGender(request.getGender());
            currentUser.setPhoneNumber(request.getPhoneNumber());
        } else{
            currentUser.setFullName(request.getFullName());
            currentUser.setDateOfBirth(request.getDateOfBirth());
            currentUser.setEmail(request.getEmail());
            currentUser.setAddress(request.getAddress());
            currentUser.setPhoneNumber(request.getPhoneNumber());
            currentUser.setGender(request.getGender());
        }
        usersRepository.save(currentUser);
        return request;
    }

    @Override
    public UserResponse profile() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .address(user.getAddress())
                .fullName(user.getFullName())
                .dateOfBirth(user.getDateOfBirth())
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
