package com.example.demo.controllers;

import com.example.demo.Constants;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/usuarios")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestBody User user) {
        userRepository.save(user);
        return Constants.SAVED;
    }

    @GetMapping
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(params = "id")
    public @ResponseBody Optional<User> getUserById(@RequestParam Integer id) {
        return userRepository.findById(id);
    }

    @GetMapping(params = "email")
    public @ResponseBody Optional<User> getUserByEmail(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }


    @DeleteMapping(params = "id")
    public @ResponseBody String deleteUser(@RequestParam Integer id) {
        userRepository.deleteById(id);
        return Constants.DELETED;
    }

    @PutMapping(params = "id")
    public @ResponseBody String updateUser(@RequestParam Integer id, @RequestBody User user) {
        Optional<User> newUser = userRepository.findById(id);
        if (newUser.isPresent()) {
            if (user.getUsuario() != null || user.getEmail() != null) {
                if (user.getEmail() != null) newUser.get().setEmail(user.getEmail());
                if (user.getUsuario() != null) newUser.get().setUsuario(user.getUsuario());
                userRepository.save(newUser.get());
                return Constants.SAVED;
            } else {
                return Constants.EMPTY_BODY;
            }
        } else {
            return Constants.USER_NOT_FOUND;
        }
    }
}