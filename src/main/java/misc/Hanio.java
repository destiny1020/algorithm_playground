package misc;

public class Hanio {

	public static void main(String[] args) {
		
		hanoi(3, 'A', 'B', 'C');
		
	}
	
	private static void hanoi(int current, char from, char middle, char to) {
		if(current == 1) {
			move(from, to);
		} else {
			hanoi(current - 1, from, to, middle);
			move(from, to);
			hanoi(current - 1, middle, from, to);
		}
	}
	
	private static void move(char from, char to) {
		System.out.println(from + " -> " + to);
	}
	
}
