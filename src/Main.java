import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 백준 14503 1시도 통과
public class Main {

    static int[][] arr;
    static int max;
    static int ans = 5000000;
    static ArrayList<Integer> homeLen = new ArrayList<>();
    static ArrayList<Integer> ckX = new ArrayList<>();
    static ArrayList<Integer> ckY = new ArrayList<>();
    static ArrayList<Integer> homeX = new ArrayList<>();
    static ArrayList<Integer> homeY = new ArrayList<>();
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            arr = new int[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[0])];
            max = Integer.parseInt(tmp[1]);
            for(int i=0;i<arr.length;i++) {
                tmp = bf.readLine().split(" ");
                for(int j=0;j<arr.length;j++) {
                    arr[i][j] = Integer.parseInt(tmp[j]);
                    if(arr[i][j] == 2) {
                        ckY.add(i);
                        ckX.add(j);
                        arr[i][j] =0;
                    } else if(arr[i][j]==1) {
                        homeY.add(i);
                        homeX.add(j);
                        homeLen.add(1000000000);
                    }
                }
            }
            for(int i=0;i<ckX.size()-max+1;i++) {
                fun0(0,i);
            }
            bw.write(""+ans);
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    static void fun0(int num,int at) {
        int tmpY = ckY.get(at);
        int tmpX = ckX.get(at);
        ArrayList<Integer> homeTmpLen = new ArrayList<>();
        for(int i=0;i<homeX.size();i++) {
            homeTmpLen.add(homeLen.get(i));
        }
        fun1(tmpX,tmpY,at);
        if(num==max-1)  {
            fun2();
        } else {
            for(int i=at+1;i<ckX.size();i++) {
                fun0(num+1,i);
            }
        }

        for(int i=0;i<homeY.size();i++) {
            homeLen.set(i,homeTmpLen.get(i));
        }
    }
    static void fun1(int x, int y,int at) {
        for(int i=0;i<homeX.size();i++) {
            int xLen = homeX.get(i) - x > 0 ? homeX.get(i) - x : x - homeX.get(i);
            int yLen = homeY.get(i) - y > 0 ? homeY.get(i) - y : y - homeY.get(i);
            int tmpLen = xLen+yLen;
            if(homeLen.get(i) > tmpLen)  {
                homeLen.set(i,tmpLen);
            }
        }
    }
    static void fun2() {
        int sum =0;
        for(int i=0;i<homeLen.size();i++) {
            sum+= homeLen.get(i);
        }
        if(sum<ans) ans = sum;
    }
}