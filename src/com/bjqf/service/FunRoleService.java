package com.bjqf.service;

import java.util.List;

import com.bjqf.dao.FunRoleDao;
import com.bjqf.entity.FunRole;

public class FunRoleService {
	FunRoleDao dao = new FunRoleDao();
	
	/**
	 * 	查询角色权限
	 * @param roleid
	 * @return
	 */
	public List<FunRole> Rolefun(int roleid) {
		List<FunRole> list = dao.selectFunRole(roleid);
		return list;
	}
	
	/**
	 * 	更新角色权限
	 * @param roleid
	 * @param funid
	 */
	public void updatepro(int roleid,int funid[]){
		dao.updatepro(roleid, funid);
	}
}
