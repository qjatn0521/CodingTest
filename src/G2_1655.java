import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class G2_1655 {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            PriorityQueue<Integer> priorityQueue1 = new PriorityQueue<>((o1, o2) -> o2 - o1);
            PriorityQueue<Integer> priorityQueue2 = new PriorityQueue<>((o1, o2) -> o1 - o2);
            int n = Integer.parseInt(bf.readLine());
            for(int i=0;i<n;i++) {
                int num = Integer.parseInt(bf.readLine());

                if(priorityQueue1.size()==priorityQueue2.size()) priorityQueue1.offer(num);
                else priorityQueue2.offer(num);

                if(!priorityQueue1.isEmpty()&&!priorityQueue2.isEmpty()) {
                    if(priorityQueue1.peek()>priorityQueue2.peek()) {
                        priorityQueue2.offer(priorityQueue1.poll());
                        priorityQueue1.offer(priorityQueue2.poll());
                    }
                }
                bw.write(priorityQueue1.peek() +"\n");
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 8 3 2 2 1 {} 5 7 8 9
}