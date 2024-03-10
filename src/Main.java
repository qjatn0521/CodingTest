import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 14:00 ~ 14:47
public class Main {
    static int[][] arr;
    static int y;
    static int x;
    static int value;
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            arr = new int[3][3];
            y = Integer.parseInt(tmp[0])-1;
            x = Integer.parseInt(tmp[1])-1;
            value = Integer.parseInt(tmp[2]);

            for(int i=0;i<3;i++) {
                tmp = bf.readLine().split(" ");
                for(int j=0;j<3;j++)
                    arr[i][j] = Integer.parseInt(tmp[j]);
            }


            int ans = fun1(0);

            bw.write(""+ans);
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    static int fun1(int time) {
        //System.out.println("time : "+time);
        for(int i=0;i< arr.length;i++) {
            //System.out.println(Arrays.toString(arr[i]));
        }
        if(y< arr.length && x < arr[0].length&&arr[y][x]==value) return time;
        else if(time>=100) return -1;
        int max = 0;
        if(arr.length>=arr[0].length) {
            int[][] tmp = new int[arr.length][101];
            ArrayList<data>[] tmpA = new ArrayList[arr.length];

            for(int i=0;i<arr.length;i++) {
                for(int j=0;j<arr[0].length;j++) {
                    tmp[i][arr[i][j]]++;
                }
            }
            for(int i=0;i<arr.length;i++) {
                int tmpMax = 0;
                tmpA[i] = new ArrayList<data>();
                for(int j=1;j<101;j++) {
                    if(tmp[i][j]>0) {
                        //System.out.println(tmp[i][j]);
                        tmpA[i].add(new data(j,tmp[i][j]));
                        tmpMax++;
                    }
                }
                max = max>tmpMax? max : tmpMax;
                Collections.sort(tmpA[i], new Comparator<data>() {
                    @Override
                    public int compare(data d1, data d2) {
                        if (d1.how != d2.how) {
                            return d1.how - d2.how; // how 가 작은 순으로 정렬
                        } else {
                            return d1.num - d2.num; // 같은 경우 num 이 작은 순으로 정렬
                        }
                    }
                });
            }
            arr = new int[arr.length>=100 ? 100: arr.length][max*2>=100 ? 100 : max*2];

            for(int i=0;i<arr.length;i++) {
                for(int j=0;j<tmpA[i].size()&&j<50;j++) {
                    data tmpData = tmpA[i].get(j);
                    arr[i][2*j] = tmpData.num;
                    arr[i][2*j+1] = tmpData.how;
                }
            }
        } else {
            int[][] tmp = new int[arr[0].length][101];
            ArrayList<data>[] tmpA = new ArrayList[arr[0].length];

            for(int i=0;i<arr[0].length;i++) {
                for(int j=0;j<arr.length;j++) {
                    tmp[i][arr[j][i]]++;
                }
            }
            for(int i=0;i<arr[0].length;i++) {
                int tmpMax = 0;
                tmpA[i] = new ArrayList<data>();
                for(int j=1;j<101;j++) {
                    if(tmp[i][j]>0) {
                        tmpA[i].add(new data(j,tmp[i][j]));
                        tmpMax++;
                    }
                }
                max = max>tmpMax? max : tmpMax;
                Collections.sort(tmpA[i], new Comparator<data>() {
                    @Override
                    public int compare(data d1, data d2) {
                        if (d1.how != d2.how) {
                            return d1.how - d2.how; // how 가 작은 순으로 정렬
                        } else {
                            return d1.num - d2.num; // 같은 경우 num 이 작은 순으로 정렬
                        }
                    }
                });
            }
            arr = new int[max*2>=100?100:max*2][arr[0].length>=100?100:arr[0].length];
            for(int i=0;i<arr[0].length;i++) {
                for(int j=0;j<tmpA[i].size()&&j<50;j++) {
                    data tmpData = tmpA[i].get(j);
                    arr[2*j][i] = tmpData.num;
                    arr[2*j+1][i] = tmpData.how;
                }
            }
        }
        return fun1(time+1);
    }
    static class data {
        int num;
        int how;
        data(int num, int how){
            this.how = how;
            this.num = num;
        }
    }

}
/*

 */