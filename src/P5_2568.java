import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 11:56 ~ 12 : 36
public class P5_2568 {
    static int[] arr1;
    static int[] dp;
    static int[] arr2;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp;
            int n = Integer.parseInt(bf.readLine());
            arr1 = new int[500001];
            dp = new int[n+1];
            arr2 = new int[500001];
            Queue<Integer> dpQ[] = new Queue[n];

            for(int i=0;i<n;i++) {
                tmp = bf.readLine().split(" ");
                arr1[Integer.parseInt(tmp[0])] = Integer.parseInt(tmp[1]);
                dpQ[i] = new LinkedList<Integer>();

                arr2[Integer.parseInt(tmp[1])] = Integer.parseInt(tmp[0]);
            }
            int max = 0;
            for(int i=0;i<500001;i++) {
                if(arr1[i]>0) {
                    int low = 0;
                    int high = max;
                    while(low<high) {
                        int mid = (low+high)/2;
                        if(dp[mid]>=arr1[i]) high = mid;
                        else low = mid +1;
                    }
                    dp[low] = arr1[i];
                    dpQ[low].add(dp[low]);

                    if(low==max) {
                        max++;
                    }
                }
            }
            dp[max] = 2000000001;
            for(int i=max-1;i>=0;i--) {
                dp[i]=0;
                while(!dpQ[i].isEmpty()) {
                    int q = dpQ[i].poll();
                    if(q>dp[i]&&dp[i+1]>q) {
                        dp[i] = q;
                    }
                }
            }

            for(int i=0;i<max;i++) {
                dp[i] = arr2[dp[i]];
            }
            Arrays.sort(dp);
            System.out.println(n-max);
           // System.out.println(Arrays.toString(dp));
            int j =n-max;
            for(int i=0;i<500001;i++) {
                if(arr1[i]>0) {
                    //System.out.println("dp : "+dp[j]+", arr : "+i);
                    if(i==dp[j]) j++;
                    else bw.write(i+"\n");
                }
            }




            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}