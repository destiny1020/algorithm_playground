package puzzlers;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * 在char序列和byte序列之间转换时，可以且通常应该显式指定字符集
 * 查看默认字符集的方法，一种是通过System.getProperty("file.encoding")，还有一种使用nio包中CharSet类中的defaultCharset方法
 * 
 * @author Destiny
 *
 */
public class Puzzler18 {

	public static void main(String[] args) throws UnsupportedEncodingException {
		byte[] bytes = new byte[256];
		for(int i = 0; i < 256; i++) {
			bytes[i] = (byte)i;
			System.out.print(bytes[i] + " ");
		}
		System.out.println();
		
		
		String str = new String(bytes);
		
		// 0 - 127 然后一直都是65533
		for(int i = 0, n = str.length(); i < n; i++) {
			System.out.print((int)str.charAt(i) + " ");
		}
		System.out.println();
		System.out.println(Integer.toHexString(65533));
		System.out.println(Integer.toHexString(-128));
		
		System.out.println();
		System.out.println(str.length());
		
		String str8859 = new String(bytes, "ISO8859-1");
		System.out.println();
		System.out.println(str8859.length());
		
		
		// JRE默认字符集：这里打印的是UTF-8
		System.out.println(Charset.defaultCharset());
		System.out.println(System.getProperty("file.encoding"));
	}
	
}
