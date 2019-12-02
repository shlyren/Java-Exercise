package ren.yuxiang.java.annotation;

/**
 * 注解
 * @author yuxiang
 *
 */
public class Demo {

	public static void main(String[] args) {

		Class<Employee> clsClass = Employee.class;
//		Annotation[] annotations = clsClass.getAnnotations();
//		for (Annotation annotation : annotations) {
//			System.out.println(annotation);
//		}

		VIP vip = clsClass.getAnnotation(VIP.class);
		System.out.println(vip.name());
		System.out.println(vip.age());
		System.out.println(vip.partner());

	}
}
