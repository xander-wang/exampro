package com.bjqf.service;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.bjqf.dao.UserDao;
import com.bjqf.entity.Role;
import com.bjqf.entity.User;
import com.bjqf.exception.SubjectException;
import com.bjqf.exception.UserException;
import com.bjqf.mapper.RoleMapper;
import com.bjqf.util.JDBCUtil;
import com.bjqf.util.PageModel;

public class UserService {
	//调用dao层对象
	UserDao userDao = new UserDao();
	
	//登录
	public List<User> login(String username,String userpwd) throws UserException{//这里自定义异常，须在servlet层try捕获
		List<User> list = userDao.login(username, userpwd);
		if(list.size() == 0){
			throw new UserException("账号或密码错误");  //如果取不到值，则说明账号密码错误
		}else if(list.get(0).isRolestate() == false){
			throw new UserException("该账号已被禁用");  //如果取到值但是rolestate=0，则是禁用
		}else{
			return list;
		}
	}
	
	
	/**
	 * 以下均为后台管理模块
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	//分页查询所有用户，在service层对分页对象进行封装。页数和尺寸在servlet层获得
	public PageModel selectall(int pageNo,int pageSize) {
		PageModel pagemodel = new PageModel(); //pagemodel有四个属性需要填入，2个通过数据库查询，2个通过前端用户传入		
		List<User> list = userDao.queryByPage(pageNo,pageSize);//第一个属性：通过Dao层查询获取user表数据,准备填入		
		pagemodel.setDataList(list); //填入		
		int count = userDao.queryTotalNumber();//第二个属性：通过Dao层查询获取user表中用户总数，准备填入		
		pagemodel.setCount(count);				//填入
		pagemodel.setPageNo(pageNo);		//第三个属性由参数得到，直接填入
		pagemodel.setPageSize(pageSize);	//第四个属性由参数得到，直接填入
		return pagemodel;		//返回page对象给servlet使用
		
	}
	
	//增加用户
	public void adduser(int roleid,String username,	String userpwd,String usertruename) throws UserException {
		User user = new User();
		user.setRoleid(roleid);
		user.setUsername(username);
		user.setUserpwd(userpwd);
		user.setUsertruename(usertruename);	
		int num = userDao.adduser(user);
		if(num == 0) {
			throw new UserException("添加失败，用户名重复");
		}
			
	}
	
	//编辑时用户回显
	public List<User> selectbyusername(String username){
		List<User> list = userDao.selectbyusername(username);
		return list;
		
	}
	
	//修改用户字段
	public void updateuser(int userid,int roleid,String userpwd,String usertruename) throws UserException {
		int num = userDao.updateuser(userid,roleid,userpwd,usertruename);
		if(num == 0) {
			throw new UserException("修改失败");
		}			
	}
	
	public List<Role> selectrole(){
		List<Role> list = userDao.selectrole();
		return list;
	}
}
