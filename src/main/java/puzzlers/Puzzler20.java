package puzzlers;

import java.util.regex.Pattern;

/**
 * 警惕String類中的一些方法參數接受的是正則表達式
 * @author Destiny
 *
 */
public class Puzzler20 {
	
	public static void main(String[] args) {
		
		// 輸出是這樣的：//////////////////.class
		// 這是因為replaceAll方法的第一個參數是正則表達式，而.在正則表達式中會匹配每一個字符
		System.out.println(Puzzler20.class.getName().replaceAll(".", "/") + ".class");
		
		// 正確的方式應該是這樣：
		// 輸出為：puzzlers/Puzzler20.class
		System.out.println(Puzzler20.class.getName().replaceAll("\\.", "/") + ".class");
		
		// 上面的正則表達式看上去挺奇怪，其中包含了兩個\，之所以有兩個是因為：
		// 一個是因為\在字符串字面值中代表的是轉義序列的開始
		// 一個是因為\在正則表達式中代表轉義字符
		// 因此，需要匹配\本身，需要4個\
		
		// 或者使用正則表達式類Pattern.quote方法進行處理，這樣就能避免麻煩的轉義工作了
		System.out.println(Puzzler20.class.getName().replaceAll(Pattern.quote("."), "/") + ".class");
	}
	
}
