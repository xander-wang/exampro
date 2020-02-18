package com.bjqf.dao;

import java.util.List;

import com.bjqf.entity.Function;
import com.bjqf.entity.Subject;
import com.bjqf.exception.FunctionException;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.FunctionMapper;
import com.bjqf.mapper.RowMapper;
import com.bjqf.mapper.SubjectMapper;
import com.bjqf.util.JDBCUtil;

public class FunctionDao {
	
	/**
	 * 	分页先获取总数
	 * @return
	 */
	public int queryTotalNumber() {
		String sql ="select count(*) count from function";
		//因为结果是list，所以需要取get（0）第一个数据。list.get(index)
		int num = (int) JDBCUtil.executeQuery(sql, new CountMapper(), null).get(0);		
		return num;
	}
	
	/**
	 * 	分页查询功能列表
	 * @return
	 */
	public List<Function> queryByPage(int pageNo,int pageSize){
		//定义function分别为a,b表，条件为a.funpid=b.funid,结果命名为parentname
		String sql = "select  a.*,(select b.funname from function as b where b.funid = a.funpid) as parentname  from function as a limit ?,?";
		List<Function> list = JDBCUtil.executeQuery(sql, new FunctionMapper(), (pageNo-1)*pageSize,pageSize);
		return list;
	}
	
	/**
	 * 	增加主功能
	 * @param funname
	 * @param funurl
	 * @param funstate
	 * @return
	 * @throws FunctionException
	 */
	public int addfunction(String funname,String funurl,int funstate,int funpid){ 
		String sql = "insert into function (funname,funurl,funstate,funpid) values (?,?,?,?)";
		int num = JDBCUtil.executeUpdate(sql, funname,funurl,funstate,funpid);
		return num;	
	}
	
	/**
	 * 	编辑功能时的数据回显
	 * @param funid
	 * @return
	 */
	public List<Function> updatefun_display(int funid){
		String sql = "select  a.*,(select b.funname from function as b where b.funid = a.funpid) as parentname  from function as a where funid = ?";
		List<Function> list =JDBCUtil.executeQuery(sql, new FunctionMapper(), funid);
		return list;
	}
	
	
	/**
	 * 	编辑功能
	 * @param funname
	 * @param funurl
	 * @param funstate
	 * @param funpid
	 * @return
	 */
	public int updatefunction(String funname,String funurl,int funstate,int funpid,int funid){ 
		String sql = "update function set funname=?,funurl=?,funstate=?,funpid=? where funid = ?";
		System.out.println(funname);
		System.out.println(funurl);
		System.out.println(funstate);
		System.out.println(funpid);
		System.out.println(funid);
		int num = JDBCUtil.executeUpdate(sql, funname,funurl,funstate,funpid,funid);
		return num;	 
	}
	
}
