package misc;

import org.junit.Test;

public class MaxSubTwoDims {

	public static int[] getMaxSubSquare(int[][] matrix) {
		
		int smaller = matrix.length;
		int larger = matrix[0].length;
		
		boolean rowSmaller = true;
		if(smaller > larger) {
			int temp = smaller;
			smaller = larger;
			larger = temp;
			rowSmaller = false;
		}
		
		int[] largerSum = new int[larger];
		
		int max = Integer.MIN_VALUE;
		int rowStart = 0;
		int rowEnd = 0;
		int colStart = 0;
		int colEnd = 0;
		
		for(int i = 0; i < smaller; i++) {
			// clear all temporary sum
			for(int k = 0; k < larger; k++) {
				largerSum[k] = 0;
			}
			for(int j = i; j < smaller; j++) {
				for(int k = 0; k < larger; k++) {
					if(rowSmaller) {
						largerSum[k] += matrix[j][k];
					} else {
						largerSum[k] += matrix[k][j];
					}
					
				}
				// get the max subsequence on largerSum
				int[] res = MaxSubOneDim.getMaxSub(largerSum);
				if(res[0] > max) {
					max = res[0];
					if(rowSmaller) {
						rowStart = i;
						rowEnd = j;
						colStart = res[1];
						colEnd = res[2];
					} else {
						rowStart = res[1];
						rowEnd = res[2];
						colStart = i;
						colEnd = j;
					}
				}
			}
		}
		
		int[] results = new int[5];
		results[0] = max;
		results[1] = rowStart;
		results[2] = rowEnd;
		results[3] = colStart;
		results[4] = colEnd;
		
		return results;
	}
	
	@Test
	public void testMaxSubTwoDims() {
		
		int[][] matrix = {{1, -2, -3, 4}, 
				{-9, 8, -7, 6}, 
				{2, -3, 4, -5},
				{-8, 7, -6, 5},
				{4, -3, 2, -1}};
		
		int[] results = MaxSubTwoDims.getMaxSubSquare(matrix);
		
		System.out.println("Result: " + results[0]);
		System.out.println("Start Row: " + results[1]);
		System.out.println("End Row: " + results[2]);
		System.out.println("Start Col: " + results[3]);
		System.out.println("End Col: " + results[4]);
	}
	
}
