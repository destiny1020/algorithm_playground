package puzzlers;

public class Puzzler36 {

	//在一个try-finally语句中，finally语句块总是在控制权离开try语句块时执行
	//无论try语句块是正常结束的，还是意外结束的，情况都是如此
	
	//当try语句块和finally语句块都意外结束时，在try语句块中引发意外结束的原因将被丢弃
	public static void main(String[] args) {
		
		//这里返回的是false
		System.out.println(decision());
	}
	
	static boolean decision() {
		try {
			return true;
			
			//这里有一个warning：finally block does not complete normally
			//即finally块没有正常结束
			//在finally中调用了return break continue或者throw等来退出finally，是非正常结束
			//除非是抛出的非受检异常，即RuntimeException
		} finally {
			return false;
		}
	}
	
}
