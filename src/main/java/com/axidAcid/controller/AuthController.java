package com.axidAcid.controller;


import com.axidAcid.model.User;
import com.axidAcid.repository.UserRepository;
import com.axidAcid.service.CustomUserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsImpl customUserDetails;


    @PostMapping("/signup")
    public ResponseEntity<User>createUserHandler(@RequestBody User user)throws Exception{
        User doesUSerExist = userRepository.findByEmail(user.getEmail());
        if(doesUSerExist != null){
            throw new Exception("email already exist with another account");
        }
        User createdUSer =new User();
        createdUSer.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUSer.setEmail(user.getEmail());
        createdUSer.setFullName(user.getFullName());

        User saveUser =userRepository.save(createdUSer);

        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);

    }

}
