package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * WA WA WA ...
 * 
 * @author jiangr2
 * 
 *         1 2 3 4 3 3 4 4 1 1 2 1 2 3 0 0
 * 
 *         -1 -1 NO
 * 
 */
public class HDU1272 {
   private static final int MAX = 100000;
   private static int[] id;
   private static int[] sz;
   private static boolean valid = true;
   private static boolean hasRead = false;
   private static Set<Integer> nodes;
   private static int edges = 0;

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      String line = br.readLine();
      String[] parts = line.split("\\s+");
      // init
      if (parts.length > 2) {
         nodes = new HashSet<Integer>(MAX);
         id = new int[MAX + 1];
         sz = new int[MAX + 1];
         for (int i = 1; i <= MAX; i++) {
            id[i] = i;
            sz[i] = 1;
         }
      }
      while (parts.length > 2 || -1 != Integer.parseInt(parts[0])) {
         for (int i = 0; i < parts.length; i += 2) {
            int p = Integer.parseInt(parts[i]);
            int q = Integer.parseInt(parts[i + 1]);
            if (p != 0 && q != 0) {
               hasRead = true;
               nodes.add(p);
               nodes.add(q);
               if (valid && p != q) {
                  union(p, q);
               }
            } else { // read 0 0
               if (valid && !hasRead || nodes.size() == edges + 1) {
                  out.println("YES");
               } else {
                  out.println("NO");
               }
               valid = true;
               hasRead = false;
               edges = 0;
               nodes = new HashSet<Integer>(MAX);
               id = new int[MAX + 1];
               sz = new int[MAX + 1];
               for (int j = 1; j <= MAX; j++) {
                  id[j] = j;
                  sz[j] = 1;
               }
               br.readLine();
               break;
            }
         }
         parts = br.readLine().split("\\s+");
      }
      out.flush();
   }

   private static void union(int p, int q) {
      int pRoot = find(p);
      int qRoot = find(q);

      if (pRoot == qRoot) {
         valid = false;
         return;
      }

      edges++;
      id[qRoot] = pRoot;
   }

   private static int find(int p) {
      while (p != id[p]) {
         id[p] = id[id[p]];
         p = id[p];
      }
      return p;
   }
}
