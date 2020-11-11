package com.example.productproject.service;

import com.example.productproject.entity.User;
import com.example.productproject.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    public Optional<User> addUser(User user) {
        User existUser = userDao.findUserByUsername(user.getUsername());
        if(existUser != null) {
            return Optional.empty();
        } else {
            userDao.save(user);
            User createdUser = userDao.findUserByUsername(user.getUsername());
            createdUser.setPassword("");
            return Optional.of(createdUser);
        }
    }

    public Optional<User> getUserById(int id) {
        return userDao.findById(id);
    }

    public Optional<User> getUserByName(String username) {
        return Optional.of(userDao.findUserByUsername(username));
    }

    public Optional<List<User>> getUserAll() {
        try {
            List<User> userList = userDao.findAll();
            return Optional.of(userList);
        } catch(NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public Optional<User> setUserById(int id, User user) {
        Optional<User> existUser = userDao.findById(id);
        if(existUser.isPresent()) {
            User checkUser = userDao.findUserByUsername(user.getUsername());
            if(checkUser != null) {
                if(checkUser.getId() != id) {
                    return Optional.empty();
                }
            }
            user.setId(id);
            User savedUser = userDao.save(user);
            return Optional.of(savedUser);
        }
        return null;
    }

    public Optional<User> deleteUserById(int id) {
        Optional<User> existUser = userDao.findById(id);
        if(existUser.isPresent()) {
            userDao.deleteById(id);
            return Optional.of(existUser.get());
        }
        return Optional.empty();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Username doesn't exist!");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                new ArrayList<>());
    }


}
