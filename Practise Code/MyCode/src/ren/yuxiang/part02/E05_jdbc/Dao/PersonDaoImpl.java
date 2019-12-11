package ren.yuxiang.part02.E05_jdbc.Dao;

import java.sql.ResultSet;
import java.util.List;

import ren.yuxiang.part02.E05_jdbc.util.DaoManager;

public class PersonDaoImpl implements iDabaBaseDao<Person> {

	private DaoManager<Person> manager = new DaoManager<>();
	
	public PersonDaoImpl() {
		
		manager.setConverable(new DaoManager.Converable<Person>() {
			@Override
			public Person convert(ResultSet resultSet) throws Exception {
				Person person = new Person();
				person.setId(resultSet.getString("id"));
				person.setName(resultSet.getString("name"));
				person.setAge(resultSet.getInt("age"));
				return person;
			}
		});
	}
	
	@Override
	public void save(Person person) {
		manager.execute("INSERT INTO t_person (name, age) VALUES (?, ?);", person.getName(), person.getAge());
	}

	@Override
	public void delete(String personId) {
		manager.execute("DELETE FROM t_person WHERE id = ?;", personId);
	}

	@Override
	public void update(Person person) {
		if (person.getId() == null) return;
		manager.execute("UPDATE t_person SET name = ?, age = ? WHERE id = ?;", person.getName(), person.getAge(), person.getId());
	}

	@Override
	public Person get(String personId) {
		List<Person> list = manager.executeQuery("SELECT * FROM t_person WHERE id = ?;", personId);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public List<Person> getAll() {
		return manager.executeQuery("SELECT * FROM t_person;");
	}


}
