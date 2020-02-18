package com.bjqf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bjqf.mapper.RowMapper;

public class JDBCUtil {
	//��дJDBC����
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://192.168.31.156:3306/exampro";
	private static final String USER="root";//��¼���ݿ���û���
	private static final String PWD = "123456";//��¼���ݿ������
	/**
	 * ��������ɾ�ķ���
	 * @param sql
	 * @param params
	 */
	public static int executeUpdate(String sql,Object...params){
		int num = 0;
		try {
			//��������
			Class.forName(DRIVER);
			//��������
			Connection conn = DriverManager.getConnection(URL,USER,PWD);
			//Ԥ����sql���
			PreparedStatement pstmt = conn.prepareStatement(sql);
			/*
			 * ��ռλ����ֵ
			 * ռλ���±��1��ʼ�������±��0��ʼ
			 */
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject((i+1), params[i]);
			}
			//ִ��sql���
			num = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
		}
		return num;
	}
	
	/**
	 * ��װ�Ĳ�ѯ����
	 * @param sql
	 * @param rowMapper
	 * @param params
	 * @return
	 */
	public static List executeQuery(String sql,RowMapper rowMapper,Object...params){
		//��������
		List list = new ArrayList();
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL,USER,PWD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			setParams(pstmt, params);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				//��ȡ������е�����
				Object obj = rowMapper.rowMapper(rs);
				//��������ӵ��������
				list.add(obj);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * �����ĸ�ռλ����ֵ�ķ���
	 * @param pstmt
	 * @param params
	 * @throws SQLException
	 */
	public static void setParams(PreparedStatement pstmt,Object...params) throws SQLException{
		//��ռλ����ֵ�����ڲ�ѯ�������ܲ�����ռλ�������ѯ��������
		if(params != null){
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject((i+1), params[i]);
			}
		}
	}

}

