package com.parfumaria.be.model;

// import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    // auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // kode unik acak 36
    // @UuidGenerator
    private Integer id;
    private String username;
    private String password;
}
