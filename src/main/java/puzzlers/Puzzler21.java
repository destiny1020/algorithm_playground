package puzzlers;

import java.io.File;

/**
 * 
 * @author Destiny
 *
 */
public class Puzzler21 {

	public static void main(String[] args) {
		
		// 拋出異常：
//		Exception in thread "main" java.lang.StringIndexOutOfBoundsException: String index out of range: 1
//		at java.lang.String.charAt(String.java:686)
//		at java.util.regex.Matcher.appendReplacement(Matcher.java:703)
//		at java.util.regex.Matcher.replaceAll(Matcher.java:813)
//		at java.lang.String.replaceAll(String.java:2189)
//		at puzzlers.Puzzler21.main(Puzzler21.java:14)
		System.out.println(Puzzler21.class.getName().replaceAll("\\.", File.separator) + ".class");
		
		// 實際上，上述代碼在Unix平臺上並不會拋出異常，看來，異常和File.separator的返回值有關係
		
	}
	
}
