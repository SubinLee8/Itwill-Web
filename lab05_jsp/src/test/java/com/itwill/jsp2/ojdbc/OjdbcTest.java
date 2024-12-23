package com.itwill.jsp2.ojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.imageio.spi.RegisterableService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oracle.jdbc.OracleDriver;

public class OjdbcTest {
	private static final Logger log= LoggerFactory.getLogger(OjdbcTest.class);
	
	@Test
	public void testSelect() throws SQLException {
		//TODO: Oracle DB에 jspstudy계정으로 접속, POSTS 테이블 내용을 검색/출력.
		
		//Oracle DB jspstudy 계정으로 접속, POSTS 테이블 내용을 검색/출력.
		//1.Oracle JDBC 라이브러리를 등록.
		DriverManager.registerDriver(new OracleDriver());
		log.debug("오라클 드라이버 등록 성공.");
		
		//2. Oracle 접속 - Connection 객체 생성.
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jspstudy", "jspstudy");
		Assertions.assertNotNull(conn);
		//->conn 객체가 null이 아니면 단위 테스트 성공(success), 그렇지 않으면 실패(failure).
		log.debug("conn="+conn);
		
		//3. Statement 객체 생성.
		final String SELECT_ALL = "SELECT * FROM POSTS";
		PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
		
		//4. SQL 실행.
		ResultSet rs=stmt.executeQuery();
		Assertions.assertNotNull(rs);
		
		//5.결과 처리
		while(rs.next()) { //rs이 레코드를 가지고 있으면,
			int id=rs.getInt("ID"); //ID컬럼의 값을 읽음.
			String title=rs.getString("TITLE");
			String author=rs.getString("AUTHOR");
			String content=rs.getString("CONTENT");
			LocalDateTime createdTime=rs.getTimestamp("CREATED_TIME").toLocalDateTime();
			LocalDateTime modifiedTime=rs.getTimestamp("MODIFIED_TIME").toLocalDateTime();
			
			log.debug("{} | {} | {} | {} | {} | {}", id, title, author, content, createdTime, modifiedTime);
		}
		
		//6. 사용했었던 리소스들을 객체가 생성된 순서의 반대로 해제.
		rs.close();
		stmt.close();
		conn.close();
		log.debug("오라클 접속 해제");
	}
}
