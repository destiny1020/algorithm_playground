package basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fraction {

	private int a;
	private int b;

//	private int findCMD(int a, int b) {
//		int r;
//		int n = a;
//		int m = b;
//		r = n % m;
//		while (r != 0) {
//			n = m;
//			m = r;
//			r = n % m;
//		}
//		return m;
//	}
//	
//	/**
//	 * a should be larger than b 
//	 */
//	private int findCMD(int a, int b) {
//		
//	}

	public void setNum(int n, int m) {
		if (n <= m)
			throw new IllegalArgumentException();
		a = n;
		b = m;
	}

	public int printFractions() {
		ArrayList<String> irreducible = new ArrayList<String>();
		ArrayList<String> reducible = new ArrayList<String>();

		StringBuilder sb = new StringBuilder();

		for (int p = b; p <= a; p++)
			for (int q = 1; q < b; q++) {
//				if (this.findCMD(p, q) == 1) {
					sb.append(q);
					sb.append("/");
					sb.append(p);
					irreducible.add(sb.toString());
					sb.delete(0, sb.length());
//				} else {
					sb.append(q);
					sb.append("/");
					sb.append(p);
					reducible.add(sb.toString());
					sb.delete(0, sb.length());
//				}
			}

		System.out.println("Irreducible: " + irreducible.size());
		System.out.println("reducible: " + reducible.size());

		return irreducible.size() + reducible.size();
	}
	
	public int printFractionsEA() {
		ArrayList<String> irreducible = new ArrayList<String>();
		ArrayList<String> reducible = new ArrayList<String>();

		StringBuilder sb = new StringBuilder();

		for (int p = b; p <= a; p++)
			for (int q = 1; q < b; q++) {
				if (gcd(p, q) == 1) {
					sb.append(q);
					sb.append("/");
					sb.append(p);
					irreducible.add(sb.toString());
					sb.delete(0, sb.length());
				} else {
					sb.append(q);
					sb.append("/");
					sb.append(p);
					reducible.add(sb.toString());
					sb.delete(0, sb.length());
				}
			}

		System.out.println("Irreducible: " + irreducible.size());
		System.out.println("reducible: " + reducible.size());

		return irreducible.size() + reducible.size();
	}

	/**
	 * Improved method
	 */
	public int printFractions2() {
		boolean[] primeLib = Fraction.getPrimeLib(a);

		List<String> irreducible = new ArrayList<String>();
		List<String> reducible = new ArrayList<String>();

		StringBuilder sb = new StringBuilder();

		for (int p = b; p <= a; p++)
			if (primeLib[p]) {
				for (int q = 1; q < b; q++) {
					sb.append(q);
					sb.append("/");
					sb.append(p);
					irreducible.add(sb.toString());
					sb.delete(0, sb.length());
				}
			} else {
				for (int q = 1; q < b; q++) {
					if (gcd(p, q) == 1) {
						sb.append(q);
						sb.append("/");
						sb.append(p);
						irreducible.add(sb.toString());
						sb.delete(0, sb.length());
					} else {
						sb.append(q);
						sb.append("/");
						sb.append(p);
						reducible.add(sb.toString());
						sb.delete(0, sb.length());
					}
				}
			}

		System.out.println("Irreducible: " + irreducible.size());
		System.out.println("reducible: " + reducible.size());

		return irreducible.size() + reducible.size();
	}

	/**
	 * 获取上界为upper的素数表
	 */
	public static boolean[] getPrimeLib(int upper) {

		boolean[] isPrime = new boolean[upper + 1];

		Arrays.fill(isPrime, true);

		int maxPrimes = upper >>> 1;
		for (int i = 2; i <= maxPrimes; i++) {
			if (isPrime[i]) {
				for (int j = i << 1; j <= upper; j += i) {
					isPrime[j] = false;
				}
			}
		}

		return isPrime;
	}

	public static int gcd(int p, int q) {
		if (q == 0)
			return p;
		if (p == 0)
			return q;

		// p and q even
		if ((p & 1) == 0 && (q & 1) == 0)
			return gcd(p >> 1, q >> 1) << 1;

		// p is even, q is odd
		else if ((p & 1) == 0)
			return gcd(p >> 1, q);

		// p is odd, q is even
		else if ((q & 1) == 0)
			return gcd(p, q >> 1);

		// p and q odd, p >= q
		else if (p >= q)
			return gcd((p - q) >> 1, q);

		// p and q odd, p < q
		else
			return gcd(p, (q - p) >> 1);
	}

	public static void main(String[] args) {
		int p = 3;
		int q = 10;
		System.out.println("gcd(" + p + ", " + q + ") = " + gcd(p, q));
	}

	//
	// /**
	// * @param args
	// */
	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// // Fraction fif = new Fraction();
	// // fif.setNum(500, 100);
	// // fif.printFractions();
	// final Runtime rt = Runtime.getRuntime();
	// rt.addShutdownHook(new Thread() {
	// @Override
	// public void run() {
	// System.out.println("Total Mem: " + (rt.totalMemory() >> 20)
	// + "MB");
	// System.out.println("Max Mem: " + (rt.maxMemory() >> 20) + "MB");
	// System.out.println("Free Mem: " + (rt.freeMemory() >> 20)
	// + "MB");
	// }
	// });
	//
	// int upper = 50000;
	// int lower = 1000;
	// int total = (lower - 1) * (upper - lower + 1);
	//
	// Fraction fif = new Fraction();
	// fif.setNum(upper, lower);
	// int totalActual = fif.printFractions();
	//
	// Assert.assertTrue(total == totalActual);
	//
	// }
}
