import java.io.*;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 백준 14503 1시도 통과
public class Main {
    static int[][] arr = new int[4][8];
    static int[] at = {0,0,0,0};
    static int[] tmpAt ={0,0,0,0};
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp;

            for(int i=0;i<4;i++) {
                tmp = bf.readLine().split("");
                for(int j=0;j<8;j++) {
                    arr[i][j] = Integer.parseInt(tmp[j]);
                }
            }
            int n = Integer.parseInt(bf.readLine());
            for(int i=0;i<n;i++) {
                tmp = bf.readLine().split(" ");
                int dir = Integer.parseInt(tmp[1]);
                int num = Integer.parseInt(tmp[0])-1;
                fun0(num,dir);
                fun1(num,dir);
                for(int j=0;j<4;j++) {
                    if(tmpAt[j]>=0)
                        at[j] = tmpAt[j];
                    tmpAt[j] = -1;
                }
                //bw.write(i+":"+Arrays.toString(at)+"\n");
            }

            bw.write((1*arr[0][at[0]]+2*arr[1][at[1]]+4*arr[2][at[2]]+8*arr[3][at[3]])+"");
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    static void fun0(int n,int dir) {
        if(n==3) {
            if(dir==1) {
                tmpAt[n] = at[n]-1;
            } else {
                tmpAt[n] = at[n]+1;
            }

            if(tmpAt[n]<0) tmpAt[n] = 7;
            else if(tmpAt[n]>7) tmpAt[n]=0;
            return;
        }
        int at0 = at[n]+2>7 ? at[n]+2-8 : at[n]+2;
        int at1 = at[n+1]-2<0 ? at[n+1]-2+8 : at[n+1]-2;
        if(arr[n][at0]==arr[n+1][at1]) {
            //fun0(n+1,dir);
        } else {
            fun0(n+1,dir*-1);
        }

        if(dir==1) {
            tmpAt[n] = at[n]-1;
        } else {
            tmpAt[n] = at[n]+1;
        }

        if(tmpAt[n]<0) tmpAt[n] = 7;
        else if(tmpAt[n]>7) tmpAt[n]=0;


    }
    static void fun1(int n,int dir) {
        if(n==0) {
            return;
        }
        int at0 = at[n]-2<0 ? at[n]-2+8 : at[n]-2;
        int at1 = at[n-1]+2>7 ? at[n-1]+2-8 : at[n-1]+2;
        if(arr[n][at0]==arr[n-1][at1]) {
            return;
            //fun1(n-1,dir);
        } else {
            dir *=-1;
            fun1(n-1,dir);
        }

        if(dir==1) {
            tmpAt[n-1] = at[n-1]-1;
        } else {
            tmpAt[n-1] = at[n-1]+1;
        }

        if(tmpAt[n-1]<0) tmpAt[n-1] = 7;
        else if(tmpAt[n-1]>7) tmpAt[n-1]=0;
    }

}
/*

01100110
10001111
00011000
01110001
3
3 -1
3 1
1 1
 */