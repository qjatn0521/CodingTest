import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 13:52
public class Main {
    static int[][] arr;
    static boolean pass = true;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            arr = new int[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[1])];
            int n = Integer.parseInt(tmp[2]);

            for (int i = 0; i < arr.length; i++) {
                tmp = bf.readLine().split(" ");
                for (int j = 0; j < arr[0].length; j++) {
                    arr[i][j] = Integer.parseInt(tmp[j]);
                }
            }

            for (int i = 0; i < n; i++) {
                tmp = bf.readLine().split(" ");
                fun(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]));
            }
            int ans = 0;
            for (int i = 0; i < arr.length; i++) {
                //bw.write(Arrays.toString(arr[i]));
                for (int j = 0; j < arr[0].length; j++) {
                    ans += arr[i][j];
                }
                //bw.write("\n");
            }
            bw.write("" + ans);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void fun(int x, int d, int k) {
        //System.out.println("=====================");
        pass = true;
        for(int i=x;i<=arr.length;i+=x) {
            fun1(i-1,d,k);
            //System.out.println(i-1);
        }
        for(int i=0;i<arr.length;i++) {
            fun2(i);
        }

        if(pass) fun4();/*
        System.out.println();
        for(int i=0;i< arr.length;i++) {
            System.out.println(Arrays.toString(arr[i]));
        }*/
    }

    static void fun2(int y) {
        int[] tmp = new int[arr[0].length];
        for(int j=0;j<arr[0].length;j++) {
            tmp[j] = arr[y][j];
            if(arr[y][j]>0) fun3(y, j, arr[y][j]);
            if(tmp[j]!=arr[y][j]) pass = false;
        }
    }

    static void fun3(int y, int x, int value) {
        if(y-1>=0&&value==arr[y-1][x]) {
            arr[y][x] = 0;
            arr[y-1][x] =0;
            fun3(y-1,x,value);
        }
        if(y+1<arr.length&&value==arr[y+1][x]) {
            arr[y][x] = 0;
            arr[y+1][x] =0;
            fun3(y+1,x,value);
        }
        if((x==0&&value==arr[y][arr[0].length-1])||(x>0&&value==arr[y][x-1])) {
            arr[y][x] = 0;
            int tmpX = x;
            if(x==0) tmpX = arr[0].length;
            arr[y][tmpX-1] = 0;
            fun3(y,tmpX-1,value);
        }
        if((x==arr[0].length-1&&value==arr[y][0])||(x<arr[0].length-1&&value==arr[y][x+1])) {
            arr[y][x] = 0;
            if(x==arr[0].length-1) x= -1;
            arr[y][x+1] =0;
            fun3(y,x+1,value);
        }
    }

    static void fun4() {
        float sum = 0;
        float n = 0;
        for(int i=0;i< arr.length;i++) {
            for(int j=0;j< arr[0].length;j++) {
                if(arr[i][j]>0) {
                    sum+=arr[i][j];
                    n++;
                }
            }
        }
        //System.out.println("sum:"+sum+", n"+n+", ave:" +sum/n);
        for(int i=0;i< arr.length;i++) {
            for(int j=0;j< arr[0].length;j++) {
                if(arr[i][j]>0) {
                    if(((float) arr[i][j])> sum/n) arr[i][j]--;
                    else if(((float) arr[i][j])< sum/n)arr[i][j]++;
                }
            }
        }
    }

    static void fun1(int y, int d, int k) {
        int[] tmpArr = new int[arr[0].length];
        for(int j=0;j<tmpArr.length;j++) {
            tmpArr[j] = arr[y][j];
        }
        if(d==1) {
            for(int j=0;j<arr[0].length;j++) {
                int tmpJ = j+k >= arr[0].length ? j+k-arr[0].length : j+k;
                arr[y][j] = tmpArr[tmpJ];
            }
        } else {
            for(int j= arr[0].length-1;j>=0;j--) {
                int tmpJ = j-k < 0 ? j-k+ arr[0].length : j-k;
                arr[y][j] = tmpArr[tmpJ];
            }
        }


    }



}