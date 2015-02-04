package puzzlers;

public class Puzzler30 {

	public static void main(String[] args) {
		
		// 别忘了+对于String的连接操作是存在重载的
		// 当然，这里使用Double或者Float类型的NaN特殊值也行
		String i = "";
		while(i != i + 0) {
			System.out.println("HAHA");
		}
		
	}
	
}
