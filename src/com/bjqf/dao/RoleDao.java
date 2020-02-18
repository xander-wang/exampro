package com.bjqf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bjqf.entity.Role;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.RoleMapper;
import com.bjqf.mapper.RowMapper;
import com.bjqf.util.JDBCUtil;

public class RoleDao {
	
	/**
	 * 查询用户总数用于分页查询
	 * @return
	 */
	public int queryTotalNumber() {
		//必须给count(*)起别名叫count，因为countmapper里操作的是count
		String sql = "select count(*) as count from role";
		//这里查出来必定是一行，且确定一定是int型
		int num = (int) JDBCUtil.executeQuery(sql, new CountMapper(),null).get(0);
		return num;
	}
	/**
	 * 按页查询
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Role> selectbypage(int pageNo,int pageSize){
		String sql = "select * from role limit ?,?";
		List<Role> list = JDBCUtil.executeQuery(sql, new RoleMapper(), (pageNo-1)*pageSize,pageSize);
		return list;
	}
	
	/**
	 * 增加角色
	 * @param rolename
	 * @param rolestate
	 * @return
	 */
	public int addrole(String rolename,int rolestate) {
		String sql = "insert into role (rolename,rolestate) values (?,?)";
		int num = JDBCUtil.executeUpdate(sql, rolename,rolestate);
		return num;
	}
	
	/**
	 * 根据roleid查询role数据
	 * @param roleid
	 * @return
	 */
	public List<Role> selectbyid(int roleid){
		String sql = "select * from role where roleid = ?";
		List<Role> list = JDBCUtil.executeQuery(sql, new RoleMapper(), roleid);
		return list;
	}
	
	/**
	 * 修改role根据roleid
	 * @param rolename
	 * @param rolestate
	 * @param roleid
	 * @return
	 */
	public int updatebyid(String rolename,int rolestate,int roleid) {
		String sql = "update role set rolename=?,rolestate=? where roleid=?";
		int num = JDBCUtil.executeUpdate(sql, rolename,rolestate,roleid);
		return num;
	}
}
