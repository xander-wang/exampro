package com.bjqf.dao;
import java.util.List;

import com.bjqf.entity.Role;
import com.bjqf.entity.User;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.RoleMapper;
import com.bjqf.mapper.UserMapper;
import com.bjqf.util.JDBCUtil;

import jdk.nashorn.internal.scripts.JD;

public class UserDao {
	//登录方法
	public List<User> login(String username,String userpwd){
		String sql = "select a.*,b.rolestate,b.rolename from user a "
				+ "inner join role b "
				+ "on a.roleid = b.roleid "
				+ "where username = ? and userpwd = ?";
		List<User> list = JDBCUtil.executeQuery(sql, new UserMapper(), username,userpwd);
		return list;
	}
		
	
		/**
		 * 以下均为后台管理模块
		 * @return
		 */
		//查询用户总数
		public int queryTotalNumber() {
			//必须给count(*)起别名叫count，因为countmapper里操作的是count
			String sql = "select count(*) as count from user";
			//这里查出来必定是一行，且确定一定是int型
			int num = (int) JDBCUtil.executeQuery(sql, new CountMapper(),null).get(0);
			return num;
		}
		
		//分页查询所有用户信息
		public List<User> queryByPage(int pageNo,int pageSize){
			//User对象中存在rolestate，必须得查出来，使用内连接查询，连接条件为角色id
			String sql = "select a.*,b.rolestate,b.rolename from user a join role b on a.roleid = b.roleid limit ?,?";
			List<User> list = JDBCUtil.executeQuery(sql, new UserMapper(),(pageNo-1)*pageSize,pageSize);//这里分页查询固定写法
			return list;
		}
		
		//增加用户
		public int adduser(User user) {//参数太多了，直接传对象
			String sql = "insert into user (roleid,username,userpwd,usertruename) values (?,?,?,?)";
			int num = JDBCUtil.executeUpdate(sql,user.getRoleid(),user.getUsername(),user.getUserpwd(),user.getUsertruename());
			return num; //通过num判断是否执行成功
		}
		
		//根据用户名查询数据
		public List<User> selectbyusername(String username){
			//这里得查出角色状态
			String sql = "select a.*,b.rolestate,b.rolename from user a join role b on a.roleid = b.roleid where username = ?";
			List<User> list = JDBCUtil.executeQuery(sql, new UserMapper(), username);
			return list;
		}
		
		//修改用户数据
		public int updateuser(int userid,int roleid,String userpwd,String usertruename) {
			String sql ="update user set roleid=?,userpwd=?,usertruename=? where userid=?";
			int num = JDBCUtil.executeUpdate(sql, roleid,userpwd,usertruename,userid);
			return num;//通过num判断是否执行成功
		}
		
		/**
		 * 查询role角色表
		 */
		public List<Role> selectrole(){
			String sql = "select * from role";
			List<Role> list = JDBCUtil.executeQuery(sql, new RoleMapper(), null);
			return list;
		}
}

