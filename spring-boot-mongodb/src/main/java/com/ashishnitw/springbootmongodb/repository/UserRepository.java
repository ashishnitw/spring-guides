package com.ashishnitw.springbootmongodb.repository;

import com.ashishnitw.springbootmongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findById(String id);
    User findByEmail(String email);
}
