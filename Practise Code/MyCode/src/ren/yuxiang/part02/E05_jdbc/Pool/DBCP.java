package ren.yuxiang.part02.E05_jdbc.Pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.jupiter.api.Test;

import ren.yuxiang.part02.E05_jdbc.Dao.BaseDao;

public class DBCP extends BaseDao {
	
	// 连接池
	private static BasicDataSource dataSource = null;
	static {
		try {
			dataSource = BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(properties.getProperty(DRIVER_CLASS_NAME));
			dataSource.setUrl(properties.getProperty(URL));
			dataSource.setUsername(properties.getProperty(USER_NAME));
			dataSource.setPassword(properties.getProperty(PASSWORD));
		}
	}
	
	/**
	 * 获取JDBC连接对象，每次获取都会重新创建
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnectionInstance() throws Exception {
		return dataSource.getConnection();
	}
	
	
	
	@Test
	public void test1() throws Exception {
		Connection connection = DBCP.getConnectionInstance();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM t_person");
		while (resultSet.next()) {
			System.out.println(resultSet.getString("name"));
		}
		close(connection, statement, resultSet);
	}
	

}
