package kr.or.ddit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariDataSource;

class PerfomanceCheckTest {
	static String driverClassName;
	static String url;
	static String username;
	static String password;
	static boolean autoCommit;
	static int minimumIdle;
	static int maximumPoolSize;
	static long connectionTimeout;
	
	@BeforeAll
	static void beforeClass() {
//		Properties : qualified name의 형태로 파일 접근
//		ResourceBundle : basename의 형태로 파일 접근
		ResourceBundle dbInfo = ResourceBundle.getBundle("kr.or.ddit.db.DBInfo");
		driverClassName = dbInfo.getString("driverClassName");
		url = dbInfo.getString("url");
		username = dbInfo.getString("username");
		password = dbInfo.getString("password");
		autoCommit = Optional.of(dbInfo.getString("autoCommit"))
							.map(Boolean::parseBoolean)
							.orElseThrow();
		minimumIdle = Optional.of(dbInfo.getString("minimumIdle"))
							.map(Integer::parseInt)
							.orElseThrow();
		
		maximumPoolSize = Optional.of(dbInfo.getString("maximumPoolSize"))
								.map(Integer::parseInt)
								.orElseThrow();
		
		connectionTimeout = Optional.of(dbInfo.getString("connectionTimeout"))
								.map(Long::parseLong)
								.orElseThrow();
	}

	@Test
	void test4() {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setAutoCommit(autoCommit);
		ds.setMinimumIdle(minimumIdle);
		ds.setMaximumPoolSize(maximumPoolSize);
		ds.setConnectionTimeout(connectionTimeout);
		long start = System.currentTimeMillis();
		for (int i = 1; i <= 100; i++) {
			try (Connection conn = ds.getConnection(); Statement stmt = conn.createStatement();) {
				String sql = "SELECT NAME FROM v$containers";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String value = rs.getString("NAME");
					System.out.println(value);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.printf("소요시간 : %dms\n", end - start);
	}

	/**
	 * 평균 : 3600ms
	 */
	@Test
	void test3() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			String user = "JSY99";
			String password = "java";
			long start = System.currentTimeMillis();
			for (int i = 1; i <= 100; i++) {
				try (Connection conn = DriverManager.getConnection(url, user, password);
						Statement stmt = conn.createStatement();) {

					String sql = "SELECT NAME FROM v$containers";
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						String value = rs.getString("NAME");
						System.out.println(value);
					}
				}
			}
			long end = System.currentTimeMillis();
			System.out.printf("소요시간 : %dms\n", end - start);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 평균 : 380ms
	 */
	@Test
	void test2() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			String user = "JSY99";
			String password = "java";
			long start = System.currentTimeMillis();
			try (Connection conn = DriverManager.getConnection(url, user, password);) {
				for (int i = 1; i <= 100; i++) {
					Statement stmt = conn.createStatement();
					String sql = "SELECT NAME FROM v$containers";
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						String value = rs.getString("NAME");
						System.out.println(value);
					}
				}
			}
			long end = System.currentTimeMillis();
			System.out.printf("소요시간 : %dms\n", end - start);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 소요시간 평균 : 350ms
	 */
	@Test
	void test() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			String user = "JSY99";
			String password = "java";
			long start = System.currentTimeMillis();
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();) {
				String sql = "SELECT NAME FROM v$containers";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String value = rs.getString("NAME");
					System.out.println(value);
				}
			}
			long end = System.currentTimeMillis();
			System.out.printf("소요시간 : %dms\n", end - start);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
