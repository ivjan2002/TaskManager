package com.ivana.taskManager.Controller;


import com.ivana.taskManager.model.User;
import com.ivana.taskManager.security.JwtUtils;
import com.ivana.taskManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserController(UserService userService, JwtUtils jwtUtils) { // Spring automatski autowire-uje ove zavisnosti
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/auth/register")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id){
        if(!userService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id,@RequestBody User updatedUser){
        try{
            User user=userService.updateUser(id,updatedUser);
            return ResponseEntity.ok(user);

        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }



    @PutMapping("/{id}/change-password")
    public ResponseEntity<String> changePassword(
            @PathVariable Integer id,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        try {
            userService.changePassword(id, oldPassword, newPassword);
            return ResponseEntity.ok("Password changed successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/auth/login")
    public String login(@RequestParam String userName, @RequestParam String password) {
        if (userService.validateUser(userName, password)) {
            // Generiši JWT token za korisnika
            String token = jwtUtils.generateToken(userName);
            return "Bearer " + token;
        }
        return "Invalid credentials";
    }




}
