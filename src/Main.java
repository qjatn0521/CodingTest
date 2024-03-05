import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 백준 14503 1시도 통과
public class Main {
    static String[][][] arr;
    static String[] colors = {"w","y","r","o","g","b"}; // 위, 아래, 앞, 뒤, 왼, 오
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            arr = new String[6][3][3];
            int n = Integer.parseInt(tmp[0]);
            for(int i=0;i<n;i++) {
                reset();
                String tmp2 = bf.readLine();
                tmp = bf.readLine().split(" ");
                for(int j=0;j<tmp.length;j++) {
                    char dir = tmp[j].charAt(0);
                    char clock = tmp[j].charAt(1);
                    fun1(dir,clock);
                }

                for(int j=0;j<3;j++) {
                    for(int k=0;k<3;k++) {
                        bw.write(arr[0][j][k]);
                    }
                    bw.write("\n");
                }/*
                bw.write("\n");
                for(int j=0;j<3;j++){
                    for(int k=0;k<3;k++) {
                        bw.write(arr[2][j][k]);
                    }
                    bw.write(" ");
                    for(int k=0;k<3;k++) {
                        bw.write(arr[5][j][k]);
                    }
                    bw.write(" ");
                    for(int k=0;k<3;k++) {
                        bw.write(arr[3][j][k]);
                    }
                    bw.write(" ");
                    for(int k=0;k<3;k++) {
                        bw.write(arr[4][j][k]);
                    }

                    bw.write("\n");
                }
                bw.write("\n");
                for(int j=0;j<3;j++) {
                    for(int k=0;k<3;k++) {
                        bw.write(arr[1][j][2-k]);
                    }
                    bw.write("\n");
                }
                bw.write("\n======\n");*/
            }
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    static void reset() {
        for(int i=0;i<6;i++) {
            for(int j=0;j<3;j++) {
                for(int k=0;k<3;k++) {
                    arr[i][j][k] = colors[i];
                }
            }
        }
    }
    static void fun1(char dir, char clock) {
        int[] address = new int[12];
        int[] addressy = new int[12];
        int[] addressx = new int[12];
        if(dir=='U') {
            fun2(0,clock);
            if(clock=='+') {
                fun3(2,0,2,0,0,address,addressy,addressx,0);
                fun3(4,0,2,0,0,address,addressy,addressx,3);
                fun3(3,0,2,0,0,address,addressy,addressx,6);
                fun3(5,0,2,0,0,address,addressy,addressx,9);
            } else {
                fun3(2,0,2,0,0,address,addressy,addressx,0);
                fun3(5,0,2,0,0,address,addressy,addressx,3);
                fun3(3,0,2,0,0,address,addressy,addressx,6);
                fun3(4,0,2,0,0,address,addressy,addressx,9);
            }
        } else if(dir=='D') {
            fun2(1,clock);
            if(clock=='+') {
                fun3(2,2,2,2,0,address,addressy,addressx,0);
                fun3(5,2,2,2,0,address,addressy,addressx,3);
                fun3(3,2,2,2,0,address,addressy,addressx,6);
                fun3(4,2,2,2,0,address,addressy,addressx,9);
            } else {
                fun3(2,2,2,2,0,address,addressy,addressx,0);
                fun3(4,2,2,2,0,address,addressy,addressx,3);
                fun3(3,2,2,2,0,address,addressy,addressx,6);
                fun3(5,2,2,2,0,address,addressy,addressx,9);
            }
        } else if(dir=='F') {
            fun2(2,clock);
            if(clock=='+') {
                fun3(0,2,0,2,2,address,addressy,addressx,0);
                fun3(5,0,0,2,0,address,addressy,addressx,3);
                fun3(1,2,0,2,2,address,addressy,addressx,6);
                fun3(4,2,2,0,2,address,addressy,addressx,9);
            } else {
                fun3(0,2,2,2,0,address,addressy,addressx,0);
                fun3(4,0,2,2,2,address,addressy,addressx,3);
                fun3(1,2,2,2,0,address,addressy,addressx,6);
                fun3(5,2,0,0,0,address,addressy,addressx,9);
            }
        } else if(dir=='B') {
            fun2(3,clock);
            // 완벽
            if(clock=='+') {
                fun3(0,0,2,0,0,address,addressy,addressx,0);
                fun3(4,0,0,2,0,address,addressy,addressx,3);
                fun3(1,0,2,0,0,address,addressy,addressx,6);
                fun3(5,2,2,0,2,address,addressy,addressx,9);
            } else {
                fun3(0,0,0,0,2,address,addressy,addressx,0);
                fun3(5,0,2,2,2,address,addressy,addressx,3);
                fun3(1,0,0,0,2,address,addressy,addressx,6);
                fun3(4,2,0,0,0,address,addressy,addressx,9);
            }
        } else if(dir=='L') {
            fun2(4,clock);
            if(clock=='+') {
                fun3(0,0,0,2,0,address,addressy,addressx,0);
                fun3(2,0,0,2,0,address,addressy,addressx,3);
                fun3(1,2,2,0,2,address,addressy,addressx,6);
                fun3(3,2,2,0,2,address,addressy,addressx,9);
            } else {
                fun3(0,2,0,0,0,address,addressy,addressx,0);
                fun3(3,0,2,2,2,address,addressy,addressx,3);
                fun3(1,0,2,2,2,address,addressy,addressx,6);
                fun3(2,2,0,0,0,address,addressy,addressx,9);
            }
        } else if(dir=='R') {
            fun2(5,clock);
            if(clock=='+') {
                fun3(0,2,2,0,2,address,addressy,addressx,0);
                fun3(3,0,0,2,0,address,addressy,addressx,3);
                fun3(1,0,0,2,0,address,addressy,addressx,6);
                fun3(2,2,2,0,2,address,addressy,addressx,9);
            } else {
                fun3(0,0,2,2,2,address,addressy,addressx,0);
                fun3(2,0,2,2,2,address,addressy,addressx,3);
                fun3(1,2,0,0,0,address,addressy,addressx,6);
                fun3(3,2,0,0,0,address,addressy,addressx,9);
            }
        }
        fun4(address,addressy,addressx);
    }

