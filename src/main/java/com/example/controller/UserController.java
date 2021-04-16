package com.example.controller;
import com.example.model.Users;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/register")
    public ResponseEntity<?> newUser(@RequestBody Users user){
        try {
            System.out.println("hi");
//            user.setDateOfBirth(new Date(System.currentTimeMillis()));
            userRepo.save(user);
            return new ResponseEntity<Users>(user, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getSingleUser(@PathVariable("id") String userName) {
        Optional<Users> singleUser = userRepo.findById(userName);
        if (singleUser.isPresent()){
            return new ResponseEntity<>(singleUser.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("User not found with id " + userName, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers(){
        System.out.println("hi");
        List<Users> allUsers = userRepo.findAll();
        if (allUsers.size() > 0){
            return new ResponseEntity<List<Users>>(allUsers, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Users Available", HttpStatus.NOT_FOUND);
        }
    }
    }
