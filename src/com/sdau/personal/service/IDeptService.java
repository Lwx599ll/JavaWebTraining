package com.sdau.personal.service;

import java.util.List;

import com.sdau.personal.pojo.Dept;
import com.sdau.personal.pojo.vo.DeptEmpCountVO;


public interface IDeptService {

	List<DeptEmpCountVO> selectDeptEmpCount();

	List<Dept> selectAll();

	

}