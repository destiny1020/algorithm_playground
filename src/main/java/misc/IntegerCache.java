package misc;

import chap3.BST.IntegerWrapper;

/**
 * 这个类来源于对于二叉树判定平衡算法中使用Integer作为“指针”时不能正常工作的探究
 * @author Destiny
 *
 */
public class IntegerCache {

	public static void changeInteger(Integer source) {
		source = source + 1;
	}
	
	public static Integer changeIntegerRet(Integer source) {
		return (source + 1);
	}
	
	public static void changeIntegerWrapper(IntegerWrapper source) {
		source.value = source.value + 1;
	}
	
	public static void changeIntegerWrapperImmutable(IntegerWrapperImmutable source) {
//		source.value = source.value + 1;
		source = source.add(1);
	}
	
	public static void main(String[] args) {
		
		// 仅仅使用Integer，在一个方法中修改了zero的值，但是不返回最新的Ref值，打印错误的结果
		Integer zero = 0;
		changeInteger(zero);
		System.out.println(zero);
		
		System.out.println("--------------------------");
		
		// 还是使用了Integer，但是这里使用了带有返回值的方法，更新了最新的Ref值，得到正确的结果
		Integer zeroRet = 0;
		zeroRet = changeIntegerRet(zeroRet);
		System.out.println(zeroRet);
		
		System.out.println("--------------------------");
		
		// 使用Wrapper类，这里使用不带有返回值的方法，但是因为方法中不涉及对Ref值进行更新，得到正确的结果
		IntegerWrapper zeroWrapper = new IntegerWrapper(0);
		changeIntegerWrapper(zeroWrapper);
		System.out.println(zeroWrapper.value);
		
		System.out.println("--------------------------");
		
		// 使用Wrapper Immutable类，使用不带有返回值的方法，由于方法中没有返回最新的Ref，得到错误的结果
		IntegerWrapperImmutable zeroWrapperIm = new IntegerWrapperImmutable(0);
		changeIntegerWrapperImmutable(zeroWrapperIm);
		System.out.println(zeroWrapperIm.value);
	}
	
	// 如果把IntegerWrapper实现为Immutable Class会如何
	public static final class IntegerWrapperImmutable {
		public final int value;
		public IntegerWrapperImmutable(int value) {
			this.value = value;
		}
		
		public IntegerWrapperImmutable add(int operand) {
			return new IntegerWrapperImmutable(operand + value);
		}
	}
	
}
