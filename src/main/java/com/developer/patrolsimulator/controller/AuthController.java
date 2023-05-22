package com.developer.patrolsimulator.controller;

import com.developer.patrolsimulator.db.entities.UserEntity;
import com.developer.patrolsimulator.model.AuthenticationRequest;
import com.developer.patrolsimulator.security.TokenUtils;
import com.developer.patrolsimulator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "sign-up")
    public ResponseEntity<?> signInUsingToken(@RequestBody String token)
    {
        if (TokenUtils.getAuthentication(token) != null){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
