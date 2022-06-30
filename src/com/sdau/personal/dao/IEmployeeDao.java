package com.sdau.personal.dao;

import java.util.List;

import com.sdau.personal.pojo.Employee;
import com.sdau.personal.pojo.vo.EmployeeDeptVO;

public interface IEmployeeDao {
	public List<Employee> selectAll();
	public void deleteById(Integer id);
	public long selectTotalCount();
	public List<EmployeeDeptVO> selectByPage(int offset, Integer limit);
	public void add(Employee employee);
	public Employee selectById(Integer id);
	public void update(Employee employee);
}