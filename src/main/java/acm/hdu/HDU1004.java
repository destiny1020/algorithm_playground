package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Balloon 
 * 
 * AC - one shot
 * @author jiangr2
 *
 */
public class HDU1004 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		Map<String, Integer> map;
		
		int total = Integer.parseInt(br.readLine());
		int frequency = -1;
		String result = null;
		while(0 != total) {
			map = new HashMap<String, Integer>(20);
			String key;
			Integer value = null;
			while(total > 0) {
				key = br.readLine();
				value = map.get(key);
				value = (null == value) ? map.put(key, 1) : map.put(key, value + 1);
				
				total--;
				value = null;
			}
			// output
			for(Entry<String, Integer> e : map.entrySet()) {
				if(e.getValue() > frequency) {
					result = e.getKey();
					frequency = e.getValue();
				}
			}
			
			out.println(result);
			
			frequency = -1;
			total = Integer.parseInt(br.readLine());
		}
		out.flush();
	}
}