    static void fun4(int[] address, int[] addressy, int[] addressx) {
        String tmp1 = arr[address[9]][addressy[9]][addressx[9]];
        String tmp2 = arr[address[10]][addressy[10]][addressx[10]];
        String tmp3 = arr[address[11]][addressy[11]][addressx[11]];

        for(int i=11;i>2;i--) {
            arr[address[i]][addressy[i]][addressx[i]] = arr[address[i-3]][addressy[i-3]][addressx[i-3]];
        }
        arr[address[0]][addressy[0]][addressx[0]] = tmp1;
        arr[address[1]][addressy[1]][addressx[1]] = tmp2;
        arr[address[2]][addressy[2]][addressx[2]] = tmp3;
    }

    static void fun2(int n, char clock) {
        String[][] tmp = new String[3][3];
        if (clock == '+') {
            for(int i=0;i<3;i++) {
                for(int j=0;j<3;j++) {
                    tmp[i][j] = arr[n][2-j][i];
                }
            }
        } else {
            for(int i=0;i<3;i++) {
                for(int j=0;j<3;j++) {
                    tmp[i][j] = arr[n][j][2-i];
                }
            }
        }
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                arr[n][i][j] = tmp[i][j];
            }
        }
    }
    static void fun3(int n, int startY, int startX, int endY, int exdX,int[] a, int[] aY, int[] aX, int index) {
        for(int i=index;i<index+3;i++) {
            a[i] = n;
            if(startX==exdX) {
                aX[i] = startX;
                if(startY==2) {
                    aY[i] = startY-i+index;
                } else {
                    aY[i] = startY+i-index;
                }
            } else{
                aY[i] = startY;
                if(startX==2) {
                    aX[i] = startX-i+index;
                } else {
                    aX[i] = startX+i-index;
                }
            }
        }
    }
}
/*
2
16
U+ R+ R+
16
U+ R+ R+ F+
 */