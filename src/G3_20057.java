import java.io.*;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 16:04 ~
public class G3_20057 {
    static int[][] arr;
    static int dustSum =0;
    static int[] arrY = {1,0,-1,0};
    static int[] arrX = {0,1,0,-1};
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            int n = Integer.parseInt(tmp[0]);
            arr = new int[n][n];
            for(int i=0;i<n;i++) {
                tmp = bf.readLine().split(" ");
                for(int j=0;j<n;j++) {
                    arr[i][j] = Integer.parseInt(tmp[j]);
                }
            }
            fun(0);
            bw.write(""+dustSum);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void fun(int n) {
        //System.out.println("n : "+n);
        if(arr.length/2==n)  {
            //fun2(n-1,n,3);
        }
        else fun(n+1);
        for(int i=2+n;i<arr.length-n;i++) {
            fun2(n,i,0);
        }
        for(int i=1+n;i<arr.length-n;i++) {
            fun2(i, arr.length-1-n,1);
        }
        for(int i= arr.length-2-n;i>= n;i--) {
            fun2(arr.length-1-n,i,2);
        }
        for(int i= arr.length-2-n;i>= n-1&&i>=0;i--) {
            fun2(i,n,3);
        }
    }
    static void fun2(int x, int y,int d) {
        //System.out.println("x : "+x+", y : "+y+", d : "+d);
        if(arr[y][x]==0) return;
        int dust = arr[y][x];

        dust -= arr[y][x]*5/100;
        if(x+arrX[d]*2>=0 &&x+arrX[d]*2< arr.length&&y+arrY[d]*2>=0 && y+arrY[d]*2< arr.length) {
            arr[y+arrY[d]*2][x+arrX[d]*2] +=arr[y][x]*5/100;
        } else {
            dustSum+=arr[y][x]*5/100;
        }
        dust -= (arr[y][x]*10/100)*2;
        if(x+arrX[d]+arrX[(d+1)%4]>=0 &&x+arrX[d]+arrX[(d+1)%4]<arr.length&&y+arrY[d]+arrY[(d+1)%4]>=0 && y+arrY[d]+arrY[(d+1)%4]< arr.length) {
            arr[y+arrY[d]+arrY[(d+1)%4]][x+arrX[d]+arrX[(d+1)%4]] +=arr[y][x]*10/100;
        } else {
            dustSum+=arr[y][x]*10/100;
        }
        if(x+arrX[d]+arrX[(d+3)%4]>=0 &&x+arrX[d]+arrX[(d+3)%4]<arr.length&&y+arrY[d]+arrY[(d+3)%4]>=0 && y+arrY[d]+arrY[(d+3)%4]< arr.length) {
            arr[y+arrY[d]+arrY[(d+3)%4]][x+arrX[d]+arrX[(d+3)%4]] +=arr[y][x]*10/100;
        } else {
            dustSum+=arr[y][x]*10/100;
        }

        dust -= (arr[y][x]*7/100)*2;
        if(x+arrX[(d+1)%4]>=0 &&x+arrX[(d+1)%4]<arr.length&&y+arrY[(d+1)%4]>=0 && y+arrY[(d+1)%4]< arr.length) {
            arr[y+arrY[(d+1)%4]][x+arrX[(d+1)%4]] +=arr[y][x]*7/100;
        } else {
            dustSum+=arr[y][x]*7/100;
        }
        if(x+arrX[(d+3)%4]>=0 &&x+arrX[(d+3)%4]<arr.length&&y+arrY[(d+3)%4]>=0 && y+arrY[(d+3)%4]< arr.length) {
            arr[y+arrY[(d+3)%4]][x+arrX[(d+3)%4]] +=arr[y][x]*7/100;
        } else {
            dustSum+=arr[y][x]*7/100;
        }

        dust -= (arr[y][x]*2/100)*2;
        if(x+arrX[(d+1)%4]*2>=0 &&x+arrX[(d+1)%4]*2<arr.length&&y+arrY[(d+1)%4]*2>=0 && y+arrY[(d+1)%4]*2< arr.length) {
            arr[y+arrY[(d+1)%4]*2][x+arrX[(d+1)%4]*2] +=arr[y][x]*2/100;
        } else {
            dustSum+=arr[y][x]*2/100;
        }
        if(x+arrX[(d+3)%4]*2>=0 &&x+arrX[(d+3)%4]*2<arr.length&&y+arrY[(d+3)%4]*2>=0 && y+arrY[(d+3)%4]*2< arr.length) {
            arr[y+arrY[(d+3)%4]*2][x+arrX[(d+3)%4]*2] +=arr[y][x]*2/100;
        } else {
            dustSum+=arr[y][x]*2/100;
        }
        dust -= (arr[y][x]/100)*2;
        if(x+arrX[(d+2)%4]+arrX[(d+1)%4]>=0 &&x+arrX[(d+2)%4]+arrX[(d+1)%4]<arr.length&&y+arrY[(d+2)%4]+arrY[(d+1)%4]>=0 && y+arrY[(d+2)%4]+arrY[(d+1)%4]< arr.length) {
            arr[y+arrY[(d+2)%4]+arrY[(d+1)%4]][x+arrX[(d+2)%4]+arrX[(d+1)%4]] +=arr[y][x]*1/100;
        } else {
            dustSum+=arr[y][x]/100;
        }
        if(x+arrX[(d+2)%4]+arrX[(d+3)%4]>=0 &&x+arrX[(d+2)%4]+arrX[(d+3)%4]<arr.length&&y+arrY[(d+2)%4]+arrY[(d+3)%4]>=0 && y+arrY[(d+2)%4]+arrY[(d+3)%4]< arr.length) {
            arr[y+arrY[(d+2)%4]+arrY[(d+3)%4]][x+arrX[(d+2)%4]+arrX[(d+3)%4]] +=arr[y][x]*1/100;
        } else {
            dustSum+=arr[y][x]/100;
        }

        if(x+arrX[d]>=0 &&x+arrX[d]<arr.length&&y+arrY[d]>=0 && y+arrY[d]< arr.length) {
            arr[y+arrY[d]][x+arrX[d]] +=dust;
        } else {
            dustSum+=dust;
        }
        arr[y][x]=0;
        //System.out.println("dust Sum : "+dustSum);
        for(int i=0;i< arr.length;i++) {
            //System.out.println(Arrays.toString(arr[i]));
        }
    }

}