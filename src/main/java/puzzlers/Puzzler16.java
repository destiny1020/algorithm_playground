package puzzlers;

/**
 * 还是和Unicode相关，同时注意行分隔符在不同平台上的表示不同，尽量使用平台无关的实现方式
 * @author Destiny
 *
 */
public class Puzzler16 {

	public static void main(String[] args) {
		
		// Unicode字符之后的两个slash必不可少，因为和Puzzler14一样，程序对Unicode字符不会做任何特殊处理，只会直接进行替换
		// 因此下面的Unicode字符之后的所有字符会另起一行，没有接下来的两个slash，那么程序时不会通过编译的
		// Note: \u000A //is Unicode representation of linefeed (LF)
		char c = 0x000A;
		
		// 同时，下面的这行代码是Platform-dependent的，在Linux平台上，下面的代码会打印出两个行分隔符，但是在Windows下只会有一个
		// 为了避免这种平台相关性，可以连续使用两行下述代码来完成两个行分隔符的打印
		System.out.println(c);
		
		// 或者使用printf中的%n，注意是%n，而不是\n
		System.out.printf("%n%n");
		
	}
	
}
