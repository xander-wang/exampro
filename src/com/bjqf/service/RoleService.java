package com.bjqf.service;

import java.util.List;

import com.bjqf.dao.RoleDao;
import com.bjqf.entity.Role;
import com.bjqf.exception.RoleException;
import com.bjqf.exception.UserException;
import com.bjqf.mapper.RoleMapper;
import com.bjqf.util.JDBCUtil;
import com.bjqf.util.PageModel;

public class RoleService {
	RoleDao roledao = new RoleDao();
	/**
	 * 分页查询
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel selectall(int pageNo,int pageSize) {
		PageModel pagemodel = new PageModel();
		int count = roledao.queryTotalNumber();
		List<Role> list = roledao.selectbypage(pageNo, pageSize);
		pagemodel.setCount(count);
		pagemodel.setDataList(list);
		pagemodel.setPageNo(pageNo);
		pagemodel.setPageSize(pageSize);
		return pagemodel;
	}
	
	/**
	 * 添加用户角色
	 * @param rolename
	 * @param rolestate
	 * @throws RoleException
	 */
	public void addrole(String rolename,int rolestate) throws RoleException{
		int num = roledao.addrole(rolename,rolestate);
		if(num == 0) {
			throw new RoleException("添加失败，角色名重复");
		}
	}
	
	/**
	 * 根据id查role数据
	 * @param roleid
	 * @return
	 */
	public List<Role> selectbyid(int roleid){
		List<Role> list = roledao.selectbyid(roleid);
		return list;
	}
	
	/**
	 * 根据roleid修改role
	 * @param rolename
	 * @param rolestate
	 * @param roleid
	 * @throws RoleException
	 */
	public void updaterole(String rolename,int rolestate,int roleid) throws RoleException {
		
		int num = roledao.updatebyid(rolename,rolestate,roleid);
		if(num == 0) {
			throw new RoleException("修改，角色名重复");
		}
	}
}
