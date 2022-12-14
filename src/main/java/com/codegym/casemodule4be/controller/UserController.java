package com.codegym.casemodule4be.controller;


import com.codegym.casemodule4be.model.Role;
import com.codegym.casemodule4be.model.User;
import com.codegym.casemodule4be.repository.UserRepo;
import com.codegym.casemodule4be.service.UserService;
import com.codegym.casemodule4be.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            // Tạo ra 1 đối tượng Authentication.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtService.generateTokenLogin(authentication);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Role role = new Role();
        role.setId(Long.valueOf(1));
        role.setName("ROLE_ADMIN");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        userRepo.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/checkemail")
    public ResponseEntity<User> checkUser(@RequestParam String userName) {
        User appUser = userService.findByUsername(userName);
        if (appUser != null) {
            return new ResponseEntity<>(appUser, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(appUser, HttpStatus.OK);
        }
    }
}
