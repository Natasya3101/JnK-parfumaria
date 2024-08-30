package com.parfumaria.be.services.user;

import com.parfumaria.be.dto.user.LoginRequest;
import com.parfumaria.be.dto.user.LoginResponse;
import com.parfumaria.be.dto.user.Register;

public interface UserService {
    Register register(Register req);
    LoginResponse login(LoginRequest req);
}
