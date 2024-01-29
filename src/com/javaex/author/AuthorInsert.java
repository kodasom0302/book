package com.javaex.author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorInsert {

	public static void main(String[] args) {
		
		//번호 알아서, 이름 : 고다솜, 설명 : 막내반장
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			/*
			String id="book";
			String pw="book";
			conn = DriverManager.getConnection(url, id, pw);
			*/
		// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query="";
			query += " insert into author ";	//자동 띄어쓰기 안되니 양끝 한 칸씩 띄기
			query += " values (null, ?, ?) ";	//데이터가 오는 곳은 ? 표시
												// ; 지우기
			//바인딩 - ?에 실제값 매칭
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, "양정원");
			pstmt.setString(2, "세고최계 리더");
			
			//실행
			int count=pstmt.executeUpdate();
			
		// 4.결과처리
			System.out.println(count+"건 등록 되었습니다.");
			
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
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

	}

}
