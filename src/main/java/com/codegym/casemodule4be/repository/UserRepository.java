package com.codegym.casemodule4be.repository;


import com.codegym.casemodule4be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

//    @Query(nativeQuery = true,value = "SELECT * from user_table where phone = :phone")
//    User findByPhoneNumber(String phone);

}