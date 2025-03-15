package com.ivana.taskManager.service;

import com.ivana.taskManager.model.Task;
import com.ivana.taskManager.model.User;
import com.ivana.taskManager.repository.UserRepository;
import com.ivana.taskManager.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){

        this.userRepository=userRepository;
    }

    public List<User> getUsers(){

        return userRepository.findAll();
    }

    public Optional<User> findById(Integer id) {

        return userRepository.findById(id);
    }

    public User addUser(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String hashedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    public boolean existsById(Integer id){
        return userRepository.existsById(id);}

    public void deleteUser(Integer id){

        userRepository.deleteById(id);
    }

    public User updateUser(Integer id,User updateduser){
        if(userRepository.existsById(id)){
            updateduser.setId(id);
            return userRepository.save(updateduser);
        }
        else{
            throw new IllegalArgumentException("User with ID " + id + " does not exist.");
        }
    }

    public String login(String userName, String password) {
        Optional<User> userOptional = userRepository.findByUserName(userName);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            JWTUtil jwtUtil=new JWTUtil();

            if (passwordEncoder.matches(password, user.getPassword())) {
                return jwtUtil.generateToken(userName);
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }


}
