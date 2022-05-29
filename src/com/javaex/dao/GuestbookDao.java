package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVo;

public class GuestbookDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// 생성자

	// 메서드 getter/setter

	// 메서드 일반

	// --DB연결 메서드
	private void getConnection() {

		try {
			// JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// --자원정리 메서드
	private void close() {

		try {

			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 등록
	public int add(GuestbookVo guestbookVo) {

		int count = -1;

		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행

			// SQL문 준비
			String query = "";
			query += " insert into guestbook ";
			query += " values (seq_guestbook_no.nextval, ?, ?, ?, ?) ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, guestbookVo.getName());
			pstmt.setString(2, guestbookVo.getPassword());
			pstmt.setString(3, guestbookVo.getContent());
			pstmt.setString(4, guestbookVo.getRegDate());
			
			
			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;

	}

	// 등록 리스트
	
	public List<GuestbookVo> select() {

		// 리스트 만들기
		List<GuestbookVo> guestbooklist = new ArrayList<GuestbookVo>();

		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행

			// SQL문 준비
			String query = "";
			query += " select  no ";
			query += "        ,name ";
			query += "        ,password ";
			query += "        ,content ";
			query += "        ,reg_date ";
			query += " from guestbook ";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			// 반복문으로 Vo 만들기 List에 추가하기
			while (rs.next()) {

				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");

				GuestbookVo guestbookVo = new GuestbookVo(no, name, password, content, regDate);

				guestbooklist.add(guestbookVo);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return guestbooklist;

	}
	// 삭제
	public int delete(int no) {
		int count = 0;
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " delete from guestbook ";
			query += " where no = ? ";
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setInt(1, no);// ?(물음표) 중 1번째, 순서중요

			count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}
}
