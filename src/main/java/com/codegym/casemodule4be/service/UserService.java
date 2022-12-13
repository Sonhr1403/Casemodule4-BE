package com.codegym.casemodule4be.service;

import com.codegym.casemodule4be.model.Role;
import com.codegym.casemodule4be.model.User;
import com.codegym.casemodule4be.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getRoles());
        }
        return null;
    }
    public User findByName(String email){
        return userRepo.findByEmail(email);
    }
}
