import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// 14:32 ~ 15:12
public class S4_28279 {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String tmp[] = bf.readLine().split(" ");
            int n =Integer.parseInt(tmp[0]);
            Deque<Integer> deque = new ArrayDeque<>();

            for(int i=0;i<n;i++) {
                tmp  = bf.readLine().split(" ");
                int num = Integer.parseInt(tmp[0]);
                //bw.write("num : "+num+"\n");
                if(num==1) {
                    deque.addFirst(Integer.parseInt(tmp[1]));
                } else if(num==2) {
                    deque.addLast(Integer.parseInt(tmp[1]));
                } else if(num==3) {
                    if(!deque.isEmpty()) bw.write(deque.pollFirst()+"\n");
                    else bw.write(-1+"\n");
                } else if(num==4) {
                    if(!deque.isEmpty()) bw.write(deque.pollLast()+"\n");
                    else bw.write(-1+"\n");
                } else if(num==5){
                    bw.write(deque.size()+"\n");
                } else if(num==6){
                    if(!deque.isEmpty())bw.write(0+"\n");
                    else bw.write(1+"\n");
                } else if(num==7){
                    if(!deque.isEmpty())bw.write(deque.peekFirst()+"\n");
                    else bw.write(-1+"\n");
                } else if(num==8){
                    if(!deque.isEmpty())bw.write(deque.peekLast()+"\n");
                    else bw.write(-1+"\n");
                }
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}