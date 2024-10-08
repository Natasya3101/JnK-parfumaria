package com.parfumaria.be.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {
    String fullName;
    String gender;
    String phoneNumber;
    String email;
    String password;
}