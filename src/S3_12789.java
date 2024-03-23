import java.io.*;
import java.util.Stack;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 14:32 ~ 15:12
public class S3_12789 {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String tmp[] = bf.readLine().split(" ");
            int n =Integer.parseInt(tmp[0]);
            Stack<Integer> stack = new Stack<>();
            tmp  = bf.readLine().split(" ");
            int num = 1;
            for(int i=0;i<n;i++) {
                if(num==Integer.parseInt(tmp[i])) {
                    num++;
                } else {
                    stack.add(Integer.parseInt(tmp[i]));
                }
                while (!stack.empty()&&num==stack.peek()){
                    num++;
                    stack.pop();
                }
            }
            while (!stack.empty()){
                if(num==stack.pop()) {
                    num++;
                }
            }
            if(num==n+1) bw.write("Nice");
            else bw.write("Sad");
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}