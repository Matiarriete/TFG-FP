package com.example.demo.controllers;

import com.example.demo.Constants;
import com.example.demo.entities.Tasks;
import com.example.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @PutMapping(params = "id", path = "/update")
    public @ResponseBody String updateTask(@RequestParam Integer id, @RequestBody Tasks task) throws ParseException {
        Optional<Tasks> taskToUpdate = taskRepository.findById(id);
        if(taskToUpdate.isPresent()){
            if(task.getName() != null) taskToUpdate.get().setName(task.getName());
            if(task.getDescription() != null) taskToUpdate.get().setDescription(task.getDescription());
            if(task.getPriority() != null) taskToUpdate.get().setPriority(task.getPriority());
            if(task.getUser() != null) taskToUpdate.get().setUser(task.getUser());
            if(task.getDate() != null){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date parsedDate = dateFormat.parse(task.getDate());
                Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                taskToUpdate.get().setDate(timestamp);
            }
            taskRepository.save(taskToUpdate.get());
        } else {
            return Constants.TASK_NOT_FOUND;
        }
        return "Updated";
    }
}
