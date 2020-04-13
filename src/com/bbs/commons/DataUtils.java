package com.bbs.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���ݿ⹤����
 * @author lindy
 *
 */
public class DataUtils {
	// 1.������Ҫ�õ����ַ�������������ֵ
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/bbs?"
			+ "characterEncoding=UTF-8&useUnicode=true";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "9999";
	
	// 2.����һ��������ݿ����ӵķ���
	public static Connection createConnection() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// 3.����һ���ͷ���Դ�ķ���
	public static void closeAll(Connection conn,PreparedStatement pst
			,ResultSet rs) {
		try {
			if(rs != null)
				rs.close();
			if(pst != null)
				pst.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 4.����ͨ�õ�����ɾ���ķ���
	public static int executeUpdate(String sql,Object ... parms) {
		Connection conn = createConnection();
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			// ����������ݲ�Ϊ��
			if (parms != null) {
				// Ϊռλ����ֵ
				for (int i = 0; i < parms.length; i++) {
					pst.setObject((i+1), parms[i]);
				}
			}
			// ���÷��������ؽ��
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, pst, null);
		}
		return 0;
	}
	
	// 5.����ͨ�õĲ�ѯ����
	public static ResultSet queryAll(String sql,Object ... parms) {
		ResultSet rs = null;
		Connection conn = createConnection();
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			// ����������ݲ�Ϊ��
			if (parms != null) {
				// Ϊռλ����ֵ
				for (int i = 0; i < parms.length; i++) {
					pst.setObject((i+1), parms[i]);
				}
			}
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	//创建一个获取系统当前时间的方法
    // 定义一个专门负责日期格式转换的对象
	static Date  date = new Date();
	static String invitationid="";
	static SimpleDateFormat format=new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss");
    public static String GetId() {	
    	String id[]=format.format(date).split("-");
		for (int i = id.length-1; i>=0; i--) {
			invitationid=id[i]+invitationid;
		}   
		return invitationid;
	}
    //获得系统当前时间的方法
    public static Date getDate() {   
		return date;   	
    }
}
