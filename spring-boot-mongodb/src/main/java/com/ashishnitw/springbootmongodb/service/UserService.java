package com.ashishnitw.springbootmongodb.service;

import com.ashishnitw.springbootmongodb.dto.CreateUserRequest;
import com.ashishnitw.springbootmongodb.model.User;
import com.ashishnitw.springbootmongodb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserService {

    private final UserRepository userRepository;

    public User createCustomer(CreateUserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .roles(request.getRoles())
                .build();
        return userRepository.save(user);
    }

    public User getUser(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> searchUsers(String name, String email) {
        return userRepository.findAll();
    }

}
