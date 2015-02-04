package puzzlers;

/**
 * 这个谜题的目标和上题正好相反，是需要让 x = x + i这样的表达式合法，而x += i这样的不合法
 * 
 * @author Destiny
 *
 */
public class Puzzler10 {

	public static void main(String[] args) {
		
		// 考虑复合赋值表达式的规则限制，x和i都必须是基本类型，除了一个特殊情况，当String作为左操作数时 s += i实际上是字符串连接操作
		// 因此，可以考虑引用类型
		// 而此时右操作数会先调用toString方法，然后进行连接
		Object x = "Object";
		String s = "String";

		// 我有一点不解的是x + s这样的操作为何能成功，难道是因为默认对引用的+操作进行了重载，让他们的toString返回值进行连接？
		System.out.println(x + s);
		
		Object o1 = "O1";
		Object o2 = "O2";
		
		// 验证了一下是不行的，需要其中至少有一个String类型的
//		System.out.println(o1 + o2); 
		System.out.println(o1 + o2.toString());
	}
	
}
