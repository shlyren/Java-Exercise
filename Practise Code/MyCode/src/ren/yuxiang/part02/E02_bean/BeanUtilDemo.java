package ren.yuxiang.part02.E02_bean;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import ren.yuxiang.part02.E01_base.Person;

public class BeanUtilDemo {

	public static void main(String[] args) {
		Person person = new Person();
		Map <String, Object> map = new HashMap<>();
		map.put("name", "Yuxiang");
		map.put("age", "10");
		System.out.println(map);
		
		try {
			BeanUtils.copyProperties(person, map);
			System.out.println(person);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
