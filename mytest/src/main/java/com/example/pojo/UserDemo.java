package com.example.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by naruto on 2017/5/15.
 */
public class UserDemo {
    private int id;             // 用户id
    private String username;    // 用户名
    private String password;    // 用户密码
    private String email;       // 用户邮箱
    private String phone;       // 用户手机
    @JSONField(format="yyyy-MM-dd")
    private Date created;
    @JSONField(format="yyyy-MM-dd")
    private Date updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
