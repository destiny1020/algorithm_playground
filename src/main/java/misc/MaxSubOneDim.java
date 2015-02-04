package misc;

import org.junit.Test;

public class MaxSubOneDim {

	public static int[] getMaxSub(int[] arr) {
		
		int previous = arr[0];
		int current;
		
		int lastFrom = 0;
		int from = 0;
		int to = 0;
		
		int max= previous;
		
		for(int i = 1; i < arr.length; i++) {
			int possible = previous + arr[i];
			if(possible > arr[i]) {
				current = possible;
			} else {
				lastFrom = i;
				current = arr[i];
			}
			if(current > max) {
				max = current;
				to = i;
				from = lastFrom;
			}
			previous = current;
		}
		
		int[] res = new int[3];
		
		res[0] = max;
		res[1] = from;
		res[2] = to;
		
		return res;
	}
	
	@Test
	public void testMaxSub() {
		
//		int[] test = {-1, 3, 4, -4, 5, -9, 5};
		int[] test = {1, -9, 2, -8, 4};
		
		int[] results = MaxSubOneDim.getMaxSub(test);
		
		System.out.println("Result: " + results[0]);
		System.out.println("From: " + results[1]);
		System.out.println("To: " + results[2]);
		
	}

}
