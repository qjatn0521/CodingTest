import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 10:47 ~ 11:31
public class G2_20061 {
    static LinkedList<int[]> blueTile = new LinkedList<>();
    static LinkedList<int[]> greenTile = new LinkedList<>();
    static int point = 0;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            int n = Integer.parseInt(tmp[0]);
            for(int i=0;i<6;i++) {
                blueTile.add(new int[4]);
                greenTile.add(new int[4]);
            }
            for(int i=0;i<n;i++) {
                tmp = bf.readLine().split(" ");
                fun(Integer.parseInt(tmp[2]),Integer.parseInt(tmp[1]),Integer.parseInt(tmp[0]));
            }
            int sum = 0;
            for(int i=0;i<6;i++) {
                int[] blue = blueTile.get(i);
                int[] green = greenTile.get(i);
                for(int j=0;j<4;j++) {
                    sum+=(blue[j]+green[j]);
                }
                //System.out.println(Arrays.toString(green)+"   "+Arrays.toString(blue));
            }
            bw.write(point+"\n"+sum);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void fun(int y, int x, int t) {
        if(t==1) {
            int greenY = 1;
            for(;greenY<5&&greenTile.get(greenY+1)[x]==0;greenY++) {}
            greenTile.get(greenY)[x] = 1;
            funGreen(greenY);

            int blueX = 1;
            for(;blueX<5&&blueTile.get(blueX+1)[y]==0;blueX++) {}
            blueTile.get(blueX)[y] = 1;
            funBlue(blueX);
        } else if(t==2) {
            int greenY = 1;
            for(;greenY<5&&greenTile.get(greenY+1)[x]==0;greenY++) {}
            greenTile.get(greenY)[x] = 1;
            greenTile.get(greenY-1)[x] = 1;
            funGreen2(greenY);

            int blueX = 1;
            for(;blueX<5&&blueTile.get(blueX+1)[y]==0&&blueTile.get(blueX+1)[y+1]==0;blueX++) {}
            blueTile.get(blueX)[y] = 1;
            blueTile.get(blueX)[y+1] = 1;
            funBlue(blueX);
        } else if(t==3) {
            int greenY = 1;
            for(;greenY<5&&greenTile.get(greenY+1)[x]==0&&greenTile.get(greenY+1)[x+1]==0;greenY++) {}
            greenTile.get(greenY)[x] = 1;
            greenTile.get(greenY)[x+1] = 1;
            funGreen(greenY);

            int blueX = 1;
            for(;blueX<5&&blueTile.get(blueX+1)[y]==0;blueX++) {}
            blueTile.get(blueX)[y] = 1;
            blueTile.get(blueX-1)[y] = 1;
            funBlue2(blueX);
        }
    }
    static void funGreen(int y) {
        if(y==0 || y==1) {
            greenTile.removeLast();
            greenTile.addFirst(new int[4]);
            return;
        }
        int[] arr = greenTile.get(y);

        if(arr[0]==1&&arr[1]==1&&arr[2]==1&&arr[3]==1) {
            point++;
            greenTile.remove(y);
            greenTile.addFirst(new int[4]);
        }
    }
    static void funGreen2(int y) {
        if(y==1) {
            greenTile.removeLast();
            greenTile.removeLast();
            greenTile.addFirst(new int[4]);
            greenTile.addFirst(new int[4]);
            return;
        }
        int[] arr = greenTile.get(y-1);

        if(arr[0]==1&&arr[1]==1&&arr[2]==1&&arr[3]==1) {
            point++;
            greenTile.remove(y-1);
            greenTile.addFirst(new int[4]);
        }
        arr = greenTile.get(y);

        if(arr[0]==1&&arr[1]==1&&arr[2]==1&&arr[3]==1) {
            point++;
            greenTile.remove(y++);
            greenTile.addFirst(new int[4]);
        }
        if(y==2) {
            greenTile.removeLast();
            greenTile.addFirst(new int[4]);
        }
    }
    static void funBlue(int x) {
        if(x==0 || x==1) {
            blueTile.removeLast();
            blueTile.addFirst(new int[4]);
            return;
        }
        int[] arr = blueTile.get(x);

        if(arr[0]==1&&arr[1]==1&&arr[2]==1&&arr[3]==1) {
            point++;
            blueTile.remove(x);
            blueTile.addFirst(new int[4]);
        }
    }
    static void funBlue2(int x) {
        if(x==1) {
            blueTile.removeLast();
            blueTile.removeLast();
            blueTile.addFirst(new int[4]);
            blueTile.addFirst(new int[4]);
            return;
        }
        int[] arr = blueTile.get(x-1);

        if(arr[0]==1&&arr[1]==1&&arr[2]==1&&arr[3]==1) {
            point++;
            blueTile.remove(x-1);
            blueTile.addFirst(new int[4]);
        }
        arr = blueTile.get(x);

        if(arr[0]==1&&arr[1]==1&&arr[2]==1&&arr[3]==1) {
            point++;
            blueTile.remove(x++);
            blueTile.addFirst(new int[4]);
        }
        if(x==2) {
            blueTile.removeLast();
            blueTile.addFirst(new int[4]);
        }
    }
}