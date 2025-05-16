package kr.or.ddit.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import oracle.jdbc.pool.OracleDataSource;

/**
 *  JDBC(Java DataBase Connectivity) 프로그래밍
 *  1. 벤더(제조사)의 드라이버를 검색하고 빌드패스에 추가(pom.xml)
 *     드라이버? java.sql 패키지의 인터페이스들에 대한 구현체 집합
 *  2. 드라이버 클래스를 메모리에 로딩 
 *  3. 드라이버를 통해 Connection 생성
 *  4. 쿼리 객체 생성 : 쿼리를 컴파일하고, 실행하고, 명령을 전달하는 역할.
 *  	1) Statement
 *  	2) PreparedStatement
 *  	3) CallableStatement 
 *  5. 쿼리(SQL) 실행 
 *  	DDL(create, alter, drop), DML(insert, select, update, delete), TCL(commit, rollback)
 *  	1) ResultSet(cursor) executeQuery : select
 *  	2) int executeUpdate : insert, update, delete
 *  6. 쿼리 결과(ResultSet) 핸들링 : while문 형태로 커서의 포인터를 이동시켜 가며 접근함.
 *  7. 쿼리 객체 종료, Connection 종료 (try-with-resource 구문으로 해결)
 */

class JDBCTest {

	@Test
	void test() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			String user = "JSY99";
			String password = "java";
			try (Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt =  conn.createStatement();
			) {
				String sql = "SELECT NAME FROM v$containers";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					String value = rs.getString("NAME");
					System.out.println(value);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void test2() {
		try {
			OracleDataSource dataSource = new OracleDataSource();
			
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			String user = "JSY99";
			String password = "java";
			dataSource.setURL(url);
			dataSource.setUser(user);
			dataSource.setPassword(password);
			
			try (Connection conn = dataSource.getConnection();
				Statement stmt =  conn.createStatement();
			) {
				String sql = "SELECT NAME FROM v$containers";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					String value = rs.getString("NAME");
					System.out.println(value);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
