package com.example.productproject.dao;

import com.example.productproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
