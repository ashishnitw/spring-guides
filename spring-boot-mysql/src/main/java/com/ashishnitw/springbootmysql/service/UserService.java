package com.ashishnitw.springbootmysql.service;

import com.ashishnitw.springbootmysql.dto.CreateUserRequest;
import com.ashishnitw.springbootmysql.model.UserDetail;
import com.ashishnitw.springbootmysql.repository.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserService {

    private final UserDetailRepository userDetailRepository;

    public UserDetail createUser(CreateUserRequest request) {
        UserDetail userDetail = UserDetail.builder().name(request.getName()).email(request.getEmail()).build();
        return userDetailRepository.save(userDetail);
    }

    public UserDetail getUser(long id) {
        return userDetailRepository.findById(id).orElse(null);
    }
}
