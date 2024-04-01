import java.io.*;
import java.util.*;

public class G4_14002 {
    static int arr[];
    static int num;
    public static void main(String[] args) {
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;
            num = Integer.parseInt(bf.readLine());
            st = new StringTokenizer(bf.readLine());
            int max =0;
            arr = new int[num];
            int dp[] = new int[num+1];
            Queue<Integer> dpQ[] = new Queue[num];
            for(int i=0;i<num;i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                dpQ[i] = new LinkedList<Integer>();
            }
            for(int i=0;i<num;i++) {
                int lo =0;
                int hi =max;
                while(lo<hi) {
                    int mid = (lo+hi)/2;
                    if(dp[mid]>=arr[i]) hi = mid;
                    else lo = mid +1;
                }
                if(lo==max) max++;
                dp[lo] = arr[i];
                dpQ[lo].add(dp[lo]);
                //System.out.println(Arrays.toString(arr2));
            }
            System.out.println(max);
            dp[max] = Integer.MAX_VALUE;
            for(int i=max-1;i>=0;i--) {
                dp[i]=0;
                while(!dpQ[i].isEmpty()) {
                    int q = dpQ[i].poll();
                    if(q>dp[i]&&dp[i+1]>q) {
                        dp[i] = q;
                    }
                }
            }
            for(int i=0;i<max-1;i++) {
                System.out.print(dp[i]+" ");
            }
            System.out.print(dp[max-1]);
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
