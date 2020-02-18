package com.bjqf.service;

import java.util.List;

import com.bjqf.dao.SubjectDao;
import com.bjqf.entity.Subject;
import com.bjqf.exception.SubjectException;
import com.bjqf.exception.UserException;
import com.bjqf.util.PageModel;

public class SubjectService {

	SubjectDao SubjectDao = new SubjectDao();
	
	
	
	public PageModel queryByPage(int pageNo,int pageSize) {//pageNo和pageSize在servlet赋值
		//创建分页对象
		PageModel pageModel = new PageModel(); //四个属性，要赋4个值
		//给属性赋值
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		//调用查询总数的方法获取count值
		int count = SubjectDao.queryTotalNumber();
		pageModel.setCount(count);//第三个属性赋值
		List<Subject> list = SubjectDao.queryByPage(pageNo, pageSize); //第四个属性赋值，查询所有数据
		pageModel.setDataList(list);
		return pageModel;
	}
	/*
	 * 添加数据方法
	 */
	public void addSubject(Subject subject) throws SubjectException {
		SubjectDao.addSubject(subject);
	}
	
	/*
	 * 根据sid查询数据
	 */
	public List<Subject> selectBySid(int sid){
		List<Subject> list = SubjectDao.selectBySid(sid);
		return list;
	}
	
	/*
	 * 修改数据方法，Dao和Service都是抛异常，不要try
	 */
	public void updateSubject(Subject subject) throws SubjectException {
		int num = SubjectDao.updateSubject(subject);
		if(num == 0) {
			throw new SubjectException("该题干已经存在");
		}
	}
	
}
