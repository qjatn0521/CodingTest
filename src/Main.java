import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 2 : 13
public class Main {
    static int[][] arr;
    static int[][] gone;
    static int num = 1;
    static int l;
    static int r;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            int n = Integer.parseInt(tmp[0]);
            l = Integer.parseInt(tmp[1]);
            r = Integer.parseInt(tmp[2]);
            arr = new int[n][n];
            gone = new int[n][n];
            for(int i=0;i<n;i++) {
                tmp = bf.readLine().split(" ");
                for(int j=0;j<n;j++) {
                    arr[i][j] = Integer.parseInt(tmp[j]);
                }
            }
            int ans = fun1(0);
            bw.write(""+ans);
            //bw.write(Arrays.deepToString(arr));
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    static int fun1(int n) {

        //System.out.println("arr : "+Arrays.deepToString(arr));
        for(int i=0;i<gone.length;i++) {
            for(int j=0;j<gone.length;j++) {
                if(gone[i][j]==0) {
                    fun2(i,j);
                    num++;
                }
                //System.out.println(Arrays.deepToString(gone));
            }
        }
        //System.out.println();
        if(num== gone.length* gone.length+1) {
            return n;
        }else {
            fun3();
            num=1;
            return fun1(n+1);
        }
    }

    private static void fun2(int y, int x) {
        gone[y][x] = num;
        if(x+1<gone.length && gone[y][x+1]==0
                && Math.abs(arr[y][x] - arr[y][x+1])>=l &&Math.abs(arr[y][x] - arr[y][x+1])<=r) {
            gone[y][x+1] = num;
            fun2(y,x+1);
        }
        if(x>0 && gone[y][x-1]==0
                && Math.abs(arr[y][x] - arr[y][x-1])>=l &&Math.abs(arr[y][x] - arr[y][x-1])<=r){
            gone[y][x-1] = num;
            fun2(y,x-1);
        }
        if(y+1< gone.length && gone[y+1][x]==0
                && Math.abs(arr[y][x] - arr[y+1][x])>=l &&Math.abs(arr[y][x] - arr[y+1][x])<=r){
            gone[y+1][x] = num;
            fun2(y+1,x);
        }
        if(y>0 && gone[y-1][x]==0
                && Math.abs(arr[y][x] - arr[y-1][x])>=l &&Math.abs(arr[y][x] - arr[y-1][x])<=r){
            gone[y-1][x] = num;
            fun2(y-1,x);
        }
    }
    static void fun3() {
        int[] sum = new int[num];
        int[] how = new int[num];
        for(int i=0;i<gone.length;i++) {
            for(int j=0;j<gone.length;j++) {
                if(gone[i][j]>0) {
                    sum[gone[i][j]-1]+= arr[i][j];
                    how[gone[i][j]-1]++;
                }
            }
        }
        for(int i=0;i<gone.length;i++) {
            for(int j=0;j<gone.length;j++) {
                for(int k=0;k<num;k++) {
                    if(gone[i][j]-1==k) {
                        arr[i][j] = sum[k]/how[k];
                    }
                }
                gone[i][j]=0;
            }
        }
    }
}