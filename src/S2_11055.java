import java.io.*;
import java.util.Arrays;
import java.util.Stack;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 14:32 ~ 15:12
public class S2_11055 {
    static int ans = 0;
    static int dp[][];
    static int nums[];
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(bf.readLine());
            String tmp[] = bf.readLine().split(" ");
            dp = new int[1001][n];
            nums = new int[n];
            for(int i=0;i<n;i++) {
                int num = Integer.parseInt(tmp[i]);
                nums[i] = num;
            }
            fun(0,0,0);
            bw.write(""+ans);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void fun(int n, int sum,int max) {
        //System.out.println(n+","+sum+","+max);
        ans = sum>ans?sum:ans;
        if(n==nums.length) {
            return;
        }
        if(dp[max][n]<=sum) {
            dp[max][n] = sum;
            if(max<nums[n])
                fun(n+1,sum+nums[n],nums[n]);
            fun(n+1,sum,max);
        }
    }

}