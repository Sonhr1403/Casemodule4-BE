package com.codegym.casemodule4be;

import com.codegym.casemodule4be.config.SecurityConfig;
import com.codegym.casemodule4be.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
public class SocialNetworksApplication {


    public static void main(String[] args) {
        SpringApplication.run(SocialNetworksApplication.class, args);
    }

}
