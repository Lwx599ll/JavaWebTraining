package com.sdau.personal.dao;

import java.util.List;

import com.sdau.personal.pojo.Employee;

public interface IEmployeeDao {
    public List<Employee> selectAll();

    public void deleteById(Integer id);

    public long selectTotalCount();

    public List<Employee> selectByPage(int offset, Integer limit);

    public void add(Employee employee);

    public Employee selectById(Integer id);

    public void update(Employee employee);
}