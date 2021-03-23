package com.linelife.demo.controller;

import com.linelife.demo.dto.AuthenticationRequestDto;
import com.linelife.demo.dto.RegisterRequestDto;
import com.linelife.demo.model.Status;
import com.linelife.demo.model.User;
import com.linelife.demo.security.jwt.JwtProvider;
import com.linelife.demo.service.ParametersUserService;
import com.linelife.demo.service.PersonalUserService;
import com.linelife.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/auth/")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final ParametersUserService parametersUserService;
    private final PersonalUserService personalUserService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, JwtProvider jwtProvider, ParametersUserService parametersUserService, PersonalUserService personalUserService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.parametersUserService = parametersUserService;
        this.personalUserService = personalUserService;
    }

    @PostMapping(value = "login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto){

        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            String token = jwtProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("username", username);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
    @PostMapping(value = "register")
    public ResponseEntity register(@RequestBody RegisterRequestDto requestDto){
        Map<Object, Object> response = new HashMap<>();
        if(userService.findByEmail(requestDto.getEmail())!=null) {
            response.put("status", 230);
            return new ResponseEntity(response, HttpStatus.NOT_ACCEPTABLE);
        }
        if(userService.findByUsername(requestDto.getUsername())!=null) {
            response.put("status", 231);
            return new ResponseEntity(response, HttpStatus.NOT_ACCEPTABLE);
        }
        User u = new User();
        u.setPassword(requestDto.getPassword());
        u.setEmail(requestDto.getEmail());
        u.setUsername(requestDto.getUsername());
        u.setStatus(Status.ACTIVE);
        User us = userService.saveUser(u);
        String token = jwtProvider.createToken(us.getUsername(), us.getRoles());
        response.put("status", 200);
        response.put("id", us.getId());
        response.put("username", us.getUsername());
        response.put("token", token);
        parametersUserService.add(us.getId());
        personalUserService.add(us.getId());
        return ResponseEntity.ok(response);
    }

}
