package ren.yuxiang.part02.E05_jdbc.Dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

	String id;
	String name;
	Integer age;
	
	
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	
}
