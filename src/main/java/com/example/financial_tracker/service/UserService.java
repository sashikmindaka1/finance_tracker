package com.example.financial_tracker.service;

import com.example.financial_tracker.entity.User;
import com.example.financial_tracker.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    // signup logic
    public String registerUser(User user){
        if (userRepository.existsByEmail(user.getEmail())){
            return "user already exists!";
        }

        userRepository.save(user);
        return "user registered successfully!";
    }


    // login logic
    public String loginUser(User loginRequest) {
        // පියවර 1: variable එක declare කරන්න (මෙහිදී තමයි 'userOptional' කියන නම හදන්නේ)
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());

        // පියවර 2: දැන් ඒ නම පාවිච්චි කරන්න පුළුවන්
        if (userOptional.isPresent()) {
            User foundUser = userOptional.get();

            if (foundUser.getPassword().equals(loginRequest.getPassword())) {
                return "Login successfully!";
            } else {
                return "Login failed!";
            }
        }

        return "User not found!";
    }
    



}
