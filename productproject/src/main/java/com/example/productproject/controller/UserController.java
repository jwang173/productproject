package com.example.productproject.controller;

import com.example.productproject.service.UserService;
import com.example.productproject.entity.User;
import com.example.productproject.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("")
//@Component
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Optional<User> createdUser = userService.addUser(user);

        if(createdUser.isPresent()) {
            return new ResponseEntity<User>(createdUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(user.getUsername()+" already exists. Please choose a unique username.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody(required = false) User user) throws Exception {
        if (user == null) {
            return new ResponseEntity<>("Please sign in!", HttpStatus.OK);
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            return new ResponseEntity<>("Incorrect username or password!", HttpStatus.NOT_FOUND);
        }

        final UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        Map<String,String> map = new HashMap<>();
        map.put("jwt",jwt);
        map.put("expiresIn", jwtTokenUtil.extractExpiration(jwt).toString());
        System.out.println(jwtTokenUtil.extractExpiration(jwt));
        map.put("id",Integer.toString(userService.getUserByName(user.getUsername()).get().getId()));
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public ResponseEntity<?> getUser(@PathVariable int id) {
        Optional<User> existUser = userService.getUserById(id);
        if(existUser.isPresent()) {
            return new ResponseEntity<>(existUser.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User Not Found!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> setUser(@PathVariable int id, @RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Optional<User> existUser = userService.setUserById(id, user);
        if(existUser == null) {
            return new ResponseEntity<>("User Not Found!", HttpStatus.NOT_FOUND);
        } else if(existUser.isPresent()) {
            return new ResponseEntity<>("Update User Successfully!", HttpStatus.OK);
        } else {
            return  new ResponseEntity<>("Username Has been Used, Please Use Another Username", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id) {
        Optional<User> existUser = userService.deleteUserById(id);
        if(existUser.isPresent()) {
            return new ResponseEntity<>("Deleted User Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found!", HttpStatus.NOT_FOUND);
    }
}
