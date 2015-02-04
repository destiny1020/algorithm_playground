package misc;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class PermutationIgDups {

	private static int solutions = 0;
	private static Map<String, Boolean> maps;

	public static void main(String[] args) {

		String str = "12345678";
		perm(str);
		System.out.println(solutions);
	}

	@SuppressWarnings("unchecked")
	public static void perm(String str) {
		maps = new HashMap<String, Boolean>();
		perm("", str);
	}

	private static void perm(String prefix, String remainder) {
		if (0 == remainder.length()) {
			solutions++;
			System.out.println(prefix);
			return;
		} else {
			for (int i = 0; i < remainder.length(); i++) {
				if(null == maps.get(prefix + remainder.substring(i, i + 1))) {
					maps.put(prefix + remainder.substring(i, i + 1), true);
					perm(prefix + remainder.substring(i, i + 1),
							remainder.substring(0, i) + remainder.substring(i + 1));
				}
			}
		}
	}
	
	@Test
	public void testPermIgDups() {
		PermutationIgDups.perm("aaab");
		System.out.println("Solutions: " + PermutationIgDups.solutions);
	}
	
	@Test
	public void testEmptyStringLength() {
		System.out.println("".length());
	}
}
