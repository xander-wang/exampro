package com.bjqf.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bjqf.entity.User;
public class UserMapper implements RowMapper{
	@Override
	public Object rowMapper(ResultSet rs) throws SQLException {
		//创建User对象
		User user = new User();
		//将结果集中的值拿出来赋值给对象
		user.setUserid(rs.getInt("userid"));
		user.setRoleid(rs.getInt("roleid"));
		user.setUsername(rs.getString("username"));
		user.setUserpwd(rs.getString("userpwd"));
		user.setUsertruename(rs.getString("usertruename"));
		user.setRolename(rs.getString("rolename"));
		user.setRolestate(rs.getBoolean("rolestate"));
		return user;
	}
}