package com.f1store.backend.controllers;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.f1store.backend.entities.User;
/*import com.f1store.backend.jwt.JwtAuthenticationResponse;
import com.f1store.backend.jwt.JwtTokenProvider;
import com.f1store.backend.jwt.LoginRequest;*/
import com.f1store.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

 //   @Autowired
//    private JwtTokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if(userService.findByUsername(user.getUsername())!=null) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        return ResponseEntity.ok(userService.save(user));
    }

 /*   @PostMapping("/login")
    public ResponseEntity<?> loginUser(LoginRequest loginRequest) throws Exception {
        try {
            Authentication auth = authenticate(loginRequest.getUsername(),loginRequest.getPassword());
            System.out.println(auth.toString());

            final String token = tokenProvider.generateToken(auth);
            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        }catch (Exception e){
            return null;
        }

    }*/
    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (BadCredentialsException bce){
            throw new Exception("Bad auth",bce);
        }

    }

    @PostMapping("/primoutente")
    public ResponseEntity<?> primoUser(){
        User utente = new User();
        utente.setUsername("jocy1098");
        utente.setBirthDate(new Date());
        utente.setAddress("Via Roma");
        utente.setFirstName("Giacinto");
        utente.setLastName("Dieci10");
        utente.setPassword(passwordEncoder.encode("password1"));
        userService.save(utente);
        return  ResponseEntity.ok("Utente registr");
    }
}