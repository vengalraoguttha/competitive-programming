package heaps;

import java.io.IOException;
import java.util.PriorityQueue;

public class TSECJ05 {
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int k = sc.nextInt();
            int n = sc.nextInt();
            PriorityQueue<Long> ascPq = new PriorityQueue<>(new AscendingLongOrder());

            for(int i =0; i < k; i++) {
                ascPq.add(sc.nextLong());
                if(i != k-1)
                    sb.append("-1 ");
                else
                    sb.append(ascPq.peek()).append(" ");
            }

            for(int i = k; i <n; i++){
                ascPq.add(sc.nextLong());
                ascPq.poll();
                sb.append(ascPq.peek()).append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
