package curdapplication.backend.controller;

import curdapplication.backend.entity.User;
import curdapplication.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/add")
    public User createUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @GetMapping("/")
    public List<User> allUsers(){
        return userService.listUsers();
    }

    @PutMapping("/edit/{id}")
    public User editUser(@RequestBody User user){
        return userService.updateUser(user);
    }
    @DeleteMapping("/del/{id}")
    public String deleteById(@PathVariable Integer id){
        userService.deleteUser(id);
        return "User got deleted";
    }
}
