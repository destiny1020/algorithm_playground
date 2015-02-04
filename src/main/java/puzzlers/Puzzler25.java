package puzzlers;

public class Puzzler25 {

	public static void main(String[] args) {
		
		int j = 0;
		for(int i = 0; i < 100; i++) {
			j = j++;
		}
		
		// 最后打印的是0
		System.out.println(j);
	}
	
	// 以下是javap的输出
//	public static void main(java.lang.String[]);
//	  Code:
//	   0:   iconst_0   声明一个value为0的变量
//	   1:   istore_1   将0存储到j中
//	   2:   iconst_0   声明一个value为0的变量
//	   3:   istore_2   将0存储到i中
//	   4:   goto    15
//	   7:   iload_1   读取j到堆栈中
//	   8:   iinc    1, 1  关键是这里！这个地方对j原始存储单元进行操作，或者可以看成，压入堆栈，增加1，然后移出堆栈，3个机器指令可能是佐证
//	   11:  istore_1  将堆栈中的值压入j中，这里压入的就是7中读取的j，那时候读取的就是没有变化之前的值，也就是0！
//	   12:  iinc    2, 1
//	   15:  iload_2  取出i
//	   16:  bipush  100  将100压入堆栈
//	   18:  if_icmplt       7   将堆栈中的两个操作数进行比较，如果小于，返回到7
//	   21:  getstatic       #16; //Field java/lang/System.out:Ljava/io/PrintStream;
//	   24:  iload_1
//	   25:  invokevirtual   #22; //Method java/io/PrintStream.println:(I)V
//	   28:  return
//
//	}
	
}
