package ren.yuxiang.part02.E05_jdbc;

import java.sql.*;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			connectSQL();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}

	}

	
	private static void connectSQL() throws Exception {
		// 加载驱动
		Class.forName("com.mysql.cj.jdbc.Driver");

		// 获取连接
		// jdbc:数据库类型://地址:端口/数据库名称
		String url = "jdbc:mysql://49.234.181.200:3306/db_java";
		Connection connection = DriverManager.getConnection(url, "root", "Ryx900610!");

		Statement statement = connection.createStatement();
		String sql = "select * from t_person";
		
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			System.out.println(resultSet.getString("name"));
		}
		
		statement.close();
		connection.close();
		
	}
}
















