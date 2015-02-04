package misc;

public class Permutation {

	private static int solutions = 0;
	
	public static void main(String[] args) {

		String str = "12345678";
		perm(str);
		System.out.println(solutions);
	}

	public static void perm(String str) {
		perm("", str);
	}

	private static void perm(String prefix, String remainder) {
		if (0 == remainder.length()) {
			if (check(prefix)) {
				solutions++;
				System.out.println(prefix);
			}
			return;
		} else {
			for (int i = 0; i < remainder.length(); i++) {
				perm(prefix + remainder.substring(i, i + 1),
						remainder.substring(0, i) + remainder.substring(i + 1));
			}
		}
	}

	private static boolean check(String prefix) {
		for (int i = 0; i < prefix.length(); i++) {
			for (int j = i + 1; j < prefix.length(); j++) {
				if ((j - i == prefix.charAt(j) - prefix.charAt(i))
						|| (j - i == prefix.charAt(i) - prefix.charAt(j))) {
					return false;
				}
			}
		}
		
		return true;
	}
}
