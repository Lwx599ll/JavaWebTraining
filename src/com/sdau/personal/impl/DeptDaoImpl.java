package com.sdau.personal.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sdau.personal.dao.IDeptDao;
import com.sdau.personal.pojo.Dept;
import com.sdau.personal.pojo.vo.DeptEmpCountVO;
import com.sdau.personal.util.JDBCUtil;

public class DeptDaoImpl implements  IDeptDao {

	@Override
	public List<DeptEmpCountVO> selectDeptEmpCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<DeptEmpCountVO> list = new ArrayList<DeptEmpCountVO>();
		try {
			connection = JDBCUtil.getConnection();
			String sql = "SELECT d.`name`,count(*) as 'value' FROM employee AS e,dept AS d WHERE d.id = e.dept_id GROUP BY d.id";
			preparedStatement = connection.prepareStatement(sql);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String name = resultSet.getString("name");
				int value  = resultSet.getInt("value");
				DeptEmpCountVO deptEmpCountVO = new DeptEmpCountVO(name, value);
				list.add(deptEmpCountVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	public List<Dept> selectAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Dept> list = new ArrayList<Dept>();
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select id,name,address from dept";
			preparedStatement = connection.prepareStatement(sql);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String address = resultSet.getString("address");
				Dept dept = new Dept(id, name, address);
				list.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	

}
