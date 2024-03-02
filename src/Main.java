import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 백준 14503 1시도 통과
public class Main {

    static int[][] arr;
    static int min = 4;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            arr = new int[Integer.parseInt(tmp[2])][Integer.parseInt(tmp[0])];
            int n =Integer.parseInt(tmp[1]);
            for(int i=0;i<n;i++) {
                tmp = bf.readLine().split(" ");
                arr[Integer.parseInt(tmp[0])-1][Integer.parseInt(tmp[1])-1] = 1;
                arr[Integer.parseInt(tmp[0])-1][Integer.parseInt(tmp[1])] = -1;
            }
            fun0(0);
            for(int i=0;i<arr.length;i++) {
                //bw.write(Arrays.toString(arr[i])+"\n");
            }
            if(min >=4) min = -1;
            bw.write(""+min);
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    static void fun0(int n) {
        boolean pass = fun1();
        if(pass) {
            if(n<min)
                min =n;
        } else if(n<3) {
            for(int i=0;i<arr.length;i++) {
                for(int j=0;j<arr[0].length-1;j++) {
                    if(arr[i][j]==0&&arr[i][j+1]==0) {
                        arr[i][j]=1;
                        arr[i][j+1]=-1;
                        fun0(n+1);
                        arr[i][j+1]=0;
                        arr[i][j]=0;
                    }
                }
            }
        }
    }

    static boolean fun1() {
        for(int i=0;i<arr[0].length;i++) {
            int x = i;
            for(int j=0;j<arr.length;j++) {
                if(arr[j][x]==1) x++;
                else if(arr[j][x]==-1) x--;
            }
            if(x!=i) return false;
        }
        return true;
    }
}