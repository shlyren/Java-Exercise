package ren.yuxiang.part02.E05_jdbc.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PersonDaoImpl extends BaseDao implements iPersonDao {

	@Override
	public void save(Person person) {
		execute("INSERT INTO t_person (name, age) VALUES (?, ?);", person.getName(), person.getAge());
	}

	@Override
	public void delete(String personId) {
		execute("DELETE FROM t_person WHERE id = ?;", personId);
	}

	@Override
	public void update(Person person) {
		if (person.getId() == null) {
			return;
		}
		execute("UPDATE t_person SET name = ?, age = ? WHERE id = ?;", person.getName(), person.getAge(), person.getId());
	}

	@Override
	public Person get(String personId) {
		ArrayList<Person> list = executeQuery("SELECT * FROM t_person WHERE id = ?;", personId);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ArrayList<Person> getAll() {
		return executeQuery("SELECT * FROM t_person;");
	}
	
	
	/**
	 * DML
	 * @param sql SQL语句
	 * @param args 参数列表
	 * @return 是否成功
	 * @throws Exception 异常
	 */
	private boolean execute(String sql, Object... args) {
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
	 * DQL
	 * @param sql
	 * @param args SQL参数列表
	 * @return
	 * @throws Exception
	 */
	private ArrayList<Person> executeQuery(String sql, Object...args){
		System.out.println(sql);
		ArrayList<Person> list = new ArrayList<>();
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
				Person person = new Person();
				person.setId(resultSet.getString("id"));
				person.setName(resultSet.getString("name"));
				person.setAge(resultSet.getInt("age"));
				
				list.add(person);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection,  statement, resultSet);
		}
		return list;
	}

}
