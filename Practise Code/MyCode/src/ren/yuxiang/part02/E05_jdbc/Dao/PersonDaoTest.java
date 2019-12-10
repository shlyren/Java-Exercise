package ren.yuxiang.part02.E05_jdbc.Dao;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PersonDaoTest {

	private iPersonDao personDao = new PersonDaoImpl();
	
	@Test
	void testSave() {
		Person person = new Person();
		person.setAge(23);
		person.setName("新增用户1");
		personDao.save(person);
	}

	@Test
	void testDelete() {
		personDao.delete("23");
	}

	@Test
	void testUpdate() {
		Person person = personDao.get("23");
		person.setName("更新");
		person.setAge(21);
		personDao.update(person);
	}

	@Test
	void testGet() {
		Person person = personDao.get("22");
		System.out.println(person);
	}

	@Test
	void testGetAll() {
		ArrayList<Person> list = personDao.getAll();
		
		for (Person person : list) {
			System.out.println(person);
		}
	}

}
