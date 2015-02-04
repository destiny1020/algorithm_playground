package misc;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 寻找一个无序的序列中最大的K个数
 * 插入堆中的元素都要被添加到一个哈希表中，然后在向堆中插入元素的时候，检查这个哈希表来达到去重的效果
 * 
 * @author Destiny
 *
 */
public class TopKWithCount {

	public static Map<Integer, Integer> map;
	
	public static int[] process(int[] test, int k) {
		
		if(null == test || k <= 0) {
			return null;
		}
		
		// 注意这里的heap应该是最小堆，即若要求最大的K个数，那么应该维护一个最小堆
		int[] heap = new int[k];
		
		// 因为要考虑重复元素的情况，不可在长度不够时直接返回了
//		if(test.length <= k) {
//			// omit sorting the test
//			return test;
//		} 
		
		int heapSize = 0;
		int currentIndex = -1;
		map = new HashMap<Integer, Integer>();
		
		// 将前k个数先插入到heap中，直接插入，不考虑建堆
		for(int i = 0; i < test.length; i++) {
			
			// 检查test[i]是否已经出现在堆中了
			if(null == map.get(test[i])) {
				// 若没有出现过则添加
				heap[heapSize++] = test[i];
				map.put(test[i], 1);
				if(k == heapSize) {
					// 如果heapSize已经超过了heap的空间，跳出循环
					// 记录下test已经遍历到了第几个元素
					currentIndex = i + 1;
					break;
				}
			} else if(map.get(test[i]) > 0) {
				// 如果当前元素已经存在于堆中了
				map.put(test[i], map.get(test[i]) + 1);
			}
		}
		
		// 如果上面的循环不是通过break跳出的，表示test数组已经循环完毕
		if(-1 == currentIndex) {
			
			// 根据当前heapSize来组织需要返回的结果集
			int[] result = new int[heapSize];
			for(int i = 0; i < result.length; i++) {
				result[i] = heap[i];
			}
			return result;
		}
		
		// 建堆
		for(int i = (k - 2) >> 1; i >= 0; i--) {
			// 之所以要传入k， 是因为这是一个开区间，k代表上界
			sink(heap, i, k);
		}
		
		// 从test数组中的index为currentIndex的元素开始，进行筛选操作
		for(int i = currentIndex; i < test.length; i++) {
			// 只有当前元素大于堆顶元素的时候，才进行筛选操作
			// 注意这里条件运算的结合顺序
			if(test[i] > heap[0] && null == map.get(test[i])){
				heap[0] = test[i];
				map.put(heap[0], 1);
				sink(heap, 0, k);
			} else if(test[i] > heap[0] && null != map.get(test[i])) {
				// 如果当前元素已经存在于堆中了
				map.put(test[i], map.get(test[i]) + 1);
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
		int[] test = {5, 4, 8, 9, 11, 11, 4, 5, 11, 9, 10};
		
		for(int k : TopKWithCount.process(test, 3)) {
			System.out.println(k + " - Count: " + map.get(k));
		}
	}
	
}

