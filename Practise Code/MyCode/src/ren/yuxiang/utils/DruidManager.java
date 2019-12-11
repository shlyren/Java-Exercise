package ren.yuxiang.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidManager<T> extends DaoManager<T> {

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
	@Override
	public Connection getConnectionInstance() throws Exception {
		return dataSource.getConnection();
	}
	
	public void closeDataSouce() {
		if (dataSource instanceof DruidDataSource) {
			((DruidDataSource)dataSource).close();
		}
	}
	
}
