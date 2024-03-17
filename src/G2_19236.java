import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 13:01 ~ 13:52
public class G2_19236 {
    static int[] dirsY = {-1,-1,0,1,1,1,0,-1};
    static int[] dirsX= {0,-1,-1,-1,0,1,1,1};
    static int ans = 0;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp;
            fish[][] fishs = new fish[4][4];
            for(int i=0;i<4;i++) {
                tmp = bf.readLine().split(" ");
                for(int j=0;j<4;j++) {
                    fishs[i][j] = new fish(Integer.parseInt(tmp[j*2]),Integer.parseInt(tmp[j*2+1])-1,i,j);
                }
            }
            int sharDir = fishs[0][0].dir;
            int sum = 0;
            sum+= fishs[0][0].num;
            fishs[0][0] = null;

            fun(sum,fishs,0,0,sharDir);

            bw.write("" + ans);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void fun(int sum, fish[][] fishs, int sharkY, int sharkX, int sharDir) {
        fish[][] movedFishs = moveFishs(fishs,sharkY,sharkX);
       /*for(int k=0;k<4;k++) {
            for(int j=0;j<4;j++) {
                if(movedFishs[k][j]!=null)
                    System.out.print(movedFishs[k][j].num+", ");
                else {
                    System.out.print(" , ");
                }
            }
            System.out.println();
        }*/

        int nextSharkY = sharkY + dirsY[sharDir];
        int nextSharkX = sharkX + dirsX[sharDir];
        int n = 0;
        while(nextSharkY>=0&&nextSharkX>=0&&nextSharkY<4&&nextSharkX<4) {
            if(movedFishs[nextSharkY][nextSharkX]!=null) {
                fish tmp = movedFishs[nextSharkY][nextSharkX];
                movedFishs[nextSharkY][nextSharkX] = null;
                fun(sum+tmp.num,movedFishs,nextSharkY,nextSharkX,tmp.dir);
                movedFishs[nextSharkY][nextSharkX] = tmp;
                n++;
            }
            nextSharkY += dirsY[sharDir];
            nextSharkX += dirsX[sharDir];
        }
        if(n==0) {
            ans = ans>sum?ans:sum;
        }
    }

    private static fish[][] moveFishs(fish[][] fishs,int sharkY, int sharX) {
        fish[] fishArr = new fish[17];
        fish[][] newFishs = new fish[4][4];
        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) {
                fish f = fishs[i][j];
                if(f!=null) {
                    newFishs[i][j] = new fish(f);
                    fishArr[f.num] = newFishs[i][j];
                }
            }
        }
        for(int i=1;i<=16;i++) {
            fish f = fishArr[i];
            if(f!=null) {
                int fishY = f.y + dirsY[f.dir];
                int fishX = f.x + dirsX[f.dir];
                int n = 0;
                while(fishY<0 || fishX<0 || fishY>=4 || fishX>=4 || (sharkY== fishY&&sharX==fishX)) {
                    f.nextDir();
                    fishY = f.y + dirsY[f.dir];
                    fishX = f.x + dirsX[f.dir];
                    n++;
                    if(n==9) break;
                }
                if(n!=9) {
                    if(newFishs[fishY][fishX]==null) {
                        newFishs[f.y][f.x] = null;
                        newFishs[fishY][fishX] = f;
                        f.move(fishY,fishX);
                    } else {
                        newFishs[f.y][f.x] = newFishs[fishY][fishX];
                        newFishs[fishY][fishX].move(f.y,f.x);
                        newFishs[fishY][fishX] = f;
                        f.move(fishY,fishX);
                    }
                }
            }

        }
        return newFishs;
    }

    static class fish{
        int num;
        int dir;
        int y;
        int x;
        void nextDir() {
            dir++;
            if(dir==8) dir=0;
        }
        void move(int y, int x) {
            this.y =y;
            this.x =x;
        }
        fish(int num, int dir,int y, int x) {
            this.num = num;
            this.dir = dir;
            this.y = y;
            this.x = x;
        }
        fish(fish f) {
            this.num = f.num;
            this.dir = f.dir;
            this.y = f.y;
            this.x = f.x;
        }
    }
}