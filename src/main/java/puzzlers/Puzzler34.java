package puzzlers;

public class Puzzler34 {

	//改程序会立即返回0，因为循环实际上一次都没有执行循环体
	//这是因为START本身已经非常大了，而float由于要表示浮点数，在表示整数的范围上还不如int类型，因此对于START加上50后转变为float
	//是会丢失精度的，精度丢失的三种拓宽类型转换：int到float的提升，long到float的提升，以及long到double的提升
	//不要使用浮点数作为循环索引，行为无法预测
	//在将一个int或者long转换为一个float或者double的时候，可能会丢失精度
	//使用浮点数时，要使用double而不是float
	public static void main(String[] args) {
		
		final int START = 2000000000;
		int count = 0;
		for(float f = START; f < START + 50; f++) {
			count++;
		}
		
		System.out.println(count);
		
	}
	
}
