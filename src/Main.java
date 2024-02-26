import java.io.*;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 백준 14503 1시도 통과
public class Main {
    public static void main(String[] args) {
        int[] plusY = {-1,0,1,0};
        int[] plusX = {0,1,0,-1};
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            int[][] arr = new int[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[1])];
            tmp = bf.readLine().split(" ");
            int x = Integer.parseInt(tmp[1]);
            int y = Integer.parseInt(tmp[0]);
            int direction = Integer.parseInt(tmp[2]);
            for(int i=0;i<arr.length;i++) {
                tmp = bf.readLine().split(" ");
                for(int j=0;j<tmp.length;j++) {
                    arr[i][j] = Integer.parseInt(tmp[j]);
                }
            }
            int num = 0;

            while(true) {
                if(arr[y][x]==0) {
                    // 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
                    arr[y][x]=2;
                    num++;
                } else if((arr[y][x+1]==0)||(arr[y][x-1]==0)||(arr[y+1][x]==0)||(arr[y-1][x]==0)) {
                    // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
                    if(--direction<0) direction = 3;
                    int newY = y+plusY[direction];
                    int newX = x+plusX[direction];
                    if(arr[newY][newX]==0) {
                        y = newY;
                        x = newX;
                    }
                } else {
                    // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
                    int newY = y-plusY[direction];
                    int newX = x-plusX[direction];
                    if(arr[newY][newX]==1) {
                        break;
                    } else {
                        y = newY;
                        x = newX;
                    }
                }
            }

            bw.write(""+num);
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}