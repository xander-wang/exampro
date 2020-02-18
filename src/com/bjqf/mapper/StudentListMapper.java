package com.bjqf.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bjqf.entity.StudentList;
public class StudentListMapper implements RowMapper{
	@Override
	public Object rowMapper(ResultSet rs) throws SQLException {
		//创建对象
		StudentList studentList = new StudentList();
		studentList.setSpid(rs.getString("spid"));
		studentList.setUserid(rs.getInt("userid"));
		studentList.setPname(rs.getString("pname"));
		studentList.setRightcount(rs.getInt("rightcount"));
		studentList.setErrorcount(rs.getInt("errorcount"));
		return studentList;
	}
}
