package org.example.todolist.controller;

import org.example.todolist.model.User;
import org.example.todolist.service.TaskService;
import org.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUser(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping
    public String loginUser(@RequestBody User user){
        if (userService.checkLogin(user.getEmail(),user.getPassword())){
            return "Sisteme xos geldin";
        }
        return "Email ve ya shifre yanlishdir. Yeniden cehd edin";
    }

    @DeleteMapping("/{id}")
    public String deleteUSer(@PathVariable int id){
        userService.deleteUser(id);
        return "Istifadeci ugurla silindi!";
    }
}
