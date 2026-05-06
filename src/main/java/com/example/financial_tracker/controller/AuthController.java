package com.example.financial_tracker.controller;

import com.example.financial_tracker.entity.User;
import com.example.financial_tracker.repo.UserRepository;
import com.example.financial_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        String result = userService.registerUser(user);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {

        // this is variable, it asign to email variable
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isPresent()) {

            User user = userOptional.get();

            if (user.getPassword().equals(password)){
                return ResponseEntity.ok ("User login sucessfully!");
            }else{
                return ResponseEntity.badRequest().body("Wrong password!");
            }
        }else {
            return ResponseEntity.badRequest().body("User not found!");
        }

    }
}
