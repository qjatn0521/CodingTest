import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


// 11:56 ~ 12 : 36
public class P5_3197 {
    static class Point {
        int y;
        int x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static int[] dx = {0,0,-1,+1};
    static int[] dy = {-1,+1,0,0};
    static Queue<Point> startQueue = new LinkedList();
    static Queue<Point> waterQueue = new LinkedList();
    static int[][] arr;
    static boolean[][] dp;
    static Point startPoint;
    static Point endPoint;
    public static void main(String[] args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            String[] inputs = bf.readLine().split(" ");
            arr = new int[Integer.parseInt(inputs[0])+2][Integer.parseInt(inputs[1])+2];
            dp = new boolean[Integer.parseInt(inputs[0])+2][Integer.parseInt(inputs[1])+2];

            for(int y=1;y<arr.length-1;y++) {
                String[] tmpInput = bf.readLine().split("");
                for(int x=1;x<=tmpInput.length;x++) {
                    String input = tmpInput[x-1];
                    if(input.equals("L")) {
                        if(startPoint!=null)  {
                            endPoint = new Point(y,x);
                            arr[y][x] = 4;
                        }
                        else {
                            startPoint = new Point(y,x);
                            arr[y][x] = 3;
                            startQueue.add(startPoint);
                        }
                        waterQueue.add(new Point(y,x));
                    } else if(input.equals("X")) {
                        arr[y][x] = 2;
                    } else {
                        arr[y][x] = 1;
                        waterQueue.add(new Point(y,x));
                    }
                }
            }

            int ans = 0;
            while(true) {
                if(move()) break;
                else {
                    ans++;
                    melt();
                }

            }
            bw.write(""+ans);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean move() {
        Queue<Point> newQueue = new LinkedList<>();
        while(!startQueue.isEmpty()){
            Point p = startQueue.poll();
            for(int j=0;j<4;j++) {
                int ny = p.y + dy[j];
                int nx = p.x + dx[j];

                if(dp[ny][nx]) continue;

                dp[ny][nx] = true;

                if(arr[ny][nx]==2) {
                    newQueue.add(new Point(ny,nx));
                } else if(arr[ny][nx]==1) {
                    startQueue.add(new Point(ny,nx));
                } else if(arr[ny][nx]==4) {
                    return true;
                }
            }
        }
        startQueue = newQueue;
        return false;
    }
    static void melt() {
        Queue<Point> newQueue = new LinkedList<>();
        while(!waterQueue.isEmpty()){
            Point p = waterQueue.poll();
            for(int j=0;j<4;j++) {
                int ny = p.y + dy[j];
                int nx = p.x + dx[j];
                if(arr[ny][nx]==2) {
                    arr[ny][nx] = 1;
                    newQueue.add(new Point(ny,nx));
                }
            }
        }
        waterQueue = newQueue;
    }
}