package com.spring.service;


import com.spring.dao.UserDao;
import com.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserServiсe {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(User application) {
        application.setPassword(passwordEncoder.encode(application.getPassword()));
        userDao.addUser(application);
    }

    @Override
    public void deleteUser(int userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public void updateUser(User application) {
        application.setPassword(passwordEncoder.encode(application.getPassword()));
        userDao.updateUser(application);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }
}
