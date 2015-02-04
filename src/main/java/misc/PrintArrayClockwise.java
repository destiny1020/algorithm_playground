package misc;

import org.junit.Test;

/**
 * 顺时针打印二维数组，比如下面的二维数组： 
 * 1 2 3 4 
 * 14 15 16 5 
 * 13 20 17 6 
 * 12 19 18 7 
 * 11 10 9 8
 * 
 * 接回打印输出1, 2, 3 ..... 20
 * 
 * @author Destiny
 * 
 */
public class PrintArrayClockwise {

	@Test
	public void printMatrix() {

		int[][] matrix = { { 1, 2, 3, 4 }, { 14, 15, 16, 5 },
				{ 13, 20, 17, 6 }, { 12, 19, 18, 7 }, { 11, 10, 9, 8 } };

		PrintArrayClockwise.print(matrix, 0, matrix.length - 1, 0,
				matrix[0].length - 1);
	}

	private static void print(int[][] matrix, int row_start, int row_end,
			int col_start, int col_end) {

		// 递归终止条件
		if(row_start > row_end || col_start > col_end) {
			return;
		}
		
		// 打印外围最上层的元素
		for(int col = col_start; col <= col_end; col++) {
			System.out.print(matrix[row_start][col] + " ");
		}
		
		// 打印外围最右侧的元素
		for(int row = row_start + 1; row <= row_end; row++) {
			System.out.print(matrix[row][col_end] + " ");
		}
		
		// 打印外围最下层的元素
		for(int col = col_end - 1; col >= row_start; col--) {
			System.out.print(matrix[row_end][col] + " ");
		}
		
		// 打印外围最左侧的元素
		for(int row = row_end - 1; row > row_start; row--) {
			System.out.print(matrix[row][col_start] + " ");
		}
		
		// 递归打印：
		print(matrix, row_start + 1, row_end - 1, col_start + 1, col_end - 1);
	}

}
