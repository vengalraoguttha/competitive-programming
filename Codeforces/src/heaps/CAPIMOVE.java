package heaps;

import java.io.IOException;
import java.util.*;

public class CAPIMOVE {
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            long[] p = new long[n];
            PriorityQueue<CAPIMOVEOBJ> descPq = new PriorityQueue<>(new CAPIMOVEOrder());
            for(int i = 0; i < n; i++){
                p[i] = sc.nextLong();
                descPq.add(new CAPIMOVEOBJ(p[i],i));
            }
            ArrayList<Long>[] graph = new ArrayList[n];
            for(int i = 0; i < n-1; i++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                if(graph[x-1] == null)
                    graph[x-1] = new ArrayList<>();
                if(graph[y-1] == null)
                    graph[y-1] = new ArrayList<>();
                graph[x-1].add(p[y-1]);
                graph[x-1].add(p[x-1]);
                graph[y-1].add(p[x-1]);
                graph[y-1].add(p[y-1]);
            }

            List<CAPIMOVEOBJ> tmp = new ArrayList<>();
            for(int i = 0; i < n; i++){
                while (descPq.size() != 0 && graph[i].contains(descPq.peek().val)){
                    tmp.add(descPq.poll());
                }

                if(descPq.size() == 0){
                    sb.append("0 ");
                }else{
                    sb.append(descPq.peek().pos+1).append(" ");
                }

                descPq.addAll(tmp);
                tmp.clear();
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
class CAPIMOVEOBJ{
    Long val;
    int pos;
    CAPIMOVEOBJ(Long val, int pos){
        this.val = val;
        this.pos = pos;
    }
}

class CAPIMOVEOrder implements Comparator<CAPIMOVEOBJ> {

    @Override
    public int compare(CAPIMOVEOBJ o1, CAPIMOVEOBJ o2) {
        if(o2.val-o1.val>0)
            return 1;
        else if(o2.val-o1.val==0)
            return 0;
        return -1;
    }
}