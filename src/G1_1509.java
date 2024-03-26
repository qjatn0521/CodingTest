import java.io.*;
import java.util.Arrays;
import java.util.Collections;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 11:56 ~ 12 : 36
public class G1_1509 {
    static int[] arr;
    static int[] str;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split("");
            str = new int[tmp.length];
            for(int i=0;i< tmp.length;i++) {
                str[i] = (int) tmp[i].charAt(0);
            }
            arr = new int[tmp.length];
            int ans = fun(0);
            bw.write(""+ans);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static int fun(int n) {
        int min=2501;
        if(n>= arr.length) return 0;
        if(arr[n]!=0) return arr[n];

        for(int i=n;i<arr.length;i++) {
            if(check(n,i)) {
                int tmp = 1+fun(i+1);
                min = min>tmp?tmp:min;
            }
        }
        if(arr[n]==0) {
            arr[n] = min;
        } else {
            arr[n] = min>arr[n]?arr[n]:min;
        }
        return min;
    }
    static boolean check(int start, int end) {
        while(start<end) {
            if(str[start]!=str[end]) return false;
            start++; end--;
        }
        return true;
    }
}