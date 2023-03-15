package com.developer.patrolsimulator.controller;

import com.developer.patrolsimulator.db.entities.UserEntity;
import com.developer.patrolsimulator.model.AuthenticationRequest;
import com.developer.patrolsimulator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private UserService _userService;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody AuthenticationRequest auth)
    {
        UserEntity authUser;
        String username = auth.getUsername();
        String password = auth.getPassword();

        try{
            authUser = _userService.getByUsernameAndPassword(username, password);
            if(authUser != null)
                return ResponseEntity.ok().body(authUser);
            else
                return ResponseEntity.ok().build();
        }catch (Exception ex){
            return ResponseEntity.ok().build();
        }
    }
}
