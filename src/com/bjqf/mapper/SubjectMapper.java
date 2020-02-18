package com.bjqf.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bjqf.entity.Subject;

public class SubjectMapper implements RowMapper{
	
	@Override
	public Object rowMapper(ResultSet rs) throws SQLException {
		//����Role����
		Subject Subject = new Subject();
		Subject.setSid(rs.getInt("sid"));
		Subject.setScontent(rs.getString("scontent"));
		Subject.setSa(rs.getString("sa"));
		Subject.setSb(rs.getString("sb"));
		Subject.setSc(rs.getString("sc"));
		Subject.setSd(rs.getString("sd"));
		Subject.setSkey(rs.getString("skey"));
		Subject.setSstate(rs.getBoolean("sstate"));;
		return Subject;
	}
}
