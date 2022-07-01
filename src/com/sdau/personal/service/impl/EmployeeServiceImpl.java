package com.sdau.personal.service.impl;

import java.util.List;

import com.sdau.personal.dao.IEmployeeDao;
import com.sdau.personal.dao.impl.EmployeeDaoImpl;
import com.sdau.personal.pojo.Employee;
import com.sdau.personal.service.IEmployeeService;
import com.sdau.personal.util.LayUITableResult;
import com.sdau.personal.util.MD5Util;

// 业务逻辑
public class EmployeeServiceImpl implements IEmployeeService{
    private IEmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public List<Employee> selectAll() {
        return employeeDao.selectAll();
    }

    @Override
    public void deleteById(Integer id) {
        employeeDao.deleteById(id);
    }

    @Override
    public LayUITableResult selectByPage(Integer page, Integer limit) {
        // 体现Service业务
        // 分页要完成两条sql语句查找：1、当前页的数据 2、总的数量
        int offset = (page - 1) * limit;
        List<Employee> list = employeeDao.selectByPage(offset, limit);

        long totalCount = employeeDao.selectTotalCount();

        return LayUITableResult.ok(list, totalCount);
    }

    @Override
    public void add(Employee employee) {
        employeeDao.add(employee);
    }

    @Override
    public void deleteAll(String[] array) {
        for (String id : array) {
            employeeDao.deleteById(Integer.parseInt(id));
        }
    }

    @Override
    public Employee selectById(Integer id) {
        return employeeDao.selectById(id);
    }

    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
    }
}
