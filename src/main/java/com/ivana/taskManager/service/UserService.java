package com.ivana.taskManager.service;

import com.ivana.taskManager.model.Task;
import com.ivana.taskManager.model.User;
import com.ivana.taskManager.repository.UserRepository;
import com.ivana.taskManager.security.JwtUtils;
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


    public void changePassword(Integer id, String oldPassword, String newPassword) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                String hashedNewPassword = passwordEncoder.encode(newPassword);
                user.setPassword(hashedNewPassword);
                userRepository.save(user);
            } else {
                throw new IllegalArgumentException("Old password is incorrect");
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public boolean validateUser(String userName, String password) {
        User user = userRepository.findByUserName(userName);

        if (user != null) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if (passwordEncoder.matches(password, user.getPassword())) {
                return true;
            }
        }

        return false;
    }

}
