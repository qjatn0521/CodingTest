import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 14:00 ~ 14:47
public class Main {
    static int[][] arr;
    static ArrayList<dataXY> virus = new ArrayList<dataXY>();
    static dataXY[] selectVirus;
    static int minTime = 100000000;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            arr = new int[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[0])];
            selectVirus = new dataXY[Integer.parseInt(tmp[1])];
            for(int i=0;i< arr.length;i++) {
                tmp = bf.readLine().split(" ");
                for (int j = 0; j < arr.length; j++) {
                    arr[i][j] = Integer.parseInt(tmp[j]);
                    if(arr[i][j]==2) virus.add(new dataXY(j,i));
                }
            }
            int ans = 0;
            fun1(0,0);
            if(minTime == 100000000) minTime = -1;

            bw.write(""+minTime);
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    static void fun1(int n, int size) {
        if(size == selectVirus.length) {
            //System.out.println("size :"+size+", n : "+n);
            int tmpTime = fun2();
            minTime = minTime >tmpTime ? tmpTime : minTime;
            return;
        }
        for(int i=n;i<virus.size();i++) {
            selectVirus[size] = new dataXY(virus.get(i).x,virus.get(i).y);
            fun1(i+1,size+1);
        }
    }

    static int fun2() {
        int [][]tmp = new int[arr.length][arr.length];
        int sum = 0;
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr.length;j++) {
                tmp[i][j] = arr[i][j];
                if(tmp[i][j]!=0) sum++;
            }
        }
        Queue<dataXY> tmp1 = new LinkedList<>();
        for(int i=0;i<selectVirus.length;i++) {
            tmp1.add(new dataXY(selectVirus[i].x,selectVirus[i].y));
        }
        int time = 0;
        while(sum<arr.length* arr.length && !tmp1.isEmpty() && time<minTime) {
            time++;
            int size = tmp1.size();
            for(int i=0;i<size;i++) {
                dataXY tmpVirus = tmp1.poll();
                if(tmpVirus.x-1>=0 && (tmp[tmpVirus.y][tmpVirus.x-1]==2 || tmp[tmpVirus.y][tmpVirus.x-1]==0)) {
                    if(tmp[tmpVirus.y][tmpVirus.x-1]==0) sum++;
                    tmp[tmpVirus.y][tmpVirus.x-1] = 3;
                    tmp1.add(new dataXY(tmpVirus.x-1, tmpVirus.y));
                }
                if(tmpVirus.y-1>=0 && (tmp[tmpVirus.y-1][tmpVirus.x]==2 || tmp[tmpVirus.y-1][tmpVirus.x]==0)) {
                    if(tmp[tmpVirus.y-1][tmpVirus.x]==0) sum++;
                    tmp[tmpVirus.y-1][tmpVirus.x] = 3;
                    tmp1.add(new dataXY(tmpVirus.x, tmpVirus.y-1));
                }
                if(tmpVirus.x+1<tmp.length && (tmp[tmpVirus.y][tmpVirus.x+1]==2 || tmp[tmpVirus.y][tmpVirus.x+1]==0)) {
                    if(tmp[tmpVirus.y][tmpVirus.x+1]==0) sum++;
                    tmp[tmpVirus.y][tmpVirus.x+1] = 3;
                    tmp1.add(new dataXY(tmpVirus.x+1, tmpVirus.y));
                }
                if(tmpVirus.y+1<tmp.length && (tmp[tmpVirus.y+1][tmpVirus.x]==2 || tmp[tmpVirus.y+1][tmpVirus.x]==0)) {
                    if(tmp[tmpVirus.y+1][tmpVirus.x]==0) sum++;
                    tmp[tmpVirus.y+1][tmpVirus.x] = 3;
                    tmp1.add(new dataXY(tmpVirus.x, tmpVirus.y+1));
                }
            }
        }
        //System.out.println("time : "+time+", size : "+sum);
        for (int i=0;i< arr.length;i++) {
            //System.out.println(Arrays.toString(tmp[i]));
        }
        if(sum!= arr.length* arr.length) return 100000000;

        return time;
    }
    static class dataXY {
        int x;
        int y;
        dataXY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
/*

 */