package com.bjqf.dao;

import java.util.List;

import com.bjqf.entity.Paper;
import com.bjqf.entity.Subject;
import com.bjqf.exception.PaperException;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.PaperMapper;
import com.bjqf.mapper.SubjectMapper;
import com.bjqf.util.JDBCUtil;

public class PaperDao {
	
	
	/*
	 * 获取数据总数
	 */
	public int queryTotalNumber() {
		//as count别忘了，countmapper里是取count的
		String sql ="SELECT COUNT(*) as count from (select paper.*,count(*) as pcount from paper group by pname)x";
		int num = (int) JDBCUtil.executeQuery(sql, new CountMapper(), null).get(0);		
		return num;
	}
	
	/*
	 * 按页查询试卷
	 */
	public List<Paper> queryByPage(int pageNo,int pageSize){
		//按pid降序排列
		String sql = "select * from (select paper.*,count(*) as pcount from paper group by pname)x order by pid DESC limit ?,?";
		List<Paper> list = JDBCUtil.executeQuery(sql, new PaperMapper(), (pageNo-1)*pageSize,pageSize);
		return list;
	}
	
	/*
	 * 增加试卷，随机收入试题
	 */
	public void addPaper(String pname,int num) throws PaperException {
		//根据sstate查询可用试题,并对比判断
		String sql =null;
		//as count 别忘了，mapper里是取count的
		sql = "select count(*) as count from subject where sstate = 1";
		int size =(int)JDBCUtil.executeQuery(sql, new CountMapper(), null).get(0);
		//获取返回值的size与num进行对比判断，是否要求添加的试题数大于试题库总数
		if(size < num) {
			throw new PaperException("题目数量大于题库数量，添加失败");
		}
		else {
			sql = "insert into paper (pname,sid) select ?,sid from subject where sstate =1 order by rand() limit ?";
			JDBCUtil.executeUpdate(sql,pname,num);
		}
		
	}
	
	/**
	 * 查看试卷具体的试题信息
	 * @param pname
	 * @return
	 */
	public List<Subject> selectSub(String pname){
		String sql = "select s.* from subject s join paper p on s.sid = p.sid where p.pname = ?";
		List<Subject> list = JDBCUtil.executeQuery(sql, new SubjectMapper(), pname);
		return list;
	}
	
	
	
	
	
}
