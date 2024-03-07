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
    static int[][] gone;
    static int sharkY = 0;
    static int sharkX = 0;
    static int size = 2;
    static int feed = 0;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            int n = Integer.parseInt(tmp[0]);
            arr = new int[n][n];
            for(int i=0;i<n;i++) {
                tmp = bf.readLine().split(" ");
                for(int j=0;j<n;j++) {
                    int num = Integer.parseInt(tmp[j]);
                    if(num == 9) {
                        sharkY = i;
                        sharkX = j;
                    } else {
                        arr[i][j] = Integer.parseInt(tmp[j]);
                    }
                }
            }

            int ans = fun1(0);

            bw.write(""+ans);
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    static int fun1(int len) {
        int tmpY=-1;
        int tmpX=-1;
        int tmpLength = 100000;
        fun3();
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr.length;j++) {
                if(arr[i][j]!=0 && arr[i][j]<size && tmpLength>gone[i][j]-1 &&gone[i][j] !=0) {
                    tmpY = i;
                    tmpX = j;
                    tmpLength = gone[i][j]-1;
                }
            }
        }
        if(tmpY==-1) {
            return len;
        } else {
            fun2(tmpY,tmpX);
            return fun1(len+tmpLength);
        }
    }

    static void fun2(int y, int x) {
        sharkX = x;
        sharkY = y;
        arr[y][x] = 0;
        feed++;
        if(feed==size) {
            feed=0;
            size++;
        }
    }
    static void fun3() {
        gone = new int [arr.length][arr.length];
        fun4(sharkY,sharkX,1);
    }

    static void fun4(int sY, int sX, int num) {
        if(gone[sY][sX]==0||gone[sY][sX]>num) {
            gone[sY][sX] = num;
            if(sY-1>=0 && arr[sY-1][sX]<=size) fun4(sY-1,sX,num+1);
            if(sX-1>=0&& arr[sY][sX-1]<=size) fun4(sY,sX-1,num+1);
            if(sY+1<arr.length&& arr[sY+1][sX]<=size) fun4(sY+1,sX,num+1);
            if(sX+1<arr.length&& arr[sY][sX+1]<=size) fun4(sY,sX+1,num+1);
        }
    }
}
/*

 */