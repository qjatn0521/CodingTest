import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 17:20
public class G2_19237 {
    static int ans = 1001;
    static shark[][] arr;
    static shark[] sharks;
    static int[] dirY = {-1,1,0,0};
    static int[] dirX = {0,0,-1,1};
    static shark[][] dusts;
    static shark[][] tmpDusts;
    static int dustAt = 0;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            arr = new shark[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[0])];

            sharks = new shark[Integer.parseInt(tmp[1])+1];
            dusts = new shark[Integer.parseInt(tmp[2])][sharks.length-1];
            for(int i=0;i<arr.length;i++) {
                tmp = bf.readLine().split(" ");
                for(int j=0;j<arr.length;j++) {
                    int num = Integer.parseInt(tmp[j]);
                    if(num != 0) {
                        sharks[num] = new shark(i,j,num,false);
                        arr[i][j] = sharks[num];
                    }
                }
            }
            tmp = bf.readLine().split(" ");
            for(int i=1;i<sharks.length;i++) {
                sharks[i].dir = Integer.parseInt(tmp[i-1])-1;
            }
            for(int i=1;i<sharks.length;i++) {
                int[][] tmpArr = sharks[i].firstdir;
                for(int j=0;j<4;j++) {
                    tmp = bf.readLine().split(" ");
                    for(int k=0;k<4;k++) {
                        tmpArr[j][k] = Integer.parseInt(tmp[k])-1;
                    }
                }
            }
            fun(0);

            if(ans==1001) ans = -1;
            bw.write("" + ans);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void fun(int time) {
        tmpDusts = new shark[arr.length][arr.length];
        int num = 0;
        for(int i=1;i<sharks.length;i++) {
            if(sharks[i]!=null) num++;
        }
        if(num==1||time>1000)  {
            ans = time>ans?ans:time;
            return;
        }
        dusts[dustAt] = new shark[num];
        moveSharks();
        setDust(num);
        dustAt++;
        if(dustAt==dusts.length) dustAt=0;
/*
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr.length;j++) {
                if(arr[i][j]!=null) {
                    String a = arr[i][j].dust?" ":"S";
                    System.out.print(arr[i][j].num+"("+a+"), ");
                }
                else {
                    System.out.print("    , ");
                }
            }
            System.out.println();
        }
        System.out.println("=============================");*/
        fun(time+1);

    }

    private static void setDust(int num) {
        int preDustAt = dustAt==dusts.length-1?0:dustAt+1;
        if(dusts[preDustAt]!=null) {
            for(int i=0;i<dusts[preDustAt].length;i++) {
                if(dusts[preDustAt][i]!=null&&arr[dusts[preDustAt][i].y][dusts[preDustAt][i].x]!=null&&arr[dusts[preDustAt][i].y][dusts[preDustAt][i].x]==dusts[preDustAt][i])
                    arr[dusts[preDustAt][i].y][dusts[preDustAt][i].x] = null;
            }
        }

    }

    private static void moveSharks() {
        for(int i=1;i<sharks.length;i++) {
            if(sharks[i]!=null) {
                shark s = sharks[i];
                int sharkY = s.y + dirY[s.firstdir[s.dir][0]];
                int sharkX = s.x + dirX[s.firstdir[s.dir][0]];
                int n= 0;
                while(true) {
                    if(sharkX>=0&&sharkY>=0&&sharkY<arr.length&&sharkX<arr.length&&(arr[sharkY][sharkX]==null||(arr[sharkY][sharkX]!=null&&(!arr[sharkY][sharkX].dust&&tmpDusts[sharkY][sharkX]==null&&arr[sharkY][sharkX].num<s.num)))) {
                        break;
                    }
                    if(n++==3) break;
                    sharkY = s.y + dirY[s.firstdir[s.dir][n]];
                    sharkX = s.x + dirX[s.firstdir[s.dir][n]];
                    //System.out.println(s.num+" shark : "+n+", y : "+sharkY+", x : "+sharkX+", dir :"+s.firstdir[s.dir][n]);
                }
                if(n==4) {
                    //System.out.println(s.num);
                    n= 0;
                    sharkY = s.y + dirY[s.firstdir[s.dir][0]];
                    sharkX = s.x + dirX[s.firstdir[s.dir][0]];
                    while(true) {
                        if(sharkX>=0&&sharkY>=0&&sharkY<arr.length&&sharkX<arr.length&&arr[sharkY][sharkX]!=null&&arr[sharkY][sharkX].dust&&arr[sharkY][sharkX].num==s.num) {
                            break;
                        }
                        n++;
                        sharkY = s.y + dirY[s.firstdir[s.dir][n]];
                        sharkX = s.x + dirX[s.firstdir[s.dir][n]];
                    }
                }
                s.dir = s.firstdir[s.dir][n];
                arr[s.y][s.x] = new shark(s.y,s.x,s.num,true);
                addDust(arr[s.y][s.x]);
                s.y = sharkY; s.x = sharkX;
                if(arr[sharkY][sharkX]!=null&&!arr[sharkY][sharkX].dust) {
                    shark other = arr[sharkY][sharkX];
                    if(other.num>s.num) {
                        sharks[other.num]=null;
                        arr[sharkY][sharkX] = s;
                    } else {
                        sharks[s.num]=null;
                    }
                } else if(arr[sharkY][sharkX]!=null&&arr[sharkY][sharkX].dust) {
                    //System.out.println("Dust!");
                    tmpDusts[sharkY][sharkX] = arr[sharkY][sharkX];
                    arr[sharkY][sharkX] = s;
                }
                else {
                    arr[sharkY][sharkX] = s;
                }
            }
        }
    }

    private static void addDust(shark dust) {
        int n =0;
        while(dusts[dustAt][n]!=null) {
            n++;
        }
        dusts[dustAt][n] = dust;
    }


    static class shark{
        int num;
        int dir;
        int y;
        int x;
        boolean dust = false;
        int[][]  firstdir = new int[4][4];
        void nextDir() {
            dir++;
            if(dir==4) dir=0;
        }
        void move(int y, int x) {
            this.y =y;
            this.x =x;
        }
        shark(int y, int x,int num,boolean dust) {
            this.y = y;
            this.x = x;
            this.num = num;
            this.dust =dust;
        }
        void setFish(int num, int dir,int y, int x) {
            this.num = num;
            this.dir = dir;
            this.y = y;
            this.x = x;
        }
    }
}
/*
5 6 7
0 0 0 0 3
0 2 0 0 0
1 0 0 0 4
0 6 0 0 0
0 0 5 0 0
4 4 3 1 4 2
2 3 1 4
4 1 2 3
3 4 2 1
4 3 1 2
2 4 3 1
2 1 3 4
3 4 1 2
4 1 2 3
4 3 2 1
1 4 3 2
1 3 2 4
3 2 1 4
3 4 1 2
3 2 4 1
1 4 2 3
1 4 2 3
3 4 1 2
3 2 4 1
1 4 2 3
1 4 2 3
3 4 1 2
4 1 2 3
4 3 2 1
1 4 3 2
 */