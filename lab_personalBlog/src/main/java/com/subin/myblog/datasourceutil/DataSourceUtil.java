package com.subin.myblog.datasourceutil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.subin.myblog.datasourceutil.*;

public enum DataSourceUtil {
	INSTANCE; //singleton 객체
	
	//필드
	private HikariConfig config;
	private HikariDataSource ds;
	
	//생성자
	DataSourceUtil() {
		//HikariCP의 환경 설정(configuration)을 담당하는 HikariConfig 객체 생성.
		config=new HikariConfig();
		
		//커넥션 풀(데이터 소스)의 환경 설정.
		config.setDriverClassName("oracle.jdbc.OracleDriver");
		config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		config.setUsername("jspstudy");
		config.setPassword("jspstudy");
		
		//데이터 소스 객체 생성.
		ds=new HikariDataSource(config);
		
		
	}
	
	//데이터 소스 getter 메소드
	public HikariDataSource getDataSource() {
		return ds;
	}
	
	//편의(utility) 메서드.
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void close(Connection conn,Statement stmt) {
		close(conn,stmt,null);
	}
	
	
	
}
