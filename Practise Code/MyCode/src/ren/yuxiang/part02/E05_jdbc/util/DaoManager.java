package ren.yuxiang.part02.E05_jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class DaoManager<T> {
	
	public interface Converable<T> {
		/**
		 * 将结果集转换为真实的实例对象
		 * @param resultSet 结果集
		 * @return
		 * @throws Exception
		 */
		public T convert(ResultSet resultSet) throws Exception;
	}
	
	private Converable<T> converable;
	
	public static final Properties properties = new Properties();
	
	public static final String DRIVER_CLASS_NAME = "driverClassName";
	public static final String URL = "url";
	public static final String USER_NAME = "username";
	public static final String PASSWORD = "password";
	
	static {
		try {
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
			properties.load(inputStream);
			Class.forName(properties.getProperty(DRIVER_CLASS_NAME));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void setConverable(Converable<T> converable) {
		this.converable = converable;
	}
	
	/**
	 * 获取JDBC连接对象，每次获取都会重新创建
	 * @return
	 * @throws Exception
	 */
	public Connection getConnectionInstance() throws Exception {
		return DriverManager.getConnection(
				properties.getProperty(URL), 
				properties.getProperty(USER_NAME), 
				properties.getProperty(PASSWORD)
			);
	}
	
	/**
	 * DML（增删改）
	 * @param sql SQL语句
	 * @param args 参数列表
	 * @return 是否成功
	 * @throws Exception 异常
	 */
	public boolean execute(String sql, Object... args) {
		System.out.println(sql);
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnectionInstance();
			statement = connection.prepareStatement(sql);
			int i = 1;
			for (Object object : args) {
				statement.setObject(i++, object);
			}
			
			boolean result = statement.execute();
			System.out.println("操作" + (result == false ? "成功" : "失败"));
			return result == false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(connection, statement);
		}
		return false;
	}
	
	/**
	 * DQL（查）
	 * @param <T>
	 * @param sql
	 * @param args SQL参数列表
	 * @return 结果
	 * @throws Exception
	 */
	public List<T> executeQuery(String sql, Object... args) {
		if (converable == null) {
			throw new RuntimeException("You must implements Converable.convert method.");
		}
		System.out.println(sql);
		ArrayList<T> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			// 加载驱动
			connection = getConnectionInstance();
			statement = connection.prepareStatement(sql);
			int i = 1;
			for (Object object : args) {
				statement.setObject(i++, object);
			}
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				T object = converable.convert(resultSet);
				list.add(object);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection,  statement, resultSet);
		}
		return list;
	}
	
	private void close(Connection connection, Statement statement) {
		close(connection, statement, null);
	}
	private void close(Connection connection, Statement statement, ResultSet resultSet) {
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