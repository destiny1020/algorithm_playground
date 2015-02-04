package acm.hdu.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HDU1025Generator {
	
	public static int N = 50;
	
	public static void main(String[] args) throws IOException {
		Integer[] a = new Integer[N];
		Integer[] b = new Integer[N];
		
		for(int i = 1; i <= N; i++) {
			a[i - 1] = i;
		}
		
		List<Integer> list = Arrays.asList(a);
		
		Collections.shuffle(list);
		System.arraycopy(a, 0, b, 0, a.length);
		
		List<Integer> list2 = Arrays.asList(b);
		
		
		Collections.shuffle(list2);
		
		a = list.toArray(new Integer[]{});
		b = list2.toArray(new Integer[]{});
		
		File f = new File("data/acm-hdu/1025.txt");
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		bw.write(N + "\n");
		for(int i = 0; i < N; i++) {
			bw.write(a[i] + " " + b[i] + "\n");
		}
		
		bw.flush();
		System.out.println("Done");
	}
}
