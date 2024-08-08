import java.io.*;
import java.util.*;

public class G5_12865 {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String tmp[] = bf.readLine().split(" ");
            int n =Integer.parseInt(tmp[0]) +1;
            int maxWeight = Integer.parseInt((tmp[1])) +1;

            int[][] dp = new int[n][maxWeight];

            int[] values = new int[n];
            int[] weights = new int[n];

            for(int i=1;i<n;i++) {
                tmp  = bf.readLine().split(" ");
                values[i] = Integer.parseInt(tmp[1]);
                weights[i] = Integer.parseInt(tmp[0]);
            }

            for(int i=1;i<n;i++) {
                for(int j=1;j<maxWeight;j++) {
                    if(weights[i]>j) {
                        dp[i][j] = dp[i-1][j];
                    }
                    else {
                        dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weights[i]] + values[i]);
                    }
                }

            }
            for(int j=1;j<n;j++) {
                for(int i=0;i<maxWeight;i++) {
                    System.out.print(dp[j][i]+" ");
                }
                System.out.println();
            }
            System.out.println(dp[n-1][maxWeight-1]);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}