package com.bjqf.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bjqf.entity.FunRole;

public class FunRoleMapper implements RowMapper{
	@Override
	public Object rowMapper(ResultSet rs) throws SQLException {
		FunRole funRole = new FunRole();
		funRole.setFunid(rs.getInt("funid"));
		funRole.setFunname(rs.getString("funname"));
		funRole.setFunurl(rs.getString("funurl"));
		funRole.setFunpid(rs.getInt("funpid"));
		funRole.setFunstate(rs.getBoolean("funstate"));
		funRole.setRr(rs.getBoolean("rr"));
		funRole.setRrid(rs.getInt("rrid"));
		return funRole;
	}
}