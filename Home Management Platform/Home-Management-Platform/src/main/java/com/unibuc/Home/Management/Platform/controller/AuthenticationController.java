package com.unibuc.Home.Management.Platform.controller;

import com.unibuc.Home.Management.Platform.domain.JwtRequest;
import com.unibuc.Home.Management.Platform.domain.User;
import com.unibuc.Home.Management.Platform.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private CustomUserDetailsService userDetailsService;

    @Autowired
    public AuthenticationController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws SQLException {
        System.out.println("controller:"+user.getUsername());
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
        return ResponseEntity.ok(userDetailsService.handleLogin(authenticationRequest));
    }

}