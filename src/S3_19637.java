import java.io.*;
import java.util.PriorityQueue;

public class S3_19637 {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] s = bf.readLine().split(" ");
            int n1 = Integer.parseInt(s[0]);
            int n2 = Integer.parseInt(s[1]);
            int arrI[] = new int[n1+1];
            String[] arrS = new String[n1+1];
            for(int i=1;i<=n1;i++) {
                s = bf.readLine().split(" ");

                arrI[i] = Integer.parseInt(s[1]);
                if(arrI[i]-1==arrI[i])
                    arrS[i] = arrS[i-1];
                else
                    arrS[i] = s[0];
            }
            for(int i=0;i<n2;i++) {
                int num = Integer.parseInt(bf.readLine());
                int lo = 1;
                int hi = n1;
                while(true) {
                    int mid = (lo+hi)/2;

                    if(lo==hi) {
                        System.out.println(arrS[mid]);
                        break;
                    } else if(num>arrI[mid]) {
                        lo = mid+1;
                    } else {
                        hi = mid;
                    }
                }
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}