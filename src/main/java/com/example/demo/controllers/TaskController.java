package com.example.demo.controllers;

import com.example.demo.entities.Tasks;
import com.example.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public @ResponseBody Iterable<Tasks> getAllTask(){
        return taskRepository.findAll();
    }

    @GetMapping(params = "id")
    public @ResponseBody Optional<Tasks> getAllTask(@RequestParam Integer id){
        return taskRepository.findById(id);
    }
}
