package ren.yuxiang.part02.E05_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.jupiter.api.Test;

import ren.yuxiang.part02.E05_jdbc.Dao.BaseDao;
import ren.yuxiang.part02.E05_jdbc.Dao.Person;

// 批处理
public class BatchTest extends BaseDao {
	
	@Test
	public void test1() throws Exception {
		Connection connection = getConnectionInstance();
		Person person = new Person();
		person.setAge(23);
		person.setName("新增用户1");
		PreparedStatement statement = connection.prepareStatement("INSERT INTO t_person (name, age, balance) VALUES (?, ?, ?);");
		
		long start =  System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			statement.setString(1, "user" + (i + 1));
			statement.setInt(2, 20);
			statement.setInt(3, 100 + i);
			statement.addBatch(); // 添加到批处理中
			if (i % 200 == 0) {
				statement.executeBatch(); // 执行批处理操作
				statement.clearBatch(); // 清除缓存
				statement.clearParameters(); // 清除参数
			}
		}
		
		System.out.print( (System.currentTimeMillis() - start) / 1000);
		
		close(connection, statement);
	}
}
