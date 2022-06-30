package com.sdau.personal.dao;

import java.util.List;

import com.sdau.personal.pojo.User;

public interface IUserDao {
	
	public List<User> selectAll();
	public void deleteById(Integer id);
	public List<User> selectByPage(int offset, Integer limit);
	public long selectTotalCount();
	public void add(User user);
	public void update(User user);
	public User selectById(Integer id);
	public User login(String name, String password);

}
