package com.bjqf.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;



public interface RowMapper {
	//构建抽象方法（接口）
	public abstract Object rowMapper(ResultSet rs) throws SQLException;
}
