package com.bjqf.service;

import java.util.List;

import com.bjqf.dao.PaperDao;
import com.bjqf.entity.Paper;
import com.bjqf.entity.Subject;
import com.bjqf.exception.PaperException;
import com.bjqf.util.PageModel;

public class PaperService {
	PaperDao paperDao = new PaperDao();
	
	//分页查试卷方法
	public PageModel queryByPage(int pageNo,int pageSize) {//pageNo和pageSize在servlet赋值
		//创建分页对象
		PageModel pageModel = new PageModel(); //四个属性，要赋4个值
		//给属性赋值
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		//调用查询总数的方法获取count值
		int count = paperDao.queryTotalNumber();
		pageModel.setCount(count);//第三个属性赋值
		List<Paper> list = paperDao.queryByPage(pageNo, pageSize); //第四个属性赋值，查询所有数据
		pageModel.setDataList(list);
		return pageModel;
	}
	
	//增加试卷方法
	public void addPaper(String pname,int num) throws PaperException {
		PaperDao paperdao = new PaperDao();
		paperdao.addPaper(pname, num);
	}
	
	/**
	 * 查看试卷具体的试题信息
	 * @param pname
	 * @return
	 */
	public List<Subject> selectSub(String pname){
		List<Subject> list = paperDao.selectSub(pname);
		return list;
	}
}
