package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
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
        return "Saved";
    }

    @GetMapping
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(params = "id")
    public @ResponseBody Optional<User> getUserById(@RequestParam Integer id) {
        return userRepository.findById(id);
    }

    @DeleteMapping(params = "id")
    public @ResponseBody String deleteUser(@RequestParam Integer id) {
        userRepository.deleteById(id);
        return "Deleted";
    }

    @PutMapping(params = "id")
    public @ResponseBody String updateUser(@RequestBody User user, @RequestParam Integer id) {
        Optional<User> newUser = userRepository.findById(id);
        if (newUser.isPresent()) {
            if (user.getEmail() == null) user.setEmail(newUser.get().getEmail());
            if (user.getUsuario() == null) user.setUsuario(newUser.get().getUsuario());
            userRepository.save(user);
            return "Saved";
        } else {
            throw new ResourceNotFoundException();
        }
    }
}