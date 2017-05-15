package com.example.controller;

import com.example.pojo.UserDemo;
import com.example.service.UserService;
import com.example.util.DataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by naruto on 2017/5/15.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getuser")
    public UserDemo getUser() {
        UserDemo user = new UserDemo();
        user.setUsername("zhangsan");
        return user;
    }

    @RequestMapping("/getUserById")
    public UserDemo getUserById(int id){
        return userService.getUserById(id);
    }

    @RequestMapping("/list")
    public DataGridResult getAll(int pageNum,int pageSize, Model model){
        PageHelper.startPage(pageNum,pageSize);
        List<UserDemo> list = userService.getAll();
        //创建一个返回值对象
        DataGridResult result = new DataGridResult();
        // 取商品列表
        for(UserDemo item : list) {
            System.out.println(item.getUsername());
        }
        result.setRows(list);
        //取记录总条数
        PageInfo<UserDemo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
