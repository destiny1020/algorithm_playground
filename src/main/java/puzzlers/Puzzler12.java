package puzzlers;

/**
 * 关于字符数组的一些注意点
 * @author Destiny
 *
 */
public class Puzzler12 {

	public static void main(String[] args) {
		
		char[] chars = {'A', 'B', 'C'};
		
		// 利用了println方法的重载
		System.out.println(chars);
		
		// 但是字符串的连接操作并不会返回满意的结果，首先对数组类型调用了toString方法
		// 可能的打印结果：Haha [C@de6ced
		System.out.println("Haha " + chars);
		
		// primitive type null ？
//		System.out.println(null.toString());
		
		// null对象的toString方法会返回null，不会抛出NPE，这一点是个例外
		Object nullO = null;
		System.out.println(nullO + "Haha");
		
		// 还需要注意的是，调用的重载取决于传入参数的编译时类型
		Object charsObject = chars;
		// 打印的结果还是：[C@de6ced
		System.out.println(charsObject);
		
		// 合理的打印方式
		System.out.println(String.valueOf(chars));
	}
	
}
