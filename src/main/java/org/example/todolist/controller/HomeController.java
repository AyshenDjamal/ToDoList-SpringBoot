package org.example.todolist.controller;

import org.example.todolist.model.Task;
import org.example.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/tasks")
public class HomeController {

    @Autowired
    private TaskService taskService;



    @GetMapping("/{userID}/all")
    public List<Task> getAllTasksByUserId(@PathVariable int userID){
        return taskService.findTasksByUserId(userID);
    }

    @GetMapping("/{userID}/done")
    public List<Task> getDoneTasksByUserId(@PathVariable int userID, @RequestParam boolean isDone){
        return taskService.getDoneTasksByUserId(userID, isDone);
    }

    @GetMapping("/{userID}/undone")
    public List<Task> getUndoneTasksByUserId(@PathVariable int userID, @RequestParam boolean isDone){
        return taskService.getUndoneTasksByUserId(userID,isDone);
    }

    @PostMapping
    public void addTasks(@RequestBody Task task){
        taskService.addTasks(task);
    }


    @PutMapping("/{id}")
    public String updateTaskStatus(@PathVariable int id, @RequestBody Task task){
        taskService.updateTaskDoneStatus(id,task);
        return "Task ugurla icra olundu";
    }
}
