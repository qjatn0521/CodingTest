import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 백준 14503 1시도 통과
public class Main {
    static ArrayList<Integer> arrX = new ArrayList();
    static ArrayList<Integer> arrY = new ArrayList();
    static int[][] arr;
    static int min = 65;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            arr = new int[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[1])];


            for(int i=0;i<arr.length;i++) {
                tmp = bf.readLine().split(" ");
                for(int j=0;j<arr[0].length;j++) {
                    arr[i][j] = Integer.parseInt(tmp[j]);
                    if(arr[i][j]!=0 && arr[i][j]!=6) {
                        arrX.add(j);
                        arrY.add(i);
                    }
                }
            }
            fun0(0);
            for(int i=0;i<arr.length;i++) {
                bw.write(Arrays.toString(arr[i])+"\n");
            }
            bw.write(""+min);
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    static void fun0(int n) {
        if(n==arrX.size()) {
            fun5();
            return;
        }
        int x = arrX.get(n);
        int y = arrY.get(n);
        int num = arr[y][x];
        if(num==1) {
            fun1(x,y,true);
            fun0(n+1);
            fun1(x,y,false);
            fun2(x,y,true);
            fun0(n+1);
            fun2(x,y,false);
            fun3(x,y,true);
            fun0(n+1);
            fun3(x,y,false);
            fun4(x,y,true);
            fun0(n+1);
            fun4(x,y,false);
        } else if(num==2) {
            fun1(x,y,true);
            fun2(x,y,true);
            fun0(n+1);
            fun1(x,y,false);
            fun2(x,y,false);
            fun3(x,y,true);
            fun4(x,y,true);
            fun0(n+1);
            fun3(x,y,false);
            fun4(x,y,false);
        } else if(num==3) {
            fun1(x,y,true);
            fun3(x,y,true);
            fun0(n+1);
            fun1(x,y,false);
            fun3(x,y,false);
            fun1(x,y,true);
            fun4(x,y,true);
            fun0(n+1);
            fun1(x,y,false);
            fun4(x,y,false);
            fun2(x,y,true);
            fun3(x,y,true);
            fun0(n+1);
            fun2(x,y,false);
            fun3(x,y,false);
            fun2(x,y,true);
            fun4(x,y,true);
            fun0(n+1);
            fun2(x,y,false);
            fun4(x,y,false);
        } else if(num==4) {
            fun1(x,y,true);
            fun2(x,y,true);
            fun3(x,y,true);
            fun0(n+1);
            fun1(x,y,false);
            fun2(x,y,false);
            fun3(x,y,false);
            fun1(x,y,true);
            fun2(x,y,true);
            fun4(x,y,true);
            fun0(n+1);
            fun1(x,y,false);
            fun2(x,y,false);
            fun4(x,y,false);
            fun2(x,y,true);
            fun3(x,y,true);
            fun4(x,y,true);
            fun0(n+1);
            fun2(x,y,false);
            fun3(x,y,false);
            fun4(x,y,false);
            fun1(x,y,true);
            fun3(x,y,true);
            fun4(x,y,true);
            fun0(n+1);
            fun1(x,y,false);
            fun3(x,y,false);
            fun4(x,y,false);
        } else if(num==5) {
            fun1(x,y,true);
            fun2(x,y,true);
            fun3(x,y,true);
            fun4(x,y,true);
            fun0(n+1);
            fun1(x,y,false);
            fun2(x,y,false);
            fun3(x,y,false);
            fun4(x,y,false);
        }
    }

    static void fun1(int x, int y,boolean option) {
        //오른쪽
        while(x<arr[0].length && arr[y][x]!=6) {
            if(arr[y][x]<=0) {
                if(option) arr[y][x]--;
                else arr[y][x]++;
            }
            x++;
        }
    }
    static void fun2(int x, int y,boolean option) {
        //왼쪽
        while(x>=0 && arr[y][x]!=6) {
            if(arr[y][x]<=0) {
                if(option) arr[y][x]--;
                else arr[y][x]++;
            }
            x--;
        }
    }
    static void fun3(int x, int y,boolean option) {
        //아래
        while(y<arr.length && arr[y][x]!=6) {
            if(arr[y][x]<=0) {
                if(option) arr[y][x]--;
                else arr[y][x]++;
            }
            y++;
        }
    }
    static void fun4(int x, int y,boolean option) {
        //위
        while(y>=0 && arr[y][x]!=6) {
            if(arr[y][x]<=0) {
                if(option) arr[y][x]--;
                else arr[y][x]++;
            }
            y--;
        }
    }
    static void fun5() {
        int tmp = 0;
        for(int i=0;i<arr.length;i++) {
            System.out.print(Arrays.toString(arr[i])+"\n");
        }

        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[0].length;j++) {
                if(arr[i][j]==0) tmp++;
            }
        }
        if(tmp<min) min = tmp;
        System.out.println("ans :"+tmp+"\n");
    }
}