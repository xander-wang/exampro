package com.bjqf.entity;

public class User {
	//user�����ֶ�
	private int userid;
	private int roleid;
	private String username;
	private String userpwd;
	private String usertruename;
	//role���е��ֶ�
	private String rolename;
	private boolean rolestate;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getUsertruename() {
		return usertruename;
	}
	public void setUsertruename(String usertruename) {
		this.usertruename = usertruename;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public boolean isRolestate() {
		return rolestate;
	}
	public void setRolestate(boolean rolestate) {
		this.rolestate = rolestate;
	}
}
