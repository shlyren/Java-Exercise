package ren.yuxiang.part02.E02_bean;

import java.beans.BeanInfo;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ren.yuxiang.part02.E01_base.Person;

public class main {

	public static void main(String[] args) {
		
		try {

			Map <String, Object>map = new HashMap<>();
			map.put("name", "Yuxiang");
			map.put("age", 18);
			map.put("height", 180);
			System.out.println(jsonToMode(Person.class, map));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void map2Model() throws Exception {
		
		Map <String, Object>map = new HashMap<>();
		map.put("name", "Yuxiang");
		map.put("age", 18);
		map.put("height", 180);
		
		BeanInfo  beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
		PropertyDescriptor[] pdsDescriptors = beanInfo.getPropertyDescriptors();
		Person person = new Person();
		for (PropertyDescriptor pd : pdsDescriptors) {
			String nameString = pd.getName();

			Object valueObject = map.get(nameString);
			Method setter = pd.getWriteMethod();
			
			if (valueObject != null && setter != null) {
				setter.invoke(person, valueObject);
			}
			
		}

		System.out.println(person.toString());
	}
	
	@SuppressWarnings("unused")
	private static void model2Map() throws Exception {
		Person person = new Person();
		person.setName("Yuxiang");
		person.setAge(20);
		
		Map <String, Object> map = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
		PropertyDescriptor[] pDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : pDescriptors) {
			Method method = propertyDescriptor.getReadMethod();
			map.put(propertyDescriptor.getName(), method.invoke(person));
		}
		
		System.out.println(map);
		
	}
	
	private static <T> T jsonToMode(Class<T> class1, Map<String, Object> map) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(class1, Object.class);
		
		PropertyDescriptor[] pdsDescriptors = beanInfo.getPropertyDescriptors();
		T t = class1.newInstance();
		for (PropertyDescriptor pd : pdsDescriptors) {
			String nameString = pd.getName();

			Object valueObject = map.get(nameString);
			Method setter = pd.getWriteMethod();
			
			if (valueObject != null && setter != null) {
				setter.invoke(t, valueObject);
			}
			
		}
		return t;
	}
}
