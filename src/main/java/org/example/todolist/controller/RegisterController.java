package org.example.todolist.controller;

import org.example.todolist.model.User;
import org.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }



    @PostMapping
    public String registerUser(@RequestBody User user){
        if(userService.getUserByEmail(user.getEmail())){
            return "Error: Bu hesab artıq movcuddur. Zehmet olmasa daxil olun.";
        }
            userService.addUser(user);
            return "Message: Qeydiyyat ugurla tamamlandı.";
        }

}
