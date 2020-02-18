package com.bjqf.dao;

import java.util.List;

import com.bjqf.entity.Subject;
import com.bjqf.exception.SubjectException;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.SubjectMapper;
import com.bjqf.util.JDBCUtil;

public class SubjectDao {
	/*
	 * 获取数据总数的方法
	 */
	public int queryTotalNumber() {
		String sql ="select count(*) count from subject";
		//因为结果是list，所以需要取get（0）第一个数据。list.get(index)
		int num = (int) JDBCUtil.executeQuery(sql, new CountMapper(), null).get(0);
		
		return num;
	}
	
	/*
	 * 按页查询方法
	 */
	
	//比如当前第三页，Size为4，(3-1)*4=8，从第9条数据开始显示，没问题
	public List<Subject> queryByPage(int pageNo,int pageSize){
		String sql = "select * from subject limit ?,?";
		List<Subject> list = JDBCUtil.executeQuery(sql, new SubjectMapper(), (pageNo-1)*pageSize,pageSize);
		return list;
	}
	
	
	/*
	 * 添加题目方法
	 */
	
	public void addSubject(Subject subject) throws SubjectException { //由于添加操作需要传入的参数较多，所以在编写方法时，参数直接使用对象
		String sql = null;
		sql = "select * from subject where scontent = ?";
		List<Subject> list = JDBCUtil.executeQuery(sql, new SubjectMapper(), subject.getScontent());
		if(list.size() == 0) {
			sql = "insert into subject (scontent,sa,sb,sc,sd,skey,sstate) values (?,?,?,?,?,?,?)";
			JDBCUtil.executeUpdate(sql, subject.getScontent(),subject.getSa(),subject.getSb(),subject.getSc(),subject.getSd(),subject.getSkey(),subject.isSstate());
		}else {
			//数据存在就抛异常
			throw new SubjectException("该题干已经添加，不要重复");
		}
		
		
	}
	
	public List<Subject> selectBySid(int sid){
		String sql = "select * from subject where sid = ?";
		List<Subject> list = JDBCUtil.executeQuery(sql, new SubjectMapper(), sid);
		return list;
	}
	
	


	public int updateSubject(Subject subject) {
		String sql = "update subject set scontent=?,sa=?,sb=?,sc=?,sd=?,skey=?,sstate=? where sid=?";
		int num = JDBCUtil.executeUpdate(sql, subject.getScontent(),subject.getSa(),subject.getSb(),subject.getSc(),subject.getSd(),subject.getSkey(),subject.isSstate(),subject.getSid());
		return num;
	}
}
