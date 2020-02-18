package com.bjqf.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bjqf.entity.Paper;

public class PaperMapper implements RowMapper{

	@Override
	public Object rowMapper(ResultSet rs) throws SQLException {
		Paper paper = new Paper();
		paper.setSid(rs.getInt("sid"));
		paper.setPname(rs.getString("pname"));
		paper.setSid(rs.getInt("sid"));
		paper.setPcount(rs.getInt("pcount"));
		return paper;
	}

}
