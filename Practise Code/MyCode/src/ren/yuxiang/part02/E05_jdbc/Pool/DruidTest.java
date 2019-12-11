package ren.yuxiang.part02.E05_jdbc.Pool;


import java.sql.ResultSet;

import org.junit.Test;

import ren.yuxiang.part02.E05_jdbc.Dao.Person;
//import ren.yuxiang.utils.DaoManager;
import ren.yuxiang.utils.DruidManager;

public class DruidTest {

	
	@Test
	public void testName() throws Exception {
	
		DruidManager<Person> pool = new DruidManager<>();
		pool.setConverable(new DruidManager.Converable<Person>() {
			
			@Override
			public Person convert(ResultSet resultSet) throws Exception {
				Person person = new Person();
				person.setId(resultSet.getString("id"));
				person.setName(resultSet.getString("name"));
				person.setAge(resultSet.getInt("age"));
				return person;
			}
		});
		
		System.out.println(pool.executeQuery("SELECT * FROM t_person"));

		pool.closeDataSouce();
		System.out.println(pool.executeQuery("SELECT * FROM t_person WHERE id = 25"));
		
		
	}
	@Test
	public void testName1() throws Exception {
	
		DruidManager<Person> pool = new DruidManager<>();
		pool.setConverable(new DruidManager.Converable<Person>() {
			
			@Override
			public Person convert(ResultSet resultSet) throws Exception {
				Person person = new Person();
				person.setId(resultSet.getString("id"));
				person.setName(resultSet.getString("name"));
				person.setAge(resultSet.getInt("age"));
				return person;
			}
		});
		
		System.out.println(pool.executeQuery("SELECT * FROM t_person"));
		System.out.println(pool.executeQuery("SELECT * FROM t_person WHERE id = 25"));
		pool.closeDataSouce();
		
		new DruidManager<>();
		
	}
}
