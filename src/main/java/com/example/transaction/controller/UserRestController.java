package com.example.transaction.controller;


import com.example.transaction.data.UserRepository;
import com.example.transaction.model.Transaction;
import com.example.transaction.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private UserRepository repository;

    public UserRestController(UserRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<User> getAll(){
        return repository.findAll();
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username){

        return repository.findByUsername(username);
    }
    @PostMapping("/{username}")
    public void createUser(@RequestBody User user){
        repository.save(user);
        repository.flush();

    }
}
