package com.bjqf.dao;

import java.util.List;

import com.bjqf.entity.FunRole;
import com.bjqf.mapper.FunRoleMapper;
import com.bjqf.util.JDBCUtil;

public class FunRoleDao {
	
	/**
	 * 	查询角色权限
	 * @param roleid
	 * @return
	 */
	public List<FunRole> selectFunRole(int roleid){
		String sql = "select a.funid,a.funname,a.funurl,a.funpid,a.funstate,b.rrid,(case when b.rrid is null then '0' else '1' end) as rr from function a "
				+ "left outer join premission b "
				+ "on a.funid = b.funid and b.roleid = ? "
				+ "where a.funstate = 1";
		List<FunRole> list = JDBCUtil.executeQuery(sql, new FunRoleMapper(), roleid);
		return list;
	}
	
	/**
	 * 	更新角色权限，先删除，再新增
	 * @param roleid
	 * @param funid
	 */
	public void updatepro(int roleid,int funid[]){ 
		String sql_del = "delete from premission where roleid = ?";
		int num1 = JDBCUtil.executeUpdate(sql_del,roleid);
		String sql_ins = "insert into premission (funid,roleid) values (?,?)";
		int num2 = 0;
		for(int i=0;i<funid.length;i++)
		{
			num2 = JDBCUtil.executeUpdate(sql_ins,funid[i],roleid);
		}
	}
}
