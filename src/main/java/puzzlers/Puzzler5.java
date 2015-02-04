package puzzlers;

/**
 * 通常需要避免对混合类型进行计算
 * 
 * @author Destiny
 * 
 */
public class Puzzler5 {

	public static void main(String[] args) {

		// 本是想让long类型和int类型的数据运算，从而得到结果0x1CAFEBABE，但是结果中前面的1没有了
		// 这是因为对int进行扩展是基于符号来扩展，因此0xCAFEBABE最后被扩展为0xFFFFFFFFCAFEBABE
		// 因此真是的计算实际上是这样的：
		// 0x0000000100000000
		// 0xFFFFFFFFCAFEBABE
		// ------------------ +
		// 0x00000000CAFEBABE
		System.out.println(Long.toHexString(0x100000000L + 0xCAFEBABE));

		// 显示地指示第二个操作数是long类型，注意使用L而不是l
		System.out.println(Long.toHexString(0x100000000L + 0xCAFEBABEL));

	}

}
