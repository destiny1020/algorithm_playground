package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Dragon Ball
 * 
 * change class name before submitting
 * 
 * @author Destiny
 * 
 */

// TODO: WA -> TLE * n -> WA * n ...
public class HDU3635 {

   private static int[] id;
   private static int[] size;
   private static int[] trans;

   public static void main(String[] args) throws IOException {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      int totalCases = Integer.parseInt(br.readLine());
      int caseIndex = 1;
      int N, Q;
      String[] parts;
      while (totalCases > 0) {
         parts = br.readLine().split(" ");
         N = Integer.parseInt(parts[0]);
         Q = Integer.parseInt(parts[1]);

         // output
         out.print("Case ");
         out.print(caseIndex);
         out.println(": ");

         // init UF structure
         id = new int[N + 1];
         size = new int[N + 1];
         trans = new int[N + 1];

         for (int i = 1; i <= N; i++) {
            id[i] = i;
            size[i] = 1;
            trans[i] = 0;
         }

         char ops;
         int child, parent, target;
         while (Q > 0) {
            parts = br.readLine().split(" ");
            ops = parts[0].charAt(0);
            switch (ops) {
            case 'T':
            case 't':
               child = Integer.parseInt(parts[1]);
               parent = Integer.parseInt(parts[2]);
               union(child, parent);
               break;
            case 'Q':
            case 'q':
               target = Integer.parseInt(parts[1]);
               int root = find(target);
               // output

               // debug only
               //               out.print(target + ":");

               out.print(root);
               out.print(' ');
               out.print(size[root]);
               out.print(' ');
               out.println(trans[target]);
               break;
            }
            Q--;
         }
         totalCases--;
      }
      out.flush();
      out.close();
      br.close();
   }

   private static void union(int p, int q) {
      int pRoot = find(p);
      int qRoot = find(q);

      if (pRoot == qRoot) {
         return;
      }

      // union operation
      id[pRoot] = qRoot;
      //      trans[pRoot]++; // equals to trans[pRoot] = 1;
      trans[pRoot] = 1;
      size[qRoot] += size[pRoot];
   }

   private static int find(int q) {
      while (id[q] != id[id[q]]) {
         System.out.println("Trans: " + q + " : " + trans[id[q]] + " --- From "
               + q + " to " + id[id[q]]);
         trans[q] += trans[id[q]];
         id[q] = id[id[q]];
      }
      return id[q];
   }
}