package ren.yuxiang.part02.E05_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import ren.yuxiang.part02.E05_jdbc.Dao.BaseDao;

// 获取自动生成的主键
public class ReturnPkTest extends BaseDao {

	@Test
	public void test1() throws Exception {
		Connection connection = getConnectionInstance();
		String sql = "INSERT INTO t_person (name, age) VALUES ('name', 23)";
		// 可以获取主键
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.execute();
		// 获取主键
		ResultSet resultSet = statement.getGeneratedKeys();
		if (resultSet.next()) {
			// 第一列为主键
			System.out.println(resultSet.getLong(1));
		}
		
		close(connection, statement, resultSet);
	}
}
