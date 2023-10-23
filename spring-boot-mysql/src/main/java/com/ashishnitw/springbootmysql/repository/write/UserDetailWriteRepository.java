package com.ashishnitw.springbootmysql.repository.write;

import com.ashishnitw.springbootmysql.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailWriteRepository extends JpaRepository<UserDetail, Long> {

}
