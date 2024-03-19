import java.io.*;
import java.util.LinkedList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 12:21 ~ 14:21
public class G2_19238 {
    static int[][] arr;
    static int[][][][] arrRange;
    static int fuel =0;
    static obj car;
    static LinkedList<obj> customer = new LinkedList<>();
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            arr = new int[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[0])];
            arrRange = new int[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[0])][Integer.parseInt(tmp[0])][Integer.parseInt(tmp[0])];
            int n = Integer.parseInt(tmp[1]);
            fuel = Integer.parseInt(tmp[2]);

            for(int i=0;i<arr.length;i++) {
                tmp = bf.readLine().split(" ");
                for(int j=0;j<arr.length;j++) {
                    arr[i][j] = Integer.parseInt(tmp[j]);
                }
            }
            tmp = bf.readLine().split(" ");
            car = new obj(Integer.parseInt(tmp[0])-1,Integer.parseInt(tmp[1])-1);

            for(int i=0;i<n;i++) {
                tmp = bf.readLine().split(" ");
                customer.add(new obj(Integer.parseInt(tmp[0])-1,Integer.parseInt(tmp[1])-1,Integer.parseInt(tmp[2])-1,Integer.parseInt(tmp[3])-1));
            }

            fun();
            bw.write(""+fuel);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void fun() {
        if(customer.size()==0) {
            return;
        }
        int minRoute = 400;
        obj minCus = new obj(0,0);
        int[][] tmpMap = new int[arr.length][arr.length];
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr.length;j++) {
                tmpMap[i][j] = arr[i][j];
            }
        }

        findRoute(car.y, car.x,tmpMap);
        for(int i=0;i<customer.size();i++) {
            obj o = customer.get(i);
            int range = tmpMap[o.y][o.x]-1;
            if(range<minRoute) {
                minRoute = range;
                minCus = o;
            } else if(range==minRoute) {
                if(minCus.y>o.y || (minCus.y==o.y && minCus.x>o.x)) {
                    minRoute = range;
                    minCus = o;
                }
            }
        }

        fuel -= minRoute;
        if(fuel<=0||minRoute==400||minRoute<0) {
            fuel = -1;
            return;
        }
        customer.remove(minCus);

        car.y = minCus.y;
        car.x = minCus.x;
        tmpMap = new int[arr.length][arr.length];
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr.length;j++) {
                tmpMap[i][j] = arr[i][j];
            }
        }
        findRoute(car.y, car.x,tmpMap);
        int range = tmpMap[minCus.destY][minCus.destX]-1;

        if(range==-1) {
            fuel = -1;
            return;
        }
        fuel -= range;
        if(fuel<0) {
            fuel = -1;
            return;
        }
        fuel += range*2;
        //System.out.println("find :"+minCus.y +", "+minCus.x+", "+minRoute);

        car.y = minCus.destY;
        car.x = minCus.destX;
        fun();
    }

    static void findRoute(int startY, int startX,int[][] tmpMap) {
        tmpMap[startY][startX] = 1;
        findRoute(startY,startX, 2,tmpMap);
    }
    static void findRoute(int curY, int curX, int value,int[][] tmpMap) {
        if(curY>0 && (tmpMap[curY-1][curX]>value || tmpMap[curY-1][curX]==0)) {
            tmpMap[curY-1][curX] = value;
            findRoute(curY-1,curX,value+1,tmpMap);
        }
        if(curX>0 && (tmpMap[curY][curX-1]>value || tmpMap[curY][curX-1]==0)) {
            tmpMap[curY][curX-1] = value;
            findRoute(curY,curX-1,value+1,tmpMap);
        }
        if(curY+1<tmpMap.length && (tmpMap[curY+1][curX]>value || tmpMap[curY+1][curX]==0)) {
            tmpMap[curY+1][curX] = value;
            findRoute(curY+1,curX,value+1,tmpMap);
        }
        if(curX+1<tmpMap.length && (tmpMap[curY][curX+1]>value || tmpMap[curY][curX+1]==0)) {
            tmpMap[curY][curX+1] = value;
            findRoute(curY,curX+1,value+1,tmpMap);
        }
    }
    static class obj {
        int y;
        int x;
        int destY=-1;
        int destX=-1;

        obj(int y, int x, int destY, int destX) {
            this.y = y;
            this.x = x;
            this.destX = destX;
            this.destY = destY;
        }
        obj(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
/*
 */