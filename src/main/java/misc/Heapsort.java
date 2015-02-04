package misc;

import org.junit.Test;

/**
 * 堆排序的实现，分成几个步骤：
 * 1. heap construction
 * 2. sortdown
 * 
 * 需要注意的是传入的arr是0-based
 * 
 * @author Destiny
 *
 */
public class Heapsort {
	
	public static void sort(int[] arr) {
		
		// heap construction by sink
		for(int i = arr.length >> 1; i >= 0; i--) {
			sink2(arr, i, arr.length - 1);
		}
		for(int i = arr.length - 1; i > 0; i--) {
			exch(arr, i, 0);
			sink2(arr, 0, i - 1);
		}
		
		for(int i : arr) {
			System.out.print(i + " ");
		}
	}
	
	/**
	 * 
	 * @param arr
	 * @param target
	 * @param bound 需要注意bound为sink的最大索引位置，当bound等于arr.length - 1的时候，相当于对所有的数组范围进行sink，加上这个参数的目的在于使heapsort能够就地排序
	 */
	private static void sink(int[] arr, int target, int bound) {
		
		// 注意这里移位操作符的优先级是小于+操作符的
		int exchange = (target << 1) + 1;
		while(exchange <= bound) {
			// 寻找较大的那个child
			if(exchange + 1 <= bound && arr[exchange + 1] > arr[exchange]) {
				exchange++;
			}
			
			// 如果当前parent的值比最大的child的值要小，才进行交换
			if(arr[target] < arr[exchange]) {
				exch(arr, target, exchange);
				
				// 更新target以及exchange的值
				target = exchange;
				exchange = (target << 1) + 1;
			} else {
				// 否则不需要继续进行sink操作
				return;
			}
		}
	}
	
	/**
	 * 一个实现上更为简洁的sink操作
	 * @param arr
	 * @param target
	 * @param bound
	 */
	private static void sink2(int[] arr, int target, int bound) {
	
		// 首先还是检查当前的target有没有至少一个child
		while((target << 1) + 1 <= bound) {
			int exchange = (target << 1) + 1;
			// 如果还存在第二个child，则选取值较大的那个child
			if((exchange + 1) <= bound && arr[exchange] < arr[exchange + 1])
				exchange++;
			// 如果当前target的值比孩子中较大的那个还要大，没有必要继续进行sink操作
			if(arr[target] >= arr[exchange]) 
				break;
			// 交换target以及较大的孩子，同时更新target
			exch(arr, target, exchange);
			target = exchange;
		}
		
	}
	
	/**
	 * 实际上堆排序可以不适用swim过程
	 * @param arr
	 * @param target
	 */
	private static void swim(int[] arr, int target) {
		while(target > 0 && arr[(target - 1) >> 1] < arr[target]) {
			exch(arr, target, (target - 1) >> 1);
			target = (target - 1) >> 1;
		}
	}
	
	private static void exch(int[] arr, int from, int to) {
		int temp = arr[from];
		arr[from] = arr[to];
		arr[to] = temp;
	}
	
	@Test
	public void testHeapSort() {
		int[] test = {4, 5, 2, 3, 1, 8, 9, 6, 7};
		Heapsort.sort(test);
	}
}
