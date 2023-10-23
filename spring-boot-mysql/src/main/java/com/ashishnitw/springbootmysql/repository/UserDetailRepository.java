package com.ashishnitw.springbootmysql.repository;

import com.ashishnitw.springbootmysql.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

}
