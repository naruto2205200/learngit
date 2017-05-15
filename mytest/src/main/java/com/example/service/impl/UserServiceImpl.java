package com.example.service.impl;

import com.example.mapper.UserMapper;
import com.example.pojo.UserDemo;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by naruto on 2017/5/15.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper mapper;
    @Override
    public UserDemo getUserById(int id) {
        return mapper.getUserById(id);
    }

    @Override
    public List<UserDemo> getAll() {
        return mapper.getAll();
    }


}
