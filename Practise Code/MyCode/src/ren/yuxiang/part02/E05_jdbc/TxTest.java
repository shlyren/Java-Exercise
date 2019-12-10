package ren.yuxiang.part02.E05_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.jupiter.api.Test;

import ren.yuxiang.part02.E05_jdbc.Dao.BaseDao;

// 事务
public class TxTest extends BaseDao {

	@Test
	public void test1() throws Exception {
		Connection connection = getConnectionInstance();

		String sql = "SELECT * FROM t_person WHERE name = ? AND balance >= ?";
		PreparedStatement statement = connection.prepareStatement(sql);		
		statement.setString(1, "user1");
		statement.setInt(2, 1000);
		
		ResultSet resultSet = statement.executeQuery();
		if (!resultSet.next()) {
			close(connection, statement, resultSet);
			throw new RuntimeException("余额不足");
		}
		
		try {
			// 设置事务手动提交
			connection.setAutoCommit(false);
			
			sql = "UPDATE t_person SET balance = balance - ? WHERE name = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, 1000);
			statement.setString(2, "user1");
			if (statement.execute()) {
				throw new RuntimeException("操作失败");
			}
			// 模拟异常
//			int i = 1/0;
			
			sql = "UPDATE t_person SET balance = balance + ? WHERE name = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, 1000);
			statement.setString(2, "user2");
			if (statement.execute()) {
				throw new RuntimeException("操作失败");
			}else {
				System.out.println("操作成功");
			}
			
			// 提交事务
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚
			connection.rollback();
		}finally {
			close(connection, statement, resultSet);
		}
		
		
		
	}
}
