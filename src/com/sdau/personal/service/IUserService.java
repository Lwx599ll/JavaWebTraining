package com.sdau.personal.service;

import java.util.List;

import com.sdau.personal.pojo.User;
import com.sdau.personal.util.LayUITableResult;

public interface IUserService {
    public List<User> selectAll();
    public void deleteById(Integer id);
    public LayUITableResult selectByPage(Integer page, Integer limit);
    public void add(User user);
    public void deleteAll(String[] array);
    public void update(User user);
    public User selectById(Integer id);
    public User login(String name, String password);


}

