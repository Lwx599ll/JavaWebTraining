package com.sdau.personal.service;

import java.util.List;

import com.sdau.personal.pojo.Employee;
import com.sdau.personal.util.LayUITableResult;

// 接口相当于是一个功能的清单，但是没有实现
public interface IEmployeeService {
    public List<Employee> selectAll();
    public void deleteById(Integer id);
    public LayUITableResult selectByPage(Integer page, Integer limit);
    public void add(Employee employee);
    public void deleteAll(String[] array);
    public Employee selectById(Integer id);
    public void update(Employee employee);
}