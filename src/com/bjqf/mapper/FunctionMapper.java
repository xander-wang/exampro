package com.bjqf.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bjqf.entity.Function;

public class FunctionMapper implements RowMapper{

	@Override
	public Object rowMapper(ResultSet rs) throws SQLException {
		Function function = new Function();
		function.setFunid(rs.getInt("funid"));
		function.setFunname(rs.getString("funname"));
		function.setFunpid(rs.getInt("funpid"));
		function.setFunstate(rs.getInt("funstate"));
		function.setFunurl(rs.getString("funurl"));
		function.setParentname(rs.getString("parentname"));
		return function;
	}

}
