package misc;

import java.util.Arrays;

import org.junit.Test;

/**
 * 主要是为了测试Java面试宝典中第六章中的一些例子
 * 对应的Note在ON中：Java相关-Java面试-第六章
 * 
 * @author Destiny
 *
 */
public class Misc6 {

	@Test
	public void testPrint() {
		int i = 0;
		System.out.println(i + '0');
		
		// 上行中打印的是48，是字符0 的ASCII code
	}
	
	@Test
	public void incrementEfficiency() {
		int x = 0;
		System.out.println("-------");
		x = x + 1;
		System.out.println("-------");
		x += 1;
		System.out.println("-------");
		x++;
		System.out.println("-------");
	}
	
	/**
	 * 测试短路操作符
	 */
	@Test
	public void shortOperand() {
		int a = 5;
		int b = 3;
		if(0 == a & b++ == 0) {
			System.out.println("ERROR IF PRINT");
		}
		System.out.println(a);
		System.out.println(b);
	}
	
	@Test
	public void testIncrement() {
		int i = 0;
		i = i++;
		System.out.println(i);
		
		int j = 0;
		j = ++j;
		System.out.println(j);
		
		int k = 0;
		k = k++ + ++k;
		System.out.println(k);
		
		int h = 0;
		h = ++h + h++ + h++ + h++;
		System.out.println(h);
		
	}
	
	@Test
	public void testTypeConversion() {
		short s = 1;
//		s = s + 1;
		s += 1;
	}
	
	/**
	 * final关键字还有内联的作用？鬼扯
	 */
	@Test
	public void testFinal() {
		
		System.out.println("-------");
		finalInternal();
		System.out.println("-------");
		nonFinalInternal();
		System.out.println("-------");
		
	}
	
	private final void finalInternal() {
		System.out.println("Final Internal");
	}
	
	private void nonFinalInternal() {
		System.out.println("Non-Final Internal");
	}
	
	@Test
	public void testPassStr() {
		String ori = "Origin";
		changeStr(ori);
		System.out.println(ori);
	}
	
	private void changeStr(String str) {
		str = "Change";
	}
	
	@Test
	public void testPassChars() {
		char[] chars = {'A', 'B', 'C'};
		changeChars(chars);
		System.out.println(Arrays.toString(chars));
	}
	
	private void changeChars(char[] chars) {
		char[] replacedChars = {'D', 'E', 'F'};
		chars = replacedChars;
	}
}
