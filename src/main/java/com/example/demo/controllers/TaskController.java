package com.example.demo.controllers;

import com.example.demo.Constants;
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
    public @ResponseBody Optional<Tasks> getTaskById(@RequestParam Integer id){
        return taskRepository.findById(id);
    }

    @PostMapping(path = "/add")
    public @ResponseBody String createTasks(@RequestBody Tasks task) {
        task.setDone(false);
        taskRepository.save(task);
        return Constants.SAVED;
    }

    @DeleteMapping(params = "id")
    public @ResponseBody String removeTasks(@RequestParam Integer id) {
        taskRepository.deleteById(id);
        return Constants.DELETED;
    }

    @DeleteMapping
    public @ResponseBody String removeMultipleTasks(@RequestBody Iterable<Integer> ids) {
        taskRepository.deleteAllById(ids);
        return Constants.DELETED;
    }

    @PutMapping(params = "id")
    public @ResponseBody String updateDone(@RequestParam Integer id) {
        Optional<Tasks> task = taskRepository.findById(id);
        if(task.isPresent()){
            task.get().setDone(!task.get().getDone());
            taskRepository.save(task.get());
            return "Saved";
        } else {
            return Constants.TASK_NOT_FOUND;
        }
    }
}
