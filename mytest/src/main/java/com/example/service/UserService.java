package com.example.service;

import com.example.pojo.UserDemo;

import java.util.List;

/**
 * Created by naruto on 2017/5/15.
 */
public interface UserService {
    UserDemo getUserById(int id);
    List<UserDemo> getAll();
}
