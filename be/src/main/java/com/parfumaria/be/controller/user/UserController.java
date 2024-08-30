package com.parfumaria.be.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.parfumaria.be.dto.GenericResponse;
import com.parfumaria.be.dto.user.Register;
import com.parfumaria.be.services.user.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "User")
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
     public ResponseEntity<Object> register(Register request) {
        try {
            userService.register(request);
            return ResponseEntity.ok().body(GenericResponse.success(null, "Success Registry New User"));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(GenericResponse.eror(e.getReason()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.eror("Internal Server Error!"));
        }
    }
}
