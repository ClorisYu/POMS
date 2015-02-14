package hit.poms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 作者：dsz
 * 时间：2014-07-15 AM
 * 功能：数据库使用的工具类，获取数据库连接对象，释放数据库资源。
 * @author new
 * 
 */
public class DBManager {
	// 1、首先加载Oracle驱动程序
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接对象
	 * 
	 * @return 返回连接对象
	 */
	public static Connection getConnection() {
		Connection conn = null;
//		System.out.println("---use connection pool.");
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/ORACLE");
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
	public static Connection getConnection_old() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@10.25.85.143:1521:oracle";
		String username = "scott";
		String password = "tiger";

		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	/**
	 * 释放相关的数据库资源
	 * 
	 * @param conn  数据库连接对象
	 * @param st  语句对象
	 * @param rs  结果集对象
	 */
	public static void closeAll(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
