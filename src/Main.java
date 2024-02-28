import java.io.*;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 백준 14503 1시도 통과
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] tmp = bf.readLine().split(" ");
            int[][] arr = new int[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[0])];
            int width = Integer.parseInt(tmp[1]);

            for(int i=0;i<arr.length;i++) {
                tmp = bf.readLine().split(" ");
                for(int j=0;j<arr.length;j++) {
                    arr[i][j] = Integer.parseInt(tmp[j]);
                }
            }
            int ans = 0;

            for(int i=0;i<arr.length;i++) {
                int l = 1;
                for(int j=1;j<arr.length;j++) {
                    if(arr[i][j-1]==arr[i][j]) {
                        l++;
                    } else if(arr[i][j-1]+1==arr[i][j]) {
                        if(l>=width) {
                            l=1;
                        } else {
                            l=-1;
                            j = 101;
                        }
                    } else if(arr[i][j-1]-1==arr[i][j]) {
                        l = 1;

                        while(l<width) {
                            j++;
                            if(j>=arr.length||arr[i][j-1]!=arr[i][j]) {
                                break;
                            }
                            l++;
                        }
                        if(l!=width) {
                            l = -1;
                            j= 101;
                        } else {
                            l = 0;
                        }
                    } else {
                        l = -1;
                        j = 101;
                    }
                }
                if(l>=0) {
                    ans++;
                }

                l = 1;
                for(int j=1;j<arr.length;j++) {
                    if(arr[j-1][i]==arr[j][i]) {
                        l++;
                    } else if(arr[j-1][i]+1==arr[j][i]) {
                        if(l>=width) {
                            l=1;
                        } else {
                            l=-1;
                            j = 101;
                        }
                    } else if(arr[j-1][i]-1==arr[j][i]) {
                        l = 1;

                        while(l<width) {
                            j++;
                            if(j>=arr.length||arr[j-1][i]!=arr[j][i]) {
                                break;
                            }
                            l++;
                        }
                        if(l!=width) {
                            l = -1;
                            j= 101;
                        } else {
                            l = 0;
                        }
                    } else {
                        l = -1;
                        j = 101;
                    }
                }
                if(l>=0) {
                    ans++;
                }
            }

            bw.write(""+ans);
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}