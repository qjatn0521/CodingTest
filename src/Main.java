import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 12:20
public class Main {
    static int[][] arr;
    static int[][] feed;
    static int[][] treesY = new int[10000][100];
    static  int[][] treesX = new int[10000][100];
    static int[] num = new int[10000];
    static ArrayList<Integer> deadTree = new ArrayList<>();
    static ArrayList<Integer> deadTreeY = new ArrayList<>();
    static ArrayList<Integer> deadTreeX = new ArrayList<>();
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            int n = Integer.parseInt(tmp[0]);
            int n2 = Integer.parseInt(tmp[1]);
            int year = Integer.parseInt(tmp[2]);
            arr = new int[n][n];
            feed = new int[n][n];
            for(int i=0;i<n;i++) {
                tmp = bf.readLine().split(" ");
                for(int j=0;j<n;j++) {
                    arr[i][j] = 5;
                    feed[i][j] = Integer.parseInt(tmp[j]);
                }
            }
            for(int i=1;i<num.length;i++) {
            }
            for(int i=0;i<n2;i++) {
                tmp = bf.readLine().split(" ");
                treesY[Integer.parseInt(tmp[2])][num[Integer.parseInt(tmp[2])]] = (Integer.parseInt(tmp[0])-1);
                treesX[Integer.parseInt(tmp[2])][num[Integer.parseInt(tmp[2])]] = (Integer.parseInt(tmp[1])-1);
                num[Integer.parseInt(tmp[2])]++;
            }
            for(int i=0;i<year;i++) {
                fun1();
                for(int j=0;j<n;j++) {
                    //bw.write(Arrays.toString(arr[j])+"\n");
                }
                //bw.write("\n");
            }
            int ans = 0;

            for(int i =1;i<num.length;i++) {
                ans+= num[i];
            }
            bw.write(""+ans);
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    static void fun1() {
        fun2();
        fun3();
        fun4();
        fun5();
    }

    static void fun2() {
        Queue<Integer> tmpListY = new LinkedList<>();
        Queue<Integer> tmpListX = new LinkedList<>();
        for(int i =1;i<num.length;i++) {
            int size = 0;
            for(int j=0;j<num[i];j++) {
                int tmpY = treesY[i][j];
                int tmpX = treesX[i][j];
                if(!(tmpY<0 || tmpX<0 || tmpY>= arr.length || tmpX>= arr.length)) {
                    if(arr[tmpY][tmpX]>=i) {
                        arr[tmpY][tmpX]-=i;
                        size++;
                        tmpListY.add(tmpY);
                        tmpListX.add(tmpX);
                    } else {
                        deadTree.add(i);
                        deadTreeY.add(tmpY);
                        deadTreeX.add(tmpX);
                    }
                }
            }
            num[i] = tmpListY.size()-size;
            treesY[i] = new int[num[i]];
            treesX[i] = new int[num[i]];
            for(int j=0;j<num[i];j++) {
                int tmpY = tmpListY.peek();
                int tmpX = tmpListX.peek();
                treesY[i][j] = tmpY;
                treesX[i][j] = tmpX;
                tmpListY.poll();
                tmpListX.poll();
            }
        }
    }
    static void fun3() {
        for(int i=0;i<deadTree.size();i++) {
            int age = deadTree.get(i);
            arr[deadTreeY.get(i)][deadTreeX.get(i)] += (age/2);
        }
        deadTree.clear();
        deadTreeX.clear();
        deadTreeY.clear();
    }
    static void fun4() {
        Queue<Integer> tmpListY = new LinkedList<>();
        Queue<Integer> tmpListX = new LinkedList<>();
        for(int i=5;i<num.length;i+=5) {
            for(int j=0;j<num[i];j++) {
                int tmpY = treesY[i][j];
                int tmpX = treesX[i][j];
                if(tmpY-1>=0 && tmpX-1>=0) {
                    tmpListY.add(tmpY-1);
                    tmpListX.add(tmpX-1);
                }
                if(tmpY-1>=0) {
                    tmpListY.add(tmpY-1);
                    tmpListX.add(tmpX);
                }
                if(tmpY-1>=0 && tmpX+1<arr.length) {
                    tmpListY.add(tmpY-1);
                    tmpListX.add(tmpX+1);
                }
                if(tmpX-1>=0) {
                    tmpListY.add(tmpY);
                    tmpListX.add(tmpX-1);
                }
                if(tmpX+1<arr.length) {
                    tmpListY.add(tmpY);
                    tmpListX.add(tmpX+1);
                }
                if(tmpY+1<arr.length && tmpX-1>=0) {
                    tmpListY.add(tmpY+1);
                    tmpListX.add(tmpX-1);
                }
                if(tmpY+1<arr.length) {
                    tmpListY.add(tmpY+1);
                    tmpListX.add(tmpX);
                }
                if(tmpY+1<arr.length && tmpX+1<arr.length) {
                    tmpListY.add(tmpY+1);
                    tmpListX.add(tmpX+1);
                }
                //System.out.println("num 1 : "+num[1]+", "+i+", "+tmpY+", "+tmpX);
            }
        }

        num[1] = tmpListY.size();
        treesY[1] = new int[num[1]];
        treesX[1] = new int[num[1]];
        for(int i=0;i<num[1];i++) {
            int tmpY = tmpListY.peek();
            int tmpX = tmpListX.peek();
            treesY[1][i] = tmpY;
            treesX[1][i] = tmpX;
            tmpListY.poll();
            tmpListX.poll();
        }
    }
    static void fun5() {
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr.length;j++) {
                arr[i][j] += feed[i][j];
            }
        }
    }
}
/*
5 2 2
2 3 2 3 2
2 3 2 3 2
2 3 2 3 2
2 3 2 3 2
2 3 2 3 2
1 5 3
3 2 3
 */