package ren.yuxiang.part02.E05_jdbc.Pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import ren.yuxiang.part02.E05_jdbc.Dao.BaseDao;
import ren.yuxiang.part02.E05_jdbc.Dao.Person;

public class DruidTest extends BaseDao {

	// 连接池
	private static DataSource dataSource = null;
	static {
		try {
			dataSource = DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
			
			DruidDataSource ds = new DruidDataSource();
			ds.setDriverClassName(properties.getProperty(DRIVER_CLASS_NAME));
			ds.setUrl(properties.getProperty(URL));
			ds.setUsername(properties.getProperty(USER_NAME));
			ds.setPassword(properties.getProperty(PASSWORD));
			dataSource = ds;
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
	public void test1() {
		try {
			Connection connection = getConnectionInstance();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM t_person");
			while (resultSet.next()) {
				System.out.println(resultSet.getString("name"));
			}
			close(connection, statement, resultSet);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
