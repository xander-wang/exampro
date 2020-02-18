package com.bjqf.entity;

public class Function {
	private int funid;
	private String funname;
	private String funurl;
	private int funpid;
	private int funstate;
	private String parentname;
	
	public String getParentname() {
		return parentname;
	}
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	public int getFunid() {
		return funid;
	}
	public void setFunid(int funid) {
		this.funid = funid;
	}
	public String getFunname() {
		return funname;
	}
	public void setFunname(String funname) {
		this.funname = funname;
	}
	public String getFunurl() {
		return funurl;
	}
	public void setFunurl(String funurl) {
		this.funurl = funurl;
	}
	public int getFunpid() {
		return funpid;
	}
	public void setFunpid(int funpid) {
		this.funpid = funpid;
	}
	public int getFunstate() {
		return funstate;
	}
	public void setFunstate(int funstate) {
		this.funstate = funstate;
	}
	
}
