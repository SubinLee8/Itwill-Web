package com.itwill.jsp2.dbcp;



import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DbcpTest {
	private static final Logger log=LoggerFactory.getLogger(DbcpTest.class);
	
	@Test
	public void testHikariCP() throws SQLException {
		//HikariCP 의 환경설정을 할 수 있는 객체를 생성.
		HikariConfig config = new HikariConfig();
		
		//데이터베이스에 접속(Connection)할 수 있는 환경 설정.
		config.setDriverClassName("oracle.jdbc.OracleDriver");
		config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		config.setUsername("jspstudy");
		config.setPassword("jspstudy");
		
		
		//  DataSource(데이터 소스 ~~ Connection Pool)객체 생성 
		//    -> 데이터베이스 서버와 물리적인 연결(Connection)이 맺어짐.
		HikariDataSource ds = new HikariDataSource(config);
		log.debug("ds={}",ds);
		
		//데이터소스(커넥션 풀)에서 이미 생성된 커넥션 객체를 빌려옴.
		Connection conn=ds.getConnection();
		Assertions.assertNotNull(conn);
		log.debug("connection={}",conn);
		
		//Statement 생성, SQL 실행(executeQuery, executeUpdate), 결과 처리
		
		//사용했던 연결(connection)을 데이터 소스(커넥션 풀)에 반환.
		conn.close();
		log.debug("커넥션 반환 성공.");
		
		
	}
}
