package puzzlers;

/**
 * 多重转换
 * @author Destiny
 *
 */
public class Puzzler6 {

	public static void main(String[] args) {
		
		// 应该输出-1
		// 这里是一个窄转换，int型截取后八位，为11111111，所以结果仍为-1
		System.out.println((byte)-1);
		
		// 最后输出了一个空行！甚至连一个空字符都没有！这是因为最后65535这个char的值不能代表任何有效字符！
		// 为什么这里的char的实际值为65535？因为从byte到char是由8位扩展为16位的过程，而byte为有符号类型，会执行符号扩展
		// 因此9位到16位会全部置1，因此就是65535了
		System.out.println((char)(byte)-1);
		
		// 最后输出了65535
		// 这基于一个规则：char类型到任何类型的转换，都执行无符号扩展，因此，从char到int的过程，是由16位转换到32位的过程
		// 因为执行的是无符号扩展，所以最后得到的仍然是65535
		System.out.println((int)(char)(byte)-1);
		
		System.out.println("------");
		
		// 一下都是一些可能存在的字符
		System.out.println((char)76);
		System.out.println((char)176);
		System.out.println((char)1176);
		System.out.println((char)11176);
		
		// 有时候，你可能需要对char执行有符号扩展，那么可以先将char转换为short类型
		// short类型和char类型一样是16位的宽度，但是short是有符号的，因此short在
		// 扩展为int类型的时候，会执行有符号扩展 -- sign extension
		System.out.println((int)(short)(char)(byte)-1);
		
		// 同样，如果需要将byte无符号扩展的转换为char类型，可以使用bit mask
		System.out.println((int)(char)(((byte)-1) & 0xFF));
	}
	
}
