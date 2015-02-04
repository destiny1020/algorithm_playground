package puzzlers;

/**
 * 关于字符串常量池的一点问题
 * 
 * @author Destiny
 *
 */
public class Puzzler13 {

	public static void main(String[] args) {
		
		final String pig = "length: 10";
		final String pig2 = "length: 10";
		
		String dog = "length: " + pig.length();
		
		// 第一条的打印结果为true还好解释，因为这是常量池的机制使然
		System.out.println("Pig equals Pig2: " + (pig == pig2));
		
		// 但是这一条结果为false，是因为dog的初始化方式并不是使用常量表达式初始化的，其中使用了方法调用，方法调用不属于常量表达式
		System.out.println("Pig equals dog: " + (pig == dog));
		
		// 但是还是可以使用常量池机制让pig和dog最后相等
		// 首先，这里的dog不能声明为final，否则不能进行赋值操作
		dog = dog.intern();
		System.out.println("Pig equals dog: " + (pig == dog));
		
		// 另外还有一个隐藏的运算符优先级的问题在于：
		System.out.println("Pig equals dog: " + pig == dog);  // 仅仅输出false
		
		//因为+无论是执行字符串连接还是primitive运算时，优先级都是高于==操作符的！
		
	}
	
}
