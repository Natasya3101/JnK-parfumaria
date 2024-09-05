package com.parfumaria.be.models;

import java.time.LocalDate;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @UuidGenerator
    @Column(name ="id", length = 36, nullable=false)
    private String id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", length = 2000, nullable = false)
    private String password;

    @Column(name = "full_name")
    private String fullName;
    
    @Column(name = "address")
    private String address;

    @Column(name = "date_of_Birth")
    private LocalDate dateOfBirth;


    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "gender", length = 25)
    private String gender;
}
