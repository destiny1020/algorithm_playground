package misc;

import org.junit.Test;

/**
 * 寻找一个无序的序列中最大的K个数
 * 初级版本，允许重复
 * 
 * @author Destiny
 *
 */
public class TopK {

	public static int[] process(int[] test, int k) {
		
		if(null == test || k <= 0) {
			return null;
		}
		
		// 注意这里的heap应该是最小堆，即若要求最大的K个数，那么应该维护一个最小堆
		int[] heap = new int[k];
		
		if(test.length <= k) {
			// omit sorting the test
			return test;
		} 
		
		// 将前k个数先插入到heap中，直接插入，不考虑建堆
		for(int i = 0; i < k; i++) {
			heap[i] = test[i];
		}
		
		// 建堆
		for(int i = (k - 2) >> 1; i >= 0; i--) {
			// 之所以要传入k， 是因为这是一个开区间，k代表上界
			sink(heap, i, k);
		}
		
		// 从test数组中的index为k的元素开始，进行筛选操作
		for(int i = k; i < test.length; i++) {
			// 只有当前元素大于堆顶元素的时候，才进行筛选操作
			if(test[i] > heap[0]) {
				heap[0] = test[i];
				sink(heap, 0, k);
			}
		}
		
		return heap;
	}

	// 下沉操作
	private static void sink(int[] heap, int i, int bound) {
		// 确保不要发生数组越界
		while(2 * i + 1 < bound) {
			int j = 2 * i + 1;
			// 如果存在更小的child，找到它
			if(j + 1 < bound && heap[j + 1] < heap[j]) {
				j++;
			}
			// 将当前元素和较小的child进行比较
			if(heap[i] > heap[j]) {
				exch(i, j, heap);
				i = j;
			} else {
				return;
			}
		}
	}
	
	private static void exch(int i, int j, int[] heap) {
		int t = heap[i];
		heap[i] = heap[j];
		heap[j] = t;
	}
	
	@Test
	public void testTopK() {
		// 测试用途，为了体现TopK算法的应用场景，test越大越好，或者数据从外部读入，总之，数据不可能被一次性放入内存
		int[] test = {5, 7, 8, 4, 2, 11, 4, 5, 7, 9, 10};
		
		for(int k : TopK.process(test, 3)) {
			System.out.println(k);
		}
	}
	
}

