package com.example.controller;
import com.example.model.Users;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1.0/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/register")
    public ResponseEntity<?> newUser(@RequestBody Users user) {
        try {
            if(userRepo.existsByLoginId(user.getLoginId())){
                throw new Exception("user with " + user.getLoginId() + " already presents");
            }else{
                userRepo.save(user);
                return new ResponseEntity<Users>(user, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getSingleUser(@PathVariable("id") String userName) {

        Users singleUser = userRepo.findByLoginId(userName);
        if (singleUser != null) {
            singleUser.toString();
            return new ResponseEntity<Users>(singleUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found with id " + userName, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers() {
        List<Users> allUsers = userRepo.findAll();
        if (allUsers.size() > 0) {
            return new ResponseEntity<List<Users>>(allUsers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Users Available", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Users user) {
        String tempLoginId = user.getLoginId();
        String tempPassword = user.getPassword();
        try {
            Users tempUser = new Users();
            tempUser = userRepo.findByLoginId(tempLoginId);
            if (tempUser.getPassword().equalsIgnoreCase(tempPassword)){
                return new ResponseEntity<Users>(tempUser, HttpStatus.OK);
            }else{
                throw new Exception("Password is incorrect");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody Users user) {
        try {
                Users olduser = userRepo.findByLoginId(user.getLoginId());
                olduser.setPassword(user.getPassword());
                userRepo.save(olduser);
                return new ResponseEntity<Users>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
