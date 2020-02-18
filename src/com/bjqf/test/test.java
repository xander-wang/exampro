package com.bjqf.test;

import java.util.List;

import com.bjqf.dao.PaperDao;
import com.bjqf.dao.FunRoleDao;
import com.bjqf.dao.UserDao;
import com.bjqf.entity.FunRole;
import com.bjqf.entity.User;
import com.bjqf.exception.PaperException;

public class test {

	public static void main(String[] args){
		FunRoleDao test = new FunRoleDao();
			List<FunRole> list = test.selectFunRole(1);
			System.out.println(list.get(0).getFunid());

	}

}
