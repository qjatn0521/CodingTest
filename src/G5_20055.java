import java.io.*;
import java.util.LinkedList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 14:32 ~ 15:12
public class G5_20055 {
    static int[] belt;
    static int[] robot;
    static int k;
    static int broke=0;
    static int up =0;
    static int down =0;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            k = Integer.parseInt(tmp[1]);
            down = Integer.parseInt(tmp[0])-1;
            belt = new int[Integer.parseInt(tmp[0])*2];
            robot = new int[Integer.parseInt(tmp[0])*2];
            tmp = bf.readLine().split(" ");
            for(int i=0;i<belt.length;i++) {
                belt[i] = Integer.parseInt(tmp[i]);
            }
            int ans = fun(0);

            bw.write(""+ans);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int fun(int time) {
        if(broke>=k) {
            //System.out.println(time);
            return time;
        }

        int maxLen = belt.length-1;

        up--;down--;
        if(up==-1) up = maxLen;
        if(down==-1) down = maxLen;

        if(robot[down]==1) robot[down]=0;

        if(up<down) {
            for(int i=down-1;i>up;i--) {
                if(robot[i]==1) {
                    if(robot[i+1]==0&&belt[i+1]>0) {
                        robot[i] = 0;
                        robot[i+1] =1;
                        if(--belt[i+1]==0) broke++;
                    }
                }
            }
        } else {
            for(int i=down-1;i>=0;i--) {
                if(robot[i]==1&&robot[i+1]==0&&belt[i+1]>0) {
                    robot[i] = 0;
                    robot[i+1] =1;
                    if(--belt[i+1]==0) broke++;
                }
            }
            for(int i=maxLen;i>up;i--) {
                if(robot[i]==1) {
                    if((i==maxLen&&robot[0]==0&&belt[0]>0)) {
                        robot[i] = 0;
                        robot[0] =1;
                        if(--belt[0]==0) broke++;
                    } else if(i<maxLen&&robot[i+1]==0&&belt[i+1]>0) {
                        robot[i] = 0;
                        robot[i+1] =1;
                        if(--belt[i+1]==0) broke++;
                    }
                }
            }
        }
        if(robot[down]==1) robot[down]=0;
        if(belt[up]>0) {
            robot[up]=1;
            if(--belt[up]==0) broke++;
        }
/*
        if(up<down) {
            for (int i = up; i <= down; i++) {
                System.out.print(belt[i]+", ");
            }
            System.out.println("a!");
            for(int i=down+1;i<=maxLen;i++) {
                System.out.print(belt[i]+", ");
            }
            for(int i=0;i<up;i++) {
                System.out.print(belt[i]+", ");
            }
        } else {
            for (int i = up; i <= maxLen; i++) {
                System.out.print(belt[i]+", ");
            }
            for(int i=0;i<=down;i++) {
                System.out.print(belt[i]+", ");
            }
            System.out.println("b!");
            for(int i=down+1;i<up;i++) {
                System.out.print(belt[i]+", ");
            }
        }
        System.out.println("\n=================");*/
        return fun(time+1);
    }
}