package ren.yuxiang.utils;

import java.sql.Connection;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidManager<T> extends DaoManager<T> {

	// 连接池
	private static DataSource dataSource = null;
	
	/**
	 * 获取JDBC连接对象，每次获取都会重新创建
	 * @return
	 * @throws Exception
	 */
	@Override
	public Connection getConnectionInstance() throws Exception {
		openDataSouce(); // 打开连接池，防止连接池已被关闭
		return dataSource.getConnection();
	}
	
	/**
	 * 打开连接池
	 */
	public void openDataSouce() {
		// 如果连接池存在 则不打开
		if (isDataSourceOpen()) return;
		
		System.out.println("创建连接池");
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
	 * 关闭连接池
	 */
	public void closeDataSouce() {
		if (isDataSourceOpen()) {
			((DruidDataSource)dataSource).close();
		}
	}
	
	
	/**
	 * 判断连接池是否已打开
	 * @return
	 */
	private boolean isDataSourceOpen() {
		if (dataSource instanceof DruidDataSource) {
			DruidDataSource druidDataSource = (DruidDataSource)dataSource;
			return !druidDataSource.isClosed();
		}
		return false;
	}
	
}
