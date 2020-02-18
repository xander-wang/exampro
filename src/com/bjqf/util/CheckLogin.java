package com.bjqf.util;


import javax.servlet.http.HttpSession;

import com.bjqf.entity.User;

public class CheckLogin{
	
	/**
	 * 检查是否登录，无论身份
	 * @param session
	 * @return
	 */
	public static boolean check_login(HttpSession session) {
		String rolename;
		try {
			rolename = ((User) session.getAttribute("user")).getRolename();
			if(rolename != null) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}		
	}
	
	/**
	 * 检查是否是学生
	 * @param session
	 * @return
	 */
	public static boolean check_student(HttpSession session) {
		String rolename;
		try {
			rolename = ((User) session.getAttribute("user")).getRolename();
			if(rolename.equals("学生")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		
	}
	/**
	 * 检查是否是管理员
	 * @param session
	 * @return
	 */
	public static boolean check_admin(HttpSession session) {
		String rolename;
		try {
			rolename = ((User) session.getAttribute("user")).getRolename();
			if(rolename != "学生") {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}	
	}
}
