import java.io.*;

public class S1_1629 {

    static int[] dp = new int[70];
    public static void main(String[] args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] inputs = bf.readLine().split(" ");

            int A = Integer.parseInt(inputs[0]);
            int B = Integer.parseInt(inputs[1]);
            int C = Integer.parseInt(inputs[2]);

            module(A,B,C,0);

            bw.write(""+dp[0]);

            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void module(int A, int B, int C,int depth) {
        if(B==1) {
            dp[depth] = A%C;
        }
        else {
            module(A,B/2,C,depth+1);
            long tmp = dp[depth+1];
            long value = (tmp*tmp)%C;
            if(B%2==1) {
                value = (value*(A%C))%C;
            }
            dp[depth] = (int) value;
        }
    }
}