import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 16:04 ~
public class G4_9252 {
    static int[] str1;
    static int[] str2;
    static int[][] dp;
    static char[] s = new char[2000];
    static char[] tmps = new char[2000];
    static int tmpMax = 0;

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            char[] str1 = bf.readLine().toCharArray();
            char[] str2 = bf.readLine().toCharArray();

            dp = new int[str1.length+1][str2.length+1];

            for(int i=1;i<=str1.length;i++) {
                for(int j=1;j<=str2.length;j++) {
                    if(str1[i-1]==str2[j-1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }

            System.out.println(dp[str1.length][str2.length]);
            String s="";
            for(int i=str1.length,j =str2.length;i>0&&j>0;) {
                if(dp[i][j]==dp[i-1][j]) i--;
                else if(dp[i][j]==dp[i][j-1]) j--;
                else  {
                    s= (char)str1[i-1]+s;
                    i--;j--;
                }
            }
            System.out.println(s);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}