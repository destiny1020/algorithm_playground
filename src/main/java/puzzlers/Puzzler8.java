package puzzlers;

/**
 * 这个规则确实非常的绕！感觉不知道也是合情合理的
 * 
 * @author Destiny
 * 
 */
public class Puzzler8 {

	// 对于条件语句，如果操作数2和操作数3是同一类型的，那么一切OK
	// 如果操作数之一是类型T，类型T表示byte，short，char，而另外一个是int类型的常量表达式，注意这里一定需要是常量表达式，即字面值或者用final修饰的
	// 那么条件表达式的类型就是T，否则，对操作数进行二进制数字提升，而条件表达式的类型就是提升之后的类型

	public static void main(String[] args) {

		char x = 'X';
		int i = 0;

		// Output:
		// X
		// 88
		
		// 一个char类型和一个int字面值组成的操作数，返回类型应该是char，打印x
		System.out.println(true ? x : 0);
		// 一个char类型和一个int类型组成操作数，进行提升，提升得到的int类型作为返回类型，打印字符的X的ASCII码，88
		System.out.println(false ? i : x);

	}

}
