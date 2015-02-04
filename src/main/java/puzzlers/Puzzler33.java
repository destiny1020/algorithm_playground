package puzzlers;

public class Puzzler33 {

	public static void main(String[] args) {
		
		int i = Integer.MIN_VALUE;
		
		//当i取到Integer的最小值时，能使下面的循环成为一个死循环！
		//看起来很神奇，但是仔细思考一下：Java中使用2的补码来做算术运算，为了取一个数值的负值
		//需要对每一个位取反然后加1，从而得到结果
		//2的补码运算一个很大的优势是0具有唯一的表示形式，对int的0取反后得到0xFFFFFFFF，然后加1
		//结果得到的还是0，但是2的32次方必然是个偶数，既然0只有唯一的表现形式，那么就意味着整数和负数是不相等的
		//实际上，正是Integer的最小值是多余的那个负数，它的十六进制表示是0x80000000
		//对它进行取反操作之后得到0x7FFFFFFF然后加1，结果还是0x80000000
		//也就是说，Integer的最小值的相反数还是它本身……
		//Long.MIN_VALUE也满足这一规律
		while(i != 0  && i != -i) {
			
		}
		
	}
	
}
