package com.bjqf.service;

import java.util.List;

import com.bjqf.dao.FunctionDao;
import com.bjqf.entity.Function;
import com.bjqf.exception.FunctionException;
import com.bjqf.exception.RoleException;
import com.bjqf.exception.SubjectException;
import com.bjqf.mapper.FunctionMapper;
import com.bjqf.util.JDBCUtil;
import com.bjqf.util.PageModel;

public class FunctionService {
	FunctionDao functiondao = new FunctionDao();
	
	/**
	 * 	查询功能列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel queryByPage(int pageNo,int pageSize) {//pageNo和pageSize在servlet赋值
		//创建分页对象
		PageModel pageModel = new PageModel();
		//给属性赋值
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		//调用查询总数的方法获取count值
		int count = functiondao.queryTotalNumber();
		pageModel.setCount(count);//第三个属性赋值
		List<Function> list = functiondao.queryByPage(pageNo, pageSize); //第四个属性赋值，查询所有数据
		pageModel.setDataList(list);
		return pageModel;
	}
	
	/**
	 * 	添加顶层功能功能
	 * @param funname
	 * @param funurl
	 * @param funstate
	 * @param funpid
	 * @throws FunctionException
	 */
	public void addfunction(String funname,String funurl,int funstate,int funpid) throws FunctionException{
		System.out.println(funpid);
		int num = functiondao.addfunction(funname,funurl,funstate,funpid);
		if(num == 0) {
			throw new FunctionException("添加失败，功能名重复");
		}
	}
	
	/**
	 * 	编辑时的数据回显
	 * @param funid
	 * @return
	 */
	public List<Function> updatefun_display(int funid){
		List<Function> list = functiondao.updatefun_display(funid);
		return list;
	}
	
	/**
	 * 	编辑功能
	 * @param funname
	 * @param funurl
	 * @param funstate
	 * @param funpid
	 * @return
	 * @throws FunctionException 
	 */
	public void updatefunction(String funname,String funurl,int funstate,int funpid,int funid) throws FunctionException{ 
		int num = functiondao.updatefunction(funname, funurl, funstate, funpid,funid);
		if(num == 0) {
			throw new FunctionException("该功能名称已经存在");
		} 
	}
}
