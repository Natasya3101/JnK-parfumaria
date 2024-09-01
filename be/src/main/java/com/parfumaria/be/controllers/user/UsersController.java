package com.parfumaria.be.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.parfumaria.be.dto.GenericResponse;
import com.parfumaria.be.dto.user.LoginRequest;
import com.parfumaria.be.dto.user.LoginResponse;
import com.parfumaria.be.dto.user.Register;
import com.parfumaria.be.dto.user.UserRequest;
import com.parfumaria.be.services.user.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/user")
@Tag(name = "user")
@CrossOrigin(origins = "http://localhost:5173/")
public class UsersController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Register request){
        try{
            return ResponseEntity.ok().body(GenericResponse.success(userService.register(request),
            "Successfully Register New User"));
        }catch(ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).body(GenericResponse.eror(e.getReason()));
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.eror("Internal Server Error!"));
        }
    }
    @PutMapping("auth/edit-profile")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> editProfile(@RequestBody UserRequest request){
        try{
            return ResponseEntity.ok().body(GenericResponse.success(userService.editProfile(request),
            "Successfully Edit Profile"));
        }catch(ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).body(GenericResponse.eror(e.getReason()));
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.eror("Internal Server Error!"));
        }
    }

    @GetMapping("auth/profile")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> profile(){
        try{
            return ResponseEntity.ok().body(GenericResponse.success(userService.profile(),
            "Successfully Get Profile"));
        } catch(Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.eror("Internal Server Error!"));
        }
    }

    @PostMapping("auth/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request){
        try{
            LoginResponse response = userService.login(request);
            return ResponseEntity.ok().body(GenericResponse.success(response,
            "Successfully login"));
        }catch(ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).body(GenericResponse.eror(e.getReason()));
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.eror("Internal Server Error!"));
        }
    }
}
