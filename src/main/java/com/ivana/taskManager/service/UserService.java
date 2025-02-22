package com.ivana.taskManager.service;

import com.ivana.taskManager.model.Task;
import com.ivana.taskManager.model.User;
import com.ivana.taskManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public User addUser(User user){

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


}
