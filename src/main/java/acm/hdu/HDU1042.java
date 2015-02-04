package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class HDU1042 {

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      String line = br.readLine();
      while (!line.isEmpty() && null != line) {
         out.println(Factor.getFactor(Integer.parseInt(line)));
         line = br.readLine();
      }

      out.flush();
   }
}

class Factor {
   public static Map<Integer, BigDecimal> factors;

   static {
      factors = new HashMap<Integer, BigDecimal>(50);

      factors.put(0, new BigDecimal(1));
      factors.put(1, new BigDecimal(1));
   }

   public static BigDecimal getFactor(int N) {
      BigDecimal result = factors.get(N);
      if (null == result) {
         result = getFactor(N - 1).multiply(BigDecimal.valueOf(N));
      } else {
         return result;
      }

      // update the map
      if (N > 30) {
         factors.put(N, result);
      }
      return result;
   }
}
