package puzzlers;

public class Puzzler22 {

	public static void main(String[] args) {
		
		System.out.print("explorer:");
		http://www.google.com;
		System.out.println(":chrome");
		
		// 以上代码最后会打印：explorer::chrome
		// 放到IDE中还算是明显，如果不放在IDE中，我会认为这段程序根本就是有问题的程序！
		// 不要忘记了Java中的label，它用来支持跳出嵌套的循环，或者是作为goto语句的目的？goto仅仅是保留字
		next:
		System.out.println("1");
		System.out.println("2");
//		goto next;	goto是Java中的保留字，没有具体的用途
		System.out.println("2");
		
	}
	
}
