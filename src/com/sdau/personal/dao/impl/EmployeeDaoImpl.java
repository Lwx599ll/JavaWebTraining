package com.sdau.personal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sdau.personal.dao.IEmployeeDao;
import com.sdau.personal.pojo.Employee;
import com.sdau.personal.pojo.vo.EmployeeDeptVO;
import com.sdau.personal.util.JDBCUtil;

public class EmployeeDaoImpl implements IEmployeeDao{

	@Override
	public List<Employee> selectAll() {
//		1、加载驱动Class.forName("");  
//		2、获得连接对象Connection
//		3、写sql语句
//		4、创建Statement(一艘船)
//		5、执行sql语句
//		     (1) 更新类（更改了表里面数据）：delete/update/insert     executeUpdate()
//		           返回值：int，表示你影响的行数
//		      (2)查询（没有改变表里面数据）:  select                              executeQuery()
//		           返回值：结果集ResultSet
//		6、关闭连接
		// Ctrl+1
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Employee> list = new ArrayList<Employee>();
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select id,name,age,gender,dept_id from employee";
			preparedStatement = connection.prepareStatement(sql);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age  = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				Integer deptId = resultSet.getInt("dept_id");
				Employee employee = new Employee(id, name, age, gender, deptId);
				list.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public void deleteById(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "delete from employee where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			int count = preparedStatement.executeUpdate();
			System.out.println(count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, null);
		}
	}

	@Override
	public long selectTotalCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		long count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select count(*) from employee";
			preparedStatement = connection.prepareStatement(sql);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				count = resultSet.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		
		return count;
	}

	@Override
	public List<EmployeeDeptVO> selectByPage(int offset, Integer limit) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<EmployeeDeptVO> list = new ArrayList<EmployeeDeptVO>();
		try {
			connection = JDBCUtil.getConnection();
			String sql = "SELECT e.id,e.`name`,e.age,e.gender,d.`name` as deptName\r\n" + 
					"FROM employee AS e INNER JOIN dept AS d\r\n" + 
					"ON d.id=e.dept_id LIMIT ?,?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, limit);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age  = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String deptName = resultSet.getString("deptName");
				EmployeeDeptVO employeeDeptVO = new EmployeeDeptVO(id, name, age, gender, deptName);
				list.add(employeeDeptVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public void add(Employee employee) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "insert into employee(name,age,gender,dept_id) values(?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setInt(2, employee.getAge());
			preparedStatement.setString(3, employee.getGender());
			preparedStatement.setInt(4, employee.getDeptId());
			System.out.println(preparedStatement);
			int count = preparedStatement.executeUpdate();
			System.out.println(count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, null);
		}
	}

	@Override
	public Employee selectById(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Employee employee = null;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select id,name,age,gender,dept_id from employee where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				// int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age  = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				int deptId  = resultSet.getInt("dept_id");
				employee = new Employee(id, name, age, gender, deptId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		
		return employee;
	}

	@Override
	public void update(Employee employee) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "update employee set name=?,age=?,gender=?,dept_id=? where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setInt(2, employee.getAge());
			preparedStatement.setString(3, employee.getGender());
			preparedStatement.setInt(4, employee.getDeptId());
			preparedStatement.setInt(5, employee.getId());
			System.out.println(preparedStatement);
			int count = preparedStatement.executeUpdate();
			System.out.println(count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, null);
		}
	}

}	