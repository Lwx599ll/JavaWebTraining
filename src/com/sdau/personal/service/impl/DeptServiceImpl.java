package com.sdau.personal.service.impl;

import java.util.List;

import com.sdau.personal.dao.IDeptDao;
import com.sdau.personal.dao.impl.DeptDaoImpl;
import com.sdau.personal.pojo.Dept;
import com.sdau.personal.pojo.vo.DeptEmpCountVO;
import com.sdau.personal.service.IDeptService;

public class DeptServiceImpl implements IDeptService{

	private IDeptDao deptDao = new DeptDaoImpl();
	
	@Override
	public List<DeptEmpCountVO> selectDeptEmpCount() {
		// TODO Auto-generated method stub
		return deptDao.selectDeptEmpCount();
	}

	@Override
	public List<Dept> selectAll() {
		// TODO Auto-generated method stub
		return deptDao.selectAll();
	}

}
