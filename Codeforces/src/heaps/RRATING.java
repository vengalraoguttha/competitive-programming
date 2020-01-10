package heaps;

import java.io.IOException;
import java.util.*;

public class RRATING {
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int n = sc.nextInt();

        PriorityQueue<Long> ascPq = new PriorityQueue<>(new AscendingLongOrder());
        PriorityQueue<Long> descPq = new PriorityQueue<>(new DescendingLongOrder());

        StringBuilder sb = new StringBuilder();
        int count = 0;

        for(int i = 0; i < n; i++){
            int type = sc.nextInt();
            if(type == 1){
                long val = sc.nextLong();
                count++;
                ascPq.add(val);
                if(count%3 == 0){
                    if(ascPq.peek() < descPq.peek()){
                        long tmp = descPq.poll();
                        descPq.add(ascPq.poll());
                        ascPq.add(tmp);
                    }
                }else{
                    descPq.add(ascPq.poll());
                }
            }else{
                if(ascPq.size() == 0){
                    sb.append("No reviews yet").append("\n");
                }else{
                    sb.append(ascPq.peek()).append("\n");
                }
            }
        }

        System.out.println(sb.toString());
    }
}

class AscendingLongOrder implements Comparator<Long>{

    @Override
    public int compare(Long o1, Long o2) {
        if(o2-o1>0)
            return -1;
        else if(o2-o1==0)
            return 0;
        return 1;
    }
}

class DescendingLongOrder implements Comparator<Long>{

    @Override
    public int compare(Long o1, Long o2) {
        if(o2-o1>0)
            return 1;
        else if(o2-o1==0)
            return 0;
        return -1;
    }
}