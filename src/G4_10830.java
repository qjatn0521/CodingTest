import java.io.*;
import java.util.Arrays;

public class G4_10830 {
    static long[][][] dp;
    public static void main(String[] args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] inputs = bf.readLine().split(" ");

            int a = Integer.parseInt(inputs[0]);
            long b = Long.parseLong(inputs[1]);

            long[][] arr = new long[a][a];
            dp = new long[70][a][a];

            for(int i=0;i<a;i++) {
                inputs = bf.readLine().split(" ");
                for(int j=0;j<a;j++) {
                    arr[i][j] = Integer.parseInt(inputs[j]);
                }
            }
            module(arr,b,0);
            long[][] tmp = dp[0];
            for(int i=0;i<a;i++) {
                for(int j=0;j<a-1;j++) {
                    bw.write(tmp[i][j]+" ");
                }
                bw.write(tmp[i][a-1]+"\n");
            }
            bw.write("");
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void module(long[][] arr,long b,int depth) {
        if(b==1) {
            dp[depth] = arr;
        }
        else {
            module(arr,b/2,depth+1);

            long tmp[][] = matrixMul(dp[depth+1],dp[depth+1]);

            if(b%2==1) {
                tmp = matrixMul(tmp,arr);
            }
            dp[depth] = tmp;
        }
    }

    static long[][] matrixMul(long[][] arr, long[][] arr2) {
        long[][] matrix = new long[arr.length][arr.length];
        for(int y =0;y<arr.length;y++) {
            for(int x =0;x<arr.length;x++) {
                long sum = 0;
                for(int i =0;i<arr.length;i++) {
                    sum += arr2[i][x]*arr[y][i];
                }
                matrix[y][x] = sum%1000;
            }
        }
        return matrix;
    }
}