import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 10:18 ~ 11 : 24,
public class Main {
    static int[][] arr;
    static int[][] plusDust;
    static int cleanerY = 0;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            arr = new int[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[1])];
            plusDust = new int[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[1])];
            int sec = Integer.parseInt(tmp[2]);
            for(int i=0;i<arr.length;i++) {
                tmp = bf.readLine().split(" ");
                for(int j=0;j<arr[0].length;j++) {
                    int num = Integer.parseInt(tmp[j]);
                    if(num==-1) {
                        cleanerY = i;
                    }
                    arr[i][j] = num;
                }
            }
            fun1(sec);
            int sum = 2;
            for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr[0].length;j++) {
                    sum+=arr[i][j];
                }
            }

            bw.write(""+sum);
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    static void fun1(int sec) {
        if(sec<=0) return;
        fun2();
        fun4();
        fun1(sec-1);
    }

    static void fun2() {
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[0].length;j++) {
                if(arr[i][j]>0) fun3(i,j);
            }
        }
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[0].length;j++) {
                arr[i][j] += plusDust[i][j];
                plusDust[i][j]=0;
            }
        }
    }
    static void fun3(int y, int x) {
        int value = arr[y][x]/5;
        if(x-1>=0 && arr[y][x-1]>=0) {
            plusDust[y][x-1] +=value;
            arr[y][x] -=value;
        }
        if(y-1>=0 && arr[y-1][x]>=0) {
            plusDust[y-1][x] +=value;
            arr[y][x] -=value;
        }
        if(x+1<arr[0].length) {
            plusDust[y][x+1] +=value;
            arr[y][x] -=value;
        }
        if(y+1<arr.length && arr[y+1][x]>=0) {
            plusDust[y+1][x] +=value;
            arr[y][x] -=value;
        }
    }
    static void fun4() {
        funDown(0,cleanerY-3,0);
        funUp(0, cleanerY+2,arr.length-1);
        funLeft(0);
        funLeft(arr.length-1);
        funUp(arr[0].length-1, 1,cleanerY-1);
        funDown(arr[0].length-1,arr.length-2,cleanerY);
        funRight(cleanerY-1);
        funRight(cleanerY);

    }
    static void funRight(int y) {
        for(int i=arr[0].length-2;i>0;i--) {
            arr[y][i+1] = arr[y][i];
        }
        arr[y][1]=0;
    }
    static void funDown(int x, int startY, int endY) {
        for(int i=startY;i>=endY;i--) {
            arr[i+1][x] = arr[i][x];
        }
    }
    static void funLeft(int y) {
        for(int i=1;i<arr[0].length;i++) {
            arr[y][i-1] = arr[y][i];
        }
    }
    static void funUp(int x, int startY, int endY) {
        for(int i=startY;i<=endY;i++) {
            arr[i-1][x] = arr[i][x];
        }
    }
}
/*

 */