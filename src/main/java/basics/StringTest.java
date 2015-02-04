package basics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class StringTest {
   
   /**
    * 为了统计字符串中非英文字的数量
    * @throws UnsupportedEncodingException
    */
   @Test
   public void testCountHanzi() throws UnsupportedEncodingException {
      String str = "abc汉字abc汉字あの";

      int count = 0;
      for (int i = 0; i < str.length(); i++) {
         if (String.valueOf(str.charAt(i)).getBytes("GBK").length > 1) {
            count++;
         }
      }

      System.out.println(count);
   }

   /**
    * File <- FileWriter <- BufferedWriter <- buffer <- BufferedReader <-
    * StringReader <- String
    * 
    * 将字符串中的内容存储到一个文件中
    * 
    * @throws IOException
    */
   @Test
   public void testStringToFile() throws IOException {
      String src = "我是源字符串!\r\n我是第二行!";
      String dest = "test/basics/string/Target.txt";

      File f = new File(dest);

      if (!f.exists()) {
         f.getParentFile().mkdirs();
      }

      BufferedWriter bw = new BufferedWriter(new FileWriter(f));
      BufferedReader br = new BufferedReader(new StringReader(src));

      char[] buffer = new char[1024];
      int len;
      while ((len = br.read(buffer)) != -1) {
         bw.write(buffer, 0, len);
      }
      bw.flush();
      bw.close();
      br.close();
   }

   /**
    * Underlying String <- StringWriter <- BufferedWriter <- buffer <-
    * BufferedReader <- FileReader
    * 
    * 将文件中的内容读入到程序String中
    * 
    * @throws IOException
    */
   @Test
   public void testFileToString() throws IOException {
      String srcPath = "test/basics/string/Target.txt";
      StringWriter sw = new StringWriter();

      File f = new File(srcPath);

      if (!f.exists()) {
         System.out.println("源文件不存在！");
         return;
      }

      BufferedWriter bw = new BufferedWriter(sw);
      BufferedReader br = new BufferedReader(new FileReader(f));
      char[] buffer = new char[1024];
      int len;

      while (-1 != (len = br.read(buffer))) {
         bw.write(buffer, 0, len);
      }
      bw.flush();
      bw.close();
      br.close();

      System.out.println(sw.toString());
   }
   
   /**
    * substring中的index是真正char的index，lastIndexOf也是
    */
   @Test
   public void truncateHanziTest() {
      String hanzi = "abc我是汉字abc";
      
      // read 0 - 3 chars, default UTF-8, correct
      System.out.println(hanzi.substring(0, 5));
      System.out.println(hanzi.lastIndexOf("字"));
   }
}
