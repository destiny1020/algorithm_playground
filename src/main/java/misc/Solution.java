package misc;

/* Enter your code here. Read input from STDIN. Print output to STDOUT */
import java.util.Scanner;
import java.util.StringTokenizer;

//Question 2 / 2
//Question:
//
//As you know, two operations of Stack are push and pop. Now give you two integer arrays, one is the original array before push and pop operations, the other one is the result array after a series of push and pop operations to the first array. Please give the push and pop operation sequence.
//
//For example:
//
//If the original array is a[] = {1,2,3}, and the result array is b[] = {1,3,2}.
//
//Then, the operation sequence is “push1|pop1|push2|push3|pop3|pop2”(operations are split by ‘|’ and no space).
//
//Rules:
//
//The push and pop operations deal with the original int array from left to right.
//The input is two integer array. They are the original array and the result array. These interger array is split by space.
//The output is the operation sequence.
//If the original array cannot make to the result array with stack push and pop, The output should be 'None'.
//The operation "push1" means push the first element of the original array to the stack.
//The operation "pop1" means pop the first element of the original array from the stack, and add this element to the tail of the result array.
//Please don't include any space in the output string.
//Sample1: 
//Input:
//1 2 3 4
//
//1 2 3 4
//Output:
//push1|pop1|push2|pop2|push3|pop3|push4|pop4
//
//Sample2: 
//
//Input:
//
//1 2 3 4
//
//4 3 2 1
//Output:
//push1|push2|push3|push4|pop4|pop3|pop2|pop1

public class Solution 
{
	private String calculateOperationSequence(int[] originalArray, int[] resultArray) 
	{
		if(null == originalArray || null == resultArray || 0 == originalArray.length || 0 == resultArray.length) {
			return "None";
		}
		
		int stackTopIndex = 0;
		int nextStartPoint = 0;
		int stackSize = 0;
		
		StringBuilder result = new StringBuilder();
		
		for(int i = 0; i < resultArray.length; i++) {
			int resultElement = resultArray[i];
			// if the stack top is not equals to the current popping element 
			while(resultElement != originalArray[stackTopIndex] && stackTopIndex < originalArray.length) {
				if(resultElement == originalArray[nextStartPoint]) {
					pushOps(result, originalArray[nextStartPoint]);
					stackSize++;
					nextStartPoint++;
					// fragile
					stackTopIndex++;
					break;
				} else {
					pushOps(result, originalArray[stackTopIndex++]);
				}
				stackSize++;
				nextStartPoint++;
			}
			if(stackTopIndex == originalArray.length) {
				return "None";
			} 
			
			if(resultElement == originalArray[stackTopIndex] && stackSize <= stackTopIndex) {
				pushOps(result, originalArray[stackTopIndex]);
				stackSize++;
				nextStartPoint++;
			}
			
			if(i != resultArray.length - 1) {
				popOps(result, resultElement, false);
			} else {
				popOps(result, resultElement, true);
			}
			stackSize--;
			
			// adjust the stack top index
			if(stackSize == 0) {
				stackTopIndex = nextStartPoint;  
			} else {
				stackTopIndex--;
			}
		}
		
		return result.toString();
	}
	
	/**
	 * helper method for push operation
	 * @param sb
	 */
	private void pushOps(StringBuilder sb, int element) {
		sb.append("push");
		sb.append(element);
		sb.append("|");
	}
	
	/**
	 * helper method for pop operation
	 * @param sb
	 */
	private void popOps(StringBuilder sb, int element, boolean isLast) {
		sb.append("pop");
		sb.append(element);
		if(!isLast) {
			sb.append("|");
		}
	}
	
	public static void main(String[] args) 
	{
		Solution solution = new Solution();
		Scanner scanner = new Scanner(System.in);
		
		while (scanner.hasNextLine()) 
		{
			String strLine1 = scanner.nextLine();
			StringTokenizer stringTokenizer1 = new StringTokenizer(strLine1);
			
			//Initialize the original array
			int arrayLength = stringTokenizer1.countTokens();
			int[] originalArray = new int[arrayLength];
			for(int i = 0; i < arrayLength; i++)
			{
				originalArray[i] = Integer.parseInt(stringTokenizer1.nextToken());
			}
			
			//Initialize the result array
			String strLine2 = scanner.nextLine();
			StringTokenizer stringTokenizer2 = new StringTokenizer(strLine2);
			arrayLength = stringTokenizer2.countTokens();
			int[] resultArray = new int[arrayLength];
			for(int j = 0; j < arrayLength; j++)
			{
				resultArray[j] = Integer.parseInt(stringTokenizer2.nextToken());
			}
			
			String operationSequence = solution.calculateOperationSequence(originalArray, resultArray);
			System.out.println(operationSequence);
		}
	}
}