package ren.yuxiang.java.base;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

	private String name;
	
	private int age;
	
	private String wrok;
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", wrok=" + wrok + "]";
	}
	
	
	
}
