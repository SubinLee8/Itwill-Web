package com.itwill.jsp2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static com.itwill.jsp2.datasourceutil.DataSourceUtil.close;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.datasourceutil.DataSourceUtil;
import com.itwill.jsp2.domain.Post;
import com.zaxxer.hikari.HikariDataSource;

// 웹 MVC 아키텍쳐에서 영속성/저장소 계층(persistance/repasitory layer) 계층을 담당하는 객체.
// DB CRUD 작업 기능을 갖고 있는 싱글턴 객체.
// DAO(Data Access Object)
public enum PostDao {
	INTANCE; // 싱글턴 객체.

	private static final Logger log = LoggerFactory.getLogger(PostDao.class);
	private final HikariDataSource ds = DataSourceUtil.INSTANCE.getDataSource();

	// 포스트 목록 전체 검색에서 사용할 SQL
	private static final String SQL_SELECT_ALL = "select * from posts order by id desc ";

	public List<Post> selelct() {
		log.debug(SQL_SELECT_ALL);

		List<Post> posts = new ArrayList<Post>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Post post = toPostFromResultSet(rs);
				posts.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, rs);
		}
		return posts;
	}
	
	private static final String SQL_SEARCH_BY_TITLE="select * from posts where ";
	

	private Post toPostFromResultSet(ResultSet rs) throws SQLException {
		int id = rs.getInt("ID");
		String title = rs.getString("TITLE");
		String author = rs.getString("AUTHOR");
		String content = rs.getString("CONTENT");
		LocalDateTime createdTime = rs.getTimestamp("CREATED_TIME").toLocalDateTime();
		LocalDateTime modifiedTime = rs.getTimestamp("MODIFIED_TIME").toLocalDateTime();
		return Post.builder().id(id).title(title).content(content).createdTime(createdTime).modifiedTime(modifiedTime)
				.author(author).build();
	}

	
	// 포스트 저장(새 글 작성)에서 필요한 SQL 문장을 선언.

	private static final String SQL_INSERT = String.format("insert into posts (title, author, content, created_time, modified_time) values(?, ?, ?, systimestamp, systimestamp)");

	public int insert(Post post) {
		int result=0;
		String title=post.getTitle();
		String author=post.getAuthor();
		String content=post.getContent();
		
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=ds.getConnection();
			stmt=conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, title);
			stmt.setString(2, author);
			stmt.setString(3, content);
			stmt.executeUpdate();
			result=1;
		} catch (SQLException e) {
			
		}
		finally {
			close(conn,stmt);
		}
		
		return result;
	}
	
	
	private static final String SQL_SELECT_BY_ID = "select * from posts where id=?";
	public Post select(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Post post=null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			//ResultSet에 레코드가 있으면
			if (rs.next()) {
				post = toPostFromResultSet(rs);
			}
			log.debug("postDetail:id={}",post.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, rs);
		}
		return post;
				
	}
	
	private final String SQL_DELETE_BY_ID = "delete from posts where id=?";
	
	public int delete(int id) {
		int result=0;
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=ds.getConnection();
			stmt=conn.prepareStatement(SQL_DELETE_BY_ID);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			result=1;
		} catch (SQLException e) {
			
		}
		finally {
			close(conn,stmt);
		}
		
		
		return result;
	}
	
	private final String SQL_UPDATE_BY_ID = "update posts set title=?, content=?, modified_time=systimestamp where id=? ";
	public int update(Post post) {
		int result=0;
		Connection conn=null;
		PreparedStatement stmt = null;
		try {
			conn=ds.getConnection();
			stmt=conn.prepareStatement(SQL_UPDATE_BY_ID);
			stmt.setString(1, post.getTitle());
			stmt.setString(2, post.getContent());
			stmt.setInt(3, post.getId());
			stmt.executeUpdate();
			result=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn,stmt);
		}
		
		return result;
	}
	
	private final String SQL_SELECT_BY_TITLE="select * from posts where upper(title) like upper('%'||?||'%') order by id desc";
	private final String SQL_SELECT_BY_CONTENT="select * from posts where upper(content) like upper('%'||?||'%') order by id desc";
	private final String SQL_SELECT_BY_TITLE_AND_CONTENT="select * from posts where upper(title) like upper('%'||?||'%') "
			+ "or upper(content) like upper('%'||?||'%') order by id desc";
	private final String SQL_SELECT_BY_AUTHOR="select * from posts where upper(author) like upper('%'||?||'%') order by id desc";
	
	public List<Post> select(String category, String keyword) {
		log.debug("category={}",category);
		log.debug("keyword={}",keyword);
		List<Post> posts = new ArrayList<Post>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			if(category.equals("t")) {
				stmt = conn.prepareStatement(SQL_SELECT_BY_TITLE);
				stmt.setString(1, keyword);
			}
			else if(category.equals("tc")) {
				stmt = conn.prepareStatement(SQL_SELECT_BY_TITLE_AND_CONTENT);
				stmt.setString(1, keyword);
				stmt.setString(2, keyword);
			}
			else if(category.equals("c")) {
				stmt = conn.prepareStatement(SQL_SELECT_BY_CONTENT);
				stmt.setString(1, keyword);
			}
			else {
				stmt = conn.prepareStatement(SQL_SELECT_BY_AUTHOR);
				stmt.setString(1, keyword);
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				Post post = toPostFromResultSet(rs);
				posts.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, rs);
		}
		return posts;
	}
}
