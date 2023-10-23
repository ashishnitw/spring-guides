package com.ashishnitw.springbootmysql.repository.read;

import com.ashishnitw.springbootmysql.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailReadRepository extends JpaRepository<UserDetail, Long> {

}
