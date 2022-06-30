package com.sdau.personal.dao;

import java.util.List;

import com.sdau.personal.pojo.Dept;
import com.sdau.personal.pojo.vo.DeptEmpCountVO;

public interface IDeptDao {

	List<DeptEmpCountVO> selectDeptEmpCount();

	List<Dept> selectAll();

}
