package puzzlers;

/**
 * 死循环的另一个例子
 * 
 * @author Destiny
 *
 */
public class Puzzler27 {

	public static void main(String[] args) {
		
		int i = 0;
		
		// 还是会出现死循环，这里通过变长的移位操作意图将-1变为0，但是这是不可能的
		// 因为移位操作符只使用其右操作数的低5位作为移位长度
		// 也就是说移位的范围是0-31，对于long类型的是0-63
		// 因此，没有任何移位长度可以让一个int数值丢弃所有的32位
		while(-1 << i != 0) {
			i++;
		}
		System.out.println(i);
		
	}
	
}
