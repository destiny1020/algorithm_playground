package acm.hdu;

import java.io.*;

//�����������io
public class HDU3635_2 {
   int n;
   int[] parent = null;
   int[] balls = null;
   int[] trans = null;

   void inint(int n) {
      parent = new int[n + 1];
      balls = new int[n + 1];
      trans = new int[n + 1];
      for (int i = 1; i <= n; i++) {
         parent[i] = i;
         balls[i] = 1;
         trans[i] = 0;
      }
   }

   int find(int x) {
      if (parent[x] == x)
         return parent[x];
      int temp = parent[x];
      parent[x] = find(parent[x]);
      trans[x] += trans[temp];
      return parent[x];
   }

   void union(int x, int y) {
      int fx = find(x);
      int fy = find(y);
      if (fx == fy)
         return;

      parent[fx] = fy;
      balls[fy] = balls[fx] + balls[fy];
      trans[fx] = 1;

   }

   public static void main(String args[]) throws IOException {
      BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
      PrintWriter write = new PrintWriter(System.out);
      int cases = Integer.parseInt(read.readLine());
      int i = 0;
      while (cases > 0) {
         i++;
         String[] ss = read.readLine().split(" ");
         int N = new Integer(ss[0]);
         int Q = new Integer(ss[1]);
         HDU3635_2 drag = new HDU3635_2();
         drag.inint(N);
         write.println("Case" + " " + i + ":");
         while (Q > 0) {
            Q--;
            String[] s = read.readLine().split(" ");
            String a = s[0];
            if (a.equals("T")) {
               int ball1 = new Integer(s[1]);
               int ball2 = new Integer(s[2]);
               drag.union(ball1, ball2);
            } else {
               int ball = new Integer(s[1]);
               int curentcity = drag.find(ball);
               write.println(curentcity + " " + drag.balls[curentcity] + " "
                     + drag.trans[ball]);
            }
         }
         cases--;
      }
      read.close();
      write.close();
   }
}