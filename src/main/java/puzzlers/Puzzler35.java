package puzzlers;

public class Puzzler35 {

	public static void main(String[] args) {
		
		int minutes = 0;
		for(int ms = 0; ms < 60 * 60 * 1000; ms++) {
			
			//对于60*1000这种类型的常量，可以使用常量来声明
			//这样也能够总某种程度上减少出现问题的可能性
			if(ms % 60 * 1000 == 0) {
				minutes++;
			}
		}
		
		//打印的是60000
		//这是因为取模运算符和乘法操作符具有相同的优先级，这里实际上是先对60作取模运算，然后乘以1000,
		//因此最后的结果和期望的不一样
		//对运算符优先级不能确定的情况，使用括号来进行限制
		System.out.println(minutes);
		
	}
	
}
