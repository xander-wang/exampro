package com.bjqf.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountMapper implements RowMapper{

	@Override
	public Object rowMapper(ResultSet rs) throws SQLException {
		Integer count = new Integer(rs.getInt("count"));  //查询结果为intget型，单个数据也不用创建对象
		return count;
	}

}
