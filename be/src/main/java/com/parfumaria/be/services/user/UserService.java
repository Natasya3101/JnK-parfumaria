package com.parfumaria.be.services.user;

import com.parfumaria.be.dto.user.LoginRequest;
import com.parfumaria.be.dto.user.LoginResponse;
import com.parfumaria.be.dto.user.Register;
import com.parfumaria.be.dto.user.UserRequest;
import com.parfumaria.be.dto.user.UserResponse;

public interface UserService {
    Register register(Register request);
    LoginResponse login(LoginRequest request);
    UserRequest editProfile(UserRequest request);
    UserResponse profile();
}
