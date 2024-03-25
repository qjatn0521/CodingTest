import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 16:04 ~
public class G4_20056 {
    static int[] moveY ={-1,-1,0,1,1,1,0,-1};
    static int[] moveX ={0,1,1,1,0,-1,-1,-1};

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            int n = Integer.parseInt(tmp[0]);
            int fbNum = Integer.parseInt(tmp[1]);
            int moveNum = Integer.parseInt(tmp[2]);

            Queue<fireball> q = new LinkedList<>();
            for(int i=0;i<fbNum;i++) {
                tmp = bf.readLine().split(" ");
                fireball f = new fireball(Integer.parseInt(tmp[0])-1,Integer.parseInt(tmp[1])-1,Integer.parseInt(tmp[2]),Integer.parseInt(tmp[3]),Integer.parseInt(tmp[4]));
                q.add(f);
            }
            int sum = 0;
            for(int i=0;i<moveNum;i++) {
                fireball[][] arr = new fireball[n][n];
                int tmpFBNum = q.size();
                for(int j=0;j<tmpFBNum;j++) {
                    fireball f = q.poll();
                    int tmpY = (f.y+moveY[f.d]*f.s)%n;
                    int tmpX = (f.x+moveX[f.d]*f.s)%n;
                    if(tmpY<0)tmpY+=n;
                    if(tmpX<0)tmpX+=n;
                    //System.out.println("fireball : "+f.y+", "+f.x+"("+tmpY+", "+tmpX+")"+",  m: "+f.m+", d :" +f.d+", s : "+f.s);
                    if(arr[tmpY][tmpX]==null) {
                        arr[tmpY][tmpX] = f;
                        f.y = tmpY;
                        f.x = tmpX;
                        q.add(f);
                    } else {
                        arr[tmpY][tmpX].m += f.m;
                        arr[tmpY][tmpX].s += f.s;
                        arr[tmpY][tmpX].n++;
                        if(arr[tmpY][tmpX].d%2 != f.d%2) arr[tmpY][tmpX].all =1;
                    }
                }
                //System.out.println();
                for(int j=0;j<n;j++) {
                    for(int k=0;k<n;k++) {
                        if(arr[j][k]!=null&&arr[j][k].n>1) {
                            int[] ds;
                            if(arr[j][k].all==1) ds = new int[]{1, 3, 5, 7};
                            else ds = new int[]{0, 2, 4, 6};

                            int m = arr[j][k].m/5;
                            int s = arr[j][k].s/arr[j][k].n;
                            if(m!=0) {
                                for(int d : ds) {
                                    fireball f = new fireball(j,k,m,s,d);
                                    q.add(f);
                                }
                            }
                            q.remove(arr[j][k]);
                        }
                    }
                }
                //System.out.println("size : "+q.size());
            }
            while(!q.isEmpty()) {
                fireball f = q.poll();
                sum+= f.m;
                //System.out.println("m : "+f.m);
            }
            bw.write(""+sum);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void fun() {
    }

    static class fireball {
        int y;
        int x;
        int m;
        int s;
        int d;
        int n = 1;
        int all=0;
        fireball(int y,int x,int m,int s,int d) {
            this.y= y;
            this.x= x;
            this.m =m;
            this.s = s;
            this.d = d;
        }
    }

}