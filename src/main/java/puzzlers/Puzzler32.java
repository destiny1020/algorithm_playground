package puzzlers;

public class Puzzler32 {

	public static void main(String[] args) {
		
		
		Integer i = new Integer(0);
		Integer j = new Integer(0);
		
		//我一开始认为这个地方应该会打印true，但是结果是false
		//实际上，我把Integer.valueOf方法的机制和new的机制弄混淆了
		//在valueOf方法中，-128到127都被cache了
		//因此两次对相同的数值(-128到127之间)执行同一valueOf方法返回的是同一个对象
		//但是new没有这个机制
		System.out.println(i == j);
		
		//以下是一个无限循环
		//因为<=和>=的比较首先会将wrapper类型转换为primitive类型，而i和j的!=操作还是直接对identity进行比较
		//为什么!=这种操作符不会首先转换成primitive呢？这是为了兼容性的考虑，改变的设计不能对既有代码造成影响，这是无法接受的
		while(i <= j && i >= j && i != j) {
			
		}
		
	}
	
}
