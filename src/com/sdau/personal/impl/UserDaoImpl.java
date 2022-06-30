package com.sdau.personal.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sdau.personal.dao.IUserDao;
import com.sdau.personal.pojo.User;
import com.sdau.personal.util.JDBCUtil;

public class UserDaoImpl implements IUserDao {

	@Override
	public List<User> selectAll() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		List<User> list = new ArrayList<User>();
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select id,name,age,gender,password from user";
			preparedStatement = connection.prepareStatement(sql);
			resultset = preparedStatement.executeQuery();
			while(resultset.next()) {
				int id = resultset.getInt("id");
				String name = resultset.getString("name");
				int age  = resultset.getInt("age");
				String gender = resultset.getString("gender");
				String password = resultset.getString("password");
				User user = new User(id, name, age, gender, password);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultset);
		}
		
		return list;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "delete from user where id=?";
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
	public List<User> selectByPage(int offset, Integer limit) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> list = new ArrayList<User>();
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select id,name,age,gender,password from user limit ?,?";
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
				String password = resultSet.getString("password");
				User user = new User(id, name, age, gender, password);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public long selectTotalCount() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		long count = 0;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select count(*) from user";
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
	public void add(User user) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "insert into user(name,age,gender,password) values(?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setInt(2, user.getAge());
			preparedStatement.setString(3, user.getGender());
			preparedStatement.setString(4, user.getPassword());
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
	public void update(User user) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "update user set name=?,age=?,gender=?,password=? where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setInt(2, user.getAge());
			preparedStatement.setString(3, user.getGender());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setInt(5, user.getId());
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
	public User selectById(Integer id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select id,name,age,gender,password from user where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				// int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age  = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String password = resultSet.getString("password");
				user = new User(id, name, age, gender, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		
		return user;
	}

	@Override
	public User login(String name, String password) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			connection = JDBCUtil.getConnection();
			String sql = "select id,name,age,gender,password from user where name=? and password=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				int age  = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				user = new User(id, name, age, gender, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection, preparedStatement, resultSet);
		}
		
		return user;
	}

}
