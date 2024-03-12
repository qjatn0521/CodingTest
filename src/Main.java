import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 14:00 ~ 14:47
public class Main {
    static int[][] arr;
    static int[] sum = new int[6];
    static int ans = 10000000;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            arr = new int[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[0])];
            for(int i=0;i< arr.length;i++) {
                tmp = bf.readLine().split(" ");
                for (int j = 0; j < arr.length; j++) {
                    arr[i][j] = Integer.parseInt(tmp[j]);
                    sum[5]+= arr[i][j];
                }
            }
            for(int y=0;y<arr.length;y++) {
                for(int x=0;x<arr.length;x++) {
                    for(int d1=1;y-d1>=0;d1++) {
                        for(int d2=1;d1+d2+x<arr.length&&y+d2<arr.length;d2++) {
                            fun1(x,y,d1,d2);
                            //bw.write(x+", "+y+", "+d1+", "+d2+"\n");
                        }
                    }
                }
            }
            //fun1(0,1,1,4);
            bw.write(""+ans);
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    static void fun1(int x, int y, int d1, int d2) {
        sum[0]=0;sum[1]=0;sum[2]=0;sum[3]=0;
        for(int i=y-1;i>=0;i--) {
            for(int k=0;k<x+(y-i)&&k<x+d1+1;k++) {
                sum[0]+= arr[i][k];
            }
        }
        int min = sum[0];
        int max = sum[0];
        for(int i=y-d1+d2;i>=0;i--) {
            int k = x+2*d1-(y-i)>=x+d1+1?x+2*d1-(y-i):x+d1;
            k++;
            for(;k<arr.length;k++) {
                sum[1]+= arr[i][k];
                //System.out.print("x,y: "+k+", "+i);
            }
            //System.out.println();
        }
        for(int i=y;i<arr.length;i++) {
            for(int j=0;j<x+i-y&&j<x+d2;j++) {
                sum[2]+= arr[i][j];
            }
        }
        for(int i=y-d1+d2+1;i<arr.length;i++) {
            int j=x+d1+d2+(y-d1+d2+1-i)<x+d2?x+d2:x+d1+d2+(y-d1+d2+1-i);
            for(;j<arr.length;j++) {
                sum[3]+= arr[i][j];
            }
        }
        sum[4] = sum[5]-sum[0]-sum[1]-sum[2]-sum[3];
        min = min>sum[1]?sum[1]:min;
        max = max>sum[1]?max:sum[1];
        min = min>sum[2]?sum[2]:min;
        max = max>sum[2]?max:sum[2];
        min = min>sum[3]?sum[3]:min;
        max = max>sum[3]?max:sum[3];
        min = min>sum[4]?sum[4]:min;
        max = max>sum[4]?max:sum[4];

        if(max-min<ans) {
            ans = max-min;
            //System.out.println("ans :"+x+", "+y+", "+d1+", "+d2);
            //System.out.println(sum[0]+", "+sum[1]+", "+sum[2]+", "+sum[3]);
        }
    }


}
/*

 */