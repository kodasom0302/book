package com.javaex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelectAll {
	
	public static void main(String[] args) {
		
		List<BookVo> bookList=new ArrayList<BookVo>();
	      
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
	         
	      // 3. SQL문 준비 / 바인딩 / 실행
	         String query="";
				query += " select book_id, ";
				query += "		  title, ";
				query += "		  pubs, ";
				query += "		  pubs_date, ";
				query += "		  author_id, ";
				query += "		  author_name, ";
				query += "		  author_desc ";
				query += " from book b, author a ";
	         
				pstmt=conn.prepareStatement(query);
				
				rs=pstmt.executeQuery();
				
				while (rs.next()) {
					int bookId=rs.getInt("book_id");
					String title=rs.getString("title");
					String pubs=rs.getString("pubs");
					String pubsDate=rs.getString("pubs_date");
					int authorId=rs.getInt("author_id");
					String name=rs.getString("author_name");
					String desc=rs.getString("author_desc");
					
					//Vo 묶기
					BookVo bookVo=new BookVo(bookId, title, pubs, pubsDate, authorId, name, desc);
					
					//리스트에 추가
					bookList.add(bookVo);
				}
	         
	      // 4.결과처리
				for (int i=0; i<bookList.size(); i++) {
					int bookId=bookList.get(i).getBookId();
					String title=bookList.get(i).getTitle();
					String pubs=bookList.get(i).getPubs();
					String pubsDate=bookList.get(i).getPubDate();
					int authorId=bookList.get(i).getAuthorId();
					String name=bookList.get(i).getAuthorName();
					String desc=bookList.get(i).getAuthorDesc();
					
					System.out.println(bookId+".\t"+title+".\t"+pubs+".\t"+pubsDate+".\t"+authorId+".\t"+name+"\t"+desc);
				}
	         
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
