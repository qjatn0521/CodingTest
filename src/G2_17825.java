import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 15:00
public class G2_17825 {
    static int[] arr;
    static int[] nums = new int[51];
    static int[] mals = new int[4];
    static int[] tmp = new int[10];
    static int ans = 0;
    static int tmpNum =0;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            arr = new int[10];
            for(int i=0;i<10;i++) {
                arr[i] = Integer.parseInt(tmp[i]);
            }
            nums[10] = 13; nums[13] = 15; nums[15] = 19; nums[19] = 25;
            nums[20] = 21; nums[21] = 23; nums[23] = 25;
            nums[30] = 31; nums[31] = 27; nums[27] = 33; nums[33] = 25;
            nums[25] = 37; nums[37] = 35; nums[35] = 40;

            fun(0,0);

            bw.write(""+ans);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void fun(int start, int pointSum) {
       /* if(tmp[0]==1&&tmp[1]==1&&tmp[2]==1&&tmp[3]==2&&tmp[4]==3&&tmp[5]==3&&tmp[6]==3&&tmp[7]==3) {
            System.out.println("start : " +start+ ", pointSum : "+pointSum);
            System.out.println(Arrays.toString(tmp));
            System.out.println();
        }*/
        for(int i=start;i<start+1&&i<10;i++) {
            int pre = mals[0];
            int point = fun2(0,arr[i]);
            tmp[i] = 1;
            if(pre!=mals[0]&&fun3(0))
                fun(i+1,pointSum+point);
            mals[0] = pre;

            pre = mals[1];
            point = fun2(1,arr[i]);
            tmp[i] = 2;
            if(pre!=mals[1]&&fun3(1))
                fun(i+1,pointSum+point);
            mals[1] = pre;

            pre = mals[2];
            point = fun2(2,arr[i]);
            tmp[i] = 3;
            if(pre!=mals[2]&&fun3(2))
                fun(i+1,pointSum+point);
            mals[2] = pre;

            pre = mals[3];
            point = fun2(3,arr[i]);
            tmp[i] = 4;
            if(pre!=mals[3]&&fun3(3))
                fun(i+1,pointSum+point);
            mals[3] = pre;
        }
        if(start == 10)
            ans = ans > pointSum ? ans : pointSum;
    }

    private static int fun2(int what, int how) {
        if(mals[what]>40) return 0;
        if(nums[mals[what]]>0) {
            int at = nums[mals[what]];
            for(int i=1;i<how;i++) {
                at = nums[at];
            }
            mals[what] = at==0? 41 : at;
        } else {
            mals[what] += how*2;
        }
        if(mals[what]==21) return 22;
        else if(mals[what]==23) return 24;
        else if(mals[what]==31) return 28;
        else if(mals[what]==33) return 26;
        else if(mals[what]==37) return 30;
        else if(mals[what]==15) return 16;
        if(mals[what]<=40) return mals[what];
        else return 0;
    }

    static boolean fun3(int what) {
        boolean pass = true;
        if(mals[what]==0 || mals[what]>40) return pass;
        if(what!=0 && mals[0]==mals[what]) pass = false;
        if(what!=1 && mals[1]==mals[what]) pass = false;
        if(what!=2 && mals[2]==mals[what]) pass = false;
        if(what!=3 && mals[3]==mals[what]) pass = false;
        return pass;
    }


}