import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 15:05
public class Main {
    static int[][] arr;
    static Queue<Data>[][] arrQ;
    static Data[] dataList;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            arr = new int[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[0])];;
            arrQ = new Queue[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[0])];
            int n = Integer.parseInt(tmp[1]);
            dataList = new Data[n];
            for(int i=0;i< arr.length;i++) {
                tmp = bf.readLine().split(" ");
                for (int j = 0; j < arr.length; j++) {
                    arr[i][j] = Integer.parseInt(tmp[j]);
                    arrQ[i][j] = new LinkedList<>();
                }
            }
            for(int i=0;i<n;i++) {
                tmp = bf.readLine().split(" ");
                int y = Integer.parseInt(tmp[0])-1;
                int x = Integer.parseInt(tmp[1])-1;
                Data data = new Data(Integer.parseInt(tmp[2]),i+1,y,x);
                dataList[i] = data;
                arrQ[y][x].add(data);
            }
            int ans = fun(0);
            bw.write(""+ans);
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    static int fun(int time) {
        for(int i=0;i< dataList.length;i++) {
            System.out.print("data("+dataList[i].value+") : "+dataList[i].y+","+dataList[i].x+","+dataList[i].dir+"     ");
        }

        System.out.println();
        if(time>8) return -1;
        boolean pass = true;
        Data data0 = dataList[0];
        for(Data data: dataList) {
            if(!(data0.y==data.y && data0.x==data.x)) pass = false;
        }
        if(pass) return time;
        for(int i=0;i<dataList.length;i++) {
            fun1(dataList[i]);
        }


        return fun(time+1);
    }

    static void fun1(Data data) {
        int nextY=data.y;
        int nextX=data.x;
        int nextDir = data.dir;
        if(data.dir==1) {
            if(data.x==arr.length-1||arr[data.y][data.x+1]==2) {
                nextX = data.x-1;
                nextDir = 2;
            } else nextX = data.x+1;
        } else if(data.dir==2) {
            if(data.x==0||arr[data.y][data.x-1]==2)  {
                nextX = data.x+1;
                nextDir = 1;
            }
            else nextX = data.x-1;
        } else if(data.dir==3) {
            if(data.y==0||arr[data.y-1][data.x]==2) {
                nextY = data.y+1;
                nextDir = 4;
            }
            else nextY = data.y-1;
        } else if(data.dir==4) {
            if(data.y==arr.length-1||arr[data.y+1][data.x]==2) {
                nextY = data.y-1;
                nextDir = 3;
            }
            else nextY = data.y+1;
        }
        data.dir = nextDir;
        fun2(nextY,nextX,data);
    }

    static void fun2(int nextY, int nextX, Data data) {
        //System.out.println("y:"+nextY+", x"+nextX);
        if(nextY<0 || nextX<0 || nextY>=arr.length || nextX >= arr.length) return;
        System.out.println();
        for(int i=0;i< arr.length;i++) {
            for(int j=0;j< arr.length;j++) {
                System.out.print(arrQ[i][j].size()+" ");
            }
            System.out.println();
        }
        if(arr[nextY][nextX]==0) {
            Iterator<Data> iterator = arrQ[data.y][data.x].iterator();
            while(iterator.next()!=data);
            iterator.remove();
            data.y = nextY;
            data.x = nextX;
            arrQ[nextY][nextX].add(data);
            while (iterator.hasNext()) {
                Data element = iterator.next();
                element.y = nextY;
                element.x = nextX;
                arrQ[nextY][nextX].add(element);
                iterator.remove();
            }
        } else if(arr[nextY][nextX]==1) {
            Iterator<Data> iterator = arrQ[data.y][data.x].iterator();
            Stack<Data> tmp = new Stack<>();
            while(iterator.next()!=data);
            iterator.remove();
            data.y = nextY;
            data.x = nextX;
            tmp.add(0,data);
            while (iterator.hasNext()) {
                Data element = iterator.next();
                element.y = nextY;
                element.x = nextX;
                tmp.add(0,element);
                iterator.remove();
            }
            for(int i=0;i<tmp.size();i++) {
                Data tmpData = tmp.get(i);
                arrQ[nextY][nextX].add(tmpData);
            }
        }
    }

    static class Data{
        int dir;
        int value;
        int y;
        int x;
        Data (int dir, int value,int y, int x) {
            this.dir = dir;
            this.value = value;
            this.y = y;
            this.x = x;
        }
    }


}
/*

 */