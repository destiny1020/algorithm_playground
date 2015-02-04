package puzzlers;

import java.util.Random;

public class Puzzler23 {

	private static Random rnd = new Random();

	public static void main(String[] args) {

		StringBuffer sb = null;

		switch (rnd.nextInt(2)) {
		case 1:
			sb = new StringBuffer('P');
		case 2:
			sb = new StringBuffer('G');
		default:
			sb = new StringBuffer('M');
		}
		
		sb.append('a');
		sb.append('i');
		sb.append('n');
		
		System.out.println(sb);
		
		// 程序最后永远只会打印ain，而不是Main或者别的什么
		// 这是因为程序中犯了三个错误：
		// 1: Random生成随机数的时候犯了栅栏柱错误，传入的2并不会保证生成的随机数范围是1-2，而是0-1
		// 2: case语句中丢失了break
		// 3: StringBuffer并没有接受char作为参数的constructor，这里虽然接受的是char，但是会拓宽成为int类型，实际的语义变成了指定capacity

	}

}
