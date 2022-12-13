package com.codegym.casemodule4be.repository;

import com.codegym.casemodule4be.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface UserRepo extends CrudRepository<User, Long> {
    @Query(nativeQuery = true, value = "select * from user where email= :email ")
    User findByEmail(@Param("email") String email);

}

