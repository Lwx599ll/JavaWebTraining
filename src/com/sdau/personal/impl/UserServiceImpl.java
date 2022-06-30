package com.sdau.personal.impl;

import java.util.List;

import com.sdau.personal.dao.IUserDao;
import com.sdau.personal.dao.impl.UserDaoImpl;
import com.sdau.personal.pojo.User;
import com.sdau.personal.service.IUserService;
import com.sdau.personal.util.LayUITableResult;
import com.sdau.personal.util.MD5Util;

public class UserServiceImpl implements IUserService {
	
	IUserDao userDao = new UserDaoImpl();
	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		return userDao.selectAll();
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		userDao.deleteById(id);
	}

	@Override
	public LayUITableResult selectByPage(Integer page, Integer limit) {
		// TODO Auto-generated method stub
		int offset = (page-1)*limit;
		List<User> list  = userDao.selectByPage(offset,limit);
		long totalCount = userDao.selectTotalCount();
		return LayUITableResult.ok(list, totalCount);
	}

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		user.setPassword(MD5Util.MD5Encode(user.getPassword() + MD5Util.MD5_SALT));
		userDao.add(user);
		
	}

	@Override
	public void deleteAll(String[] array) {
		// TODO Auto-generated method stub
		for (String id : array) {
			userDao.deleteById(Integer.parseInt(id));
		}
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public User selectById(Integer id) {
		
		return userDao.selectById(id);
	}

	@Override
	public User login(String name, String password) {
		// TODO Auto-generated method stub
		//return userDao.login(name,password);
		return userDao.login(name, MD5Util.MD5Encode(password));
	}
	
	

}
