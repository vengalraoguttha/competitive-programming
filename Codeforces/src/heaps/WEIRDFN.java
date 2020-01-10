package heaps;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class WEIRDFN {
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int t = sc.nextInt();
        long mod = 1000000007;
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            long a = sc.nextLong();
            long b = sc.nextLong();
            long c = sc.nextLong();
            int n = sc.nextInt();

            PriorityQueue<Long> ascPq = new PriorityQueue<>(new AscendingLongOrder());
            PriorityQueue<Long> descPq = new PriorityQueue<>(new DescendingLongOrder());

            descPq.add((long)1);

            long sum = 1;
            for(int i = 2; i < n+1; i++){
                long x = ((a*descPq.peek())%mod + (b*i)%mod + c%mod)%mod;
                sum += x;
                sum %= mod;
                descPq.add(x);
                ascPq.add(descPq.poll());
                descPq.add(ascPq.poll());
                if(i%2 == 0)
                ascPq.add(descPq.poll());
            }

            sb.append(sum).append("\n");
        }
        System.out.println(sb.toString());
    }
}
