package ren.yuxiang.part01.E01;

public class Grammar {

	public static void main(String[] args) {
		int[] array = {1, 2, 3, 5};
		System.out.println(array.length);
		System.out.println(plus(1, 2, 10, "kkk"));;
	}
	
	public static String plus(Object...objects) {
		String string = "";
		for (Object object : objects) {
			string += object;
		}
		return string;
	}

}
