package ren.yuxiang.part02.E05_jdbc.Dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class BaseDao {

	private static final Properties properties = new Properties();
	
	private static final String driverClassName = "driverClassName";
	private static final String url = "url";
	private static final String username = "username";
	private static final String password = "password";
	
	static {
		try {
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
			properties.load(inputStream);
			Class.forName(properties.getProperty(driverClassName));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取JDBC连接对象，每次获取都会重新创建
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnectionInstance() throws Exception {
		return DriverManager.getConnection(
				properties.getProperty(url), 
				properties.getProperty(username), 
				properties.getProperty(password)
			);
	}
	
	
	public static void close(Connection connection, Statement statement) {
		close(connection, statement, null);
	}
	public static void close(Connection connection, Statement statement, ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
