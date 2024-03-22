import java.io.*;
import java.util.Stack;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 14:32 ~ 15:12
public class S4_28278 {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String tmp[] = bf.readLine().split(" ");
            int n =Integer.parseInt(tmp[0]);
            Stack<Integer> stack = new Stack<>();

            for(int i=0;i<n;i++) {
                tmp  = bf.readLine().split(" ");
                int num = Integer.parseInt(tmp[0]);
                //bw.write("num : "+num+"\n");
                if(num==1) {
                    stack.push(Integer.parseInt(tmp[1]));
                } else if(num==2) {
                    if(!stack.empty()) bw.write(stack.pop()+"\n");
                    else bw.write(-1+"\n");
                } else if(num==3) {
                    bw.write(stack.size()+"\n");
                }
                else if(num==4) {
                    if(!stack.empty()) bw.write(0+"\n");
                    else bw.write(1+"\n");
                } else if(num==5){
                    if(!stack.empty()) {
                        bw.write(stack.peek()+"\n");
                    } else {
                        bw.write(-1+"\n");
                    }
                }
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}