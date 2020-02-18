package com.bjqf.util;

import java.util.List;

public class PageModel {
	private int pageNo;//定义当前页数
	private int pageSize;//定义每一页显示多少数据
	private List dataList;//定义数据列表
	private int count;//数据库中的总数
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * ��ȡ��ҳ��
	 * @return
	 */
	public int getTotalPage() {
		/*
		 * 
		 * 三位表达式计算总页数，根据数据库总数/size（整除） 或者不整除就+1
		 */
		return count%pageSize == 0 ? count/pageSize : count/pageSize + 1;
	}
	/**
	 * 获取上一页方法
	 * @return
	 */
	public int getPrePage() {
		//如果停留在第一页，那么当用户点击上一页，还是显示第一页
		if(this.pageNo <= 1){
			return 1;
		}else{
			//如果不是第一页，就显示上一页
			return this.pageNo - 1;
		}
	}
	/**
	 * 获取下一页方法
	 * @return
	 */
	public int getNextPage() {
		//当前页大于等于总页数，点击也只显示最后一页内容
		if(this.pageNo >= this.getTotalPage()){
			return this.getTotalPage();
		}else{
			return this.pageNo + 1;
		}
	}
}
