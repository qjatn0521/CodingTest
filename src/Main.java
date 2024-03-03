import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 백준 14503 1시도 통과
public class Main {

    static int[][] arr;
    static ArrayList<Integer> directions = new ArrayList<>();
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            arr = new int[101][101];
            int n =Integer.parseInt(tmp[0]);
            for(int i=0;i<n;i++) {
                tmp = bf.readLine().split(" ");
                directions.clear();
                int tmpDir = Integer.parseInt(tmp[2]);
                int tmpX = Integer.parseInt(tmp[0]);
                int tmpY = Integer.parseInt(tmp[1]);
                directions.add(tmpDir);
                arr[tmpY][tmpX]++;
                if(tmpDir == 0) {
                    arr[tmpY][tmpX+1]++;
                    tmpX+=1;
                } else if(tmpDir == 1) {
                    arr[tmpY-1][tmpX]++;
                    tmpY-=1;
                } else if(tmpDir == 2) {
                    arr[tmpY][tmpX-1]++;
                    tmpX-=1;
                } else {
                    arr[tmpY+1][tmpX]++;
                    tmpY+=1;
                }
                fun0(tmpX,tmpY,Integer.parseInt(tmp[3]));
            }
            int ans = 0;
            for(int i=0;i<arr.length-1;i++) {
                //bw.write(Arrays.toString(arr[i])+"\n");
                for(int j=0;j<arr.length-1;j++) {
                    if(arr[i][j]>0 && arr[i][j+1]>0 && arr[i+1][j]>0 && arr[i+1][j+1]>0) {
                        ans++;
                    }
                }
            }
            bw.write(""+ans);
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    static void fun0(int x,int y, int age) {
        //System.out.println("\nx :"+x+",y : "+y+", age : "+age);
        if(age<=0) return;
        int curSize = directions.size();
        for(int i=curSize-1;i>=0;i--) {
            int tmpDir = directions.get(i);
            if(tmpDir == 0) {
                arr[y-1][x]++;
                y-=1;
                tmpDir+=1;
            } else if(tmpDir == 1) {
                arr[y][x-1]++;
                x-=1;
                tmpDir+=1;
            } else if(tmpDir == 2) {
                arr[y+1][x]++;
                y+=1;
                tmpDir+=1;
            } else {
                arr[y][x+1]++;
                x+=1;
                tmpDir=0;
            }
            directions.add(tmpDir);
        }
        fun0(x,y,age-1);
    }

}
/*
3
3 3 0 2
4 2 1 3
4 2 2 1
 */