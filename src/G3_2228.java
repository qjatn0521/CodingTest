import java.io.*;
import java.util.Arrays;
import java.util.Collections;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 16:04 ~
public class G3_2228 {
    static int[] arr;
    static int[][] dp;
    static int[][] dp2;
    static boolean[][] gone2;
    static boolean[][] gone;
    static int max;
    static int arrSum = 0;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            int n = Integer.parseInt(tmp[0]);
            max = Integer.parseInt(tmp[1]);
            gone = new boolean[n][n];
            dp2 = new int[n][max+1];
            gone2 = new boolean[n][max+1];
            dp = new int[n][n];
            arr= new int[n];
            for(int i=0;i<n;i++) {
                int num = Integer.parseInt(bf.readLine());
                arr[i] = num;
                dp[i][i] = num;
                arrSum+=num;
            }
            int ans = fun(0,max);
            bw.write(""+ans);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static int fun(int start, int n) {
        if(n==0) {
            return 0;
        }
        int max = -3276800;
        if(gone2[start][n]==true) return dp2[start][n];

        for(int i = start;i<arr.length-2*(n-1);i++) {
            for(int j = i;j<arr.length-2*(n-1);j++) {
                int tmp = funSum(i,j)+fun(j+2,n-1);
                //System.out.println(i+"~"+j+":"+tmp);
                max = max>tmp?max:tmp;
            }
        }
        dp2[start][n] = max;
        gone2[start][n] = true;
        return max;
    }

    static int funSum(int start, int end) {
        if(gone[start][end]) {
            return dp[start][end];
        }
        int tmp =0;
        for(int i=start;i<=end;i++) {
            tmp+= arr[i];
            dp[start][i] = tmp;
            gone[start][i] = true;
        }
        return tmp;
    }

}