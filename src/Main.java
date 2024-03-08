import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 12:44 ~ 3:21 (좀 많이 놈)
public class Main {
    static ArrayList<shark>[][] arr;
    static ArrayList<shark> sharks;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            arr = new ArrayList[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[1])];
            sharks = new ArrayList<>();
            int n = Integer.parseInt(tmp[2]);
            for(int i=0;i<n;i++) {
                tmp = bf.readLine().split(" ");
                sharks.add(new shark(Integer.parseInt(tmp[0])-1,Integer.parseInt(tmp[1])-1,Integer.parseInt(tmp[2]),Integer.parseInt(tmp[3]),Integer.parseInt(tmp[4])));
            }


            int ans = 0;
            for(int i=0;i<arr[0].length;i++) {
                shark s = null;
                int remove = 0;
                for(int j=0;j<sharks.size();j++) {
                    if(i==sharks.get(j).x && (s==null || s.y>sharks.get(j).y)) {
                        s=sharks.get(j);
                        remove = j;
                    }
                }
                if(s!=null) {
                    ans+= s.size;
                    sharks.remove(remove);
                }

                fun1();
                fun2();
                for(int k=0;k<sharks.size();k++) {
                    //bw.write("shark("+(k+1)+") : "+ sharks.get(k).y+", "+sharks.get(k).x+", "+sharks.get(k).size);
                }
                //bw.write("ans :"+ans+"\n");
            }


            bw.write(""+ans+"\n");
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    static void fun1() {
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[0].length;j++)
                arr[i][j] = new ArrayList<>();
        }
        //System.out.println("===============");
        for(int i=0;i<sharks.size();i++) {
            shark tmp = sharks.get(i);
            //System.out.println(tmp.y+", "+ tmp.x+", d:"+ tmp.d+", s:"+ tmp.size);
            if(tmp.d==1) {
                int y = tmp.y - (tmp.s%(2*arr.length-2));
                if(y < 0) {
                    tmp.d =2;
                    tmp.y = y*-1;
                    if(tmp.y>= arr.length-1) {
                        tmp.d = 1;
                        tmp.y = 2*(arr.length-1) - tmp.y;
                    }
                } else {
                    tmp.y = y;
                }
            } else if(tmp.d==3) {
                int x = tmp.x + (tmp.s%(2*arr[0].length-2));
                if(x >= arr[0].length) {
                    tmp.d = 4;
                    tmp.x =  2*(arr[0].length-1) - x;
                    if(tmp.x < 0) {
                        tmp.d = 3;
                        tmp.x = tmp.x*-1;
                    }
                } else {
                    tmp.x = x;
                }
            } else if(tmp.d==2) {
                int y = tmp.y + (tmp.s%(2*arr.length-2));
                if(y >= arr.length) {
                    tmp.d =1;
                    tmp.y = 2*(arr.length-1) - y;
                    if(tmp.y<0) {
                        tmp.d = 2;
                        tmp.y = tmp.y*-1;
                    }
                } else {
                    tmp.y = y;
                }
            } else if(tmp.d==4) {
                int x = tmp.x - (tmp.s%(2*arr[0].length-2));
                if(x < 0) {
                    tmp.d = 3;
                    tmp.x = x*-1;
                    if(tmp.x >= arr[0].length-1) {
                        tmp.d = 4;
                        tmp.x =  2*(arr[0].length-1) - tmp.x;
                    }
                } else {
                    tmp.x = x;
                }
            }
            //System.out.println(tmp.y+", "+ tmp.x+", d:"+ tmp.d+", s:"+ tmp.s+"\n");
            if(!arr[tmp.y][tmp.x].isEmpty() && arr[tmp.y][tmp.x].get(0).size < tmp.size) {
                arr[tmp.y][tmp.x].add(0,tmp);
            } else if(!arr[tmp.y][tmp.x].isEmpty() && arr[tmp.y][tmp.x].get(0).size >= tmp.size){
                arr[tmp.y][tmp.x].add(tmp);
            } else if(arr[tmp.y][tmp.x].isEmpty()) {
                arr[tmp.y][tmp.x].add(tmp);
            }

        }
    }
    static void fun2(){
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[0].length;j++) {
                for(int k=1;k<arr[i][j].size();k++) {
                    sharks.remove(arr[i][j].get(k));
                }
            }
        }
    }

    static class shark{
        public int y;
        public int x;
        public  int s;
        public int d;
        public int size;

        public shark(int i, int i1, int i2, int i3, int i4) {
            y = i;
            x = i1;
            s = i2;
            d= i3;
            size = i4;
        }
    }
}
/*

 */