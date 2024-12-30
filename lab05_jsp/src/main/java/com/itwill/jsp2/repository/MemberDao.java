package com.itwill.jsp2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.datasourceutil.DataSourceUtil;
import com.itwill.jsp2.domain.Member;
import com.zaxxer.hikari.HikariDataSource;
import static com.itwill.jsp2.datasourceutil.DataSourceUtil.close;

public enum MemberDao {
	INSTANCE;
	
	private static final Logger log = LoggerFactory.getLogger(MemberDao.class);
	private HikariDataSource ds=DataSourceUtil.INSTANCE.getDataSource();
	
	
	private final String SQL_UPDATE_POINTS = "update members set points=points+ ?, modified_time=systimestamp where username=?";

	public int updatePoints(int points, String username) {
		int result=0;
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=ds.getConnection();
			stmt=conn.prepareStatement(SQL_UPDATE_POINTS);
			stmt.setInt(1, points);
			stmt.setString(2, username);
			stmt.executeUpdate();
			result=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(conn,stmt);
		}
		return result;
		
	}
	
	
	
	//회원가입에 필요한 SQL, 메서드
	private final String SQL_INSERT_INTO_MEMBERS = "insert into members (username, password, email, created_time, modified_time) values(?, ?, ?, systimestamp, systimestamp)";
	public int insert(Member member) {
		int result=0;
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=ds.getConnection();
			stmt=conn.prepareStatement(SQL_INSERT_INTO_MEMBERS);
			stmt.setString(1, member.getUserName());
			stmt.setString(2, member.getPassWord());
			stmt.setString(3, member.getEmail());
			stmt.executeUpdate();
			result=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(conn,stmt);
		}
		return result;
		
	}
	
	
	//로그인에 필요한 SQL, 메서드
	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD="select * from members where username=? and password=?";
	public Member select(String username, String password) {
		log.debug(SQL_SELECT_BY_USERNAME_AND_PASSWORD);
		log.debug("select(username={}, password={})", username, password);
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Member member=null;
		try {
			conn=ds.getConnection();
			stmt=conn.prepareStatement(SQL_SELECT_BY_USERNAME_AND_PASSWORD);
			stmt.setString(1, username);
			stmt.setString(2, password);
			rs=stmt.executeQuery();
			if(rs.next()) {
				member=getMemberFromResultSet(rs);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
	
	Member getMemberFromResultSet(ResultSet rs) throws SQLException {
		int id= rs.getInt(1);
		String username=rs.getString(2);
		String password=rs.getString(3);
		String email=rs.getString(4);
		int points=rs.getInt(5);
		LocalDateTime createdTime=rs.getTimestamp(6).toLocalDateTime();
		LocalDateTime modifiedTime=rs.getTimestamp(7).toLocalDateTime();
		return Member.Builder().id(id).userName(username)
				.passWord(password).email(email).points(points).createdTime(createdTime).modifiedTime(modifiedTime).build();
	}
	
	
}
