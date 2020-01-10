import java.util.ArrayList;
import java.util.Scanner;

public class GCPC11J {
    static int findHeight(ArrayList<Integer>[] graph, int root, boolean[] visited){
        if(visited[root]){
            return 0;
        }
        visited[root] = true;
        if(graph[root] == null){
            return 0;
        }
        if(graph[root].size() == 0){
            return 0;
        }
        if(graph[root].size() == 1){
            if(visited[graph[root].get(0)]){
                return 0;
            }
            return findHeight(graph,graph[root].get(0),visited)+1;
        }
        return max(findHeight(graph,graph[root].get(0),visited),findHeight(graph,graph[root].get(1),visited))+1;
    }

    static int max(int a, int b){
        if(a > b)
            return a;
        return b;
    }

    static int min(int a, int b){
        if( a < b)
            return a;
        return b;
    }

    static long max(long a, long b){
        if(a > b)
            return a;
        return b;
    }

    static int findMinHeight(ArrayList<Integer>[] graph, int n){
        int minHeight = -1;
        for(int i = 0; i < n;i++){
            boolean visited[] = new boolean[n];
            if(minHeight == -1){
                minHeight = findHeight(graph,i,visited);
            }else{
                minHeight = min(minHeight,findHeight(graph,i,visited));
            }
        }
        return minHeight;
    }

    static long findDiameter(ArrayList<Integer>[] graph, int root, boolean[] vis,IntHolder h){
        if(graph[root] == null){
            h.setValue(Long.valueOf("0"));
            return 0;
        }
        if(vis[root]){
            h.setValue(Long.valueOf("0"));
            return 0;
        }
        vis[root] = true;
        if(graph[root].size() == 0){
            h.setValue(Long.valueOf("1"));
            return 1;
        }
        IntHolder lh = new IntHolder(Long.valueOf("0"));
        IntHolder rh = new IntHolder(Long.valueOf("0"));
        if(graph[root].size() == 1){
            long x = findDiameter(graph,graph[root].get(0),vis,lh);
            h.setValue(lh.value+1);
            return max(x,h.value);
        }
        long x = findDiameter(graph,graph[root].get(0),vis,lh);
        long y = findDiameter(graph,graph[root].get(1),vis,rh);
        long max = max(lh.value,rh.value);
        long maxDia = max(x,y);
        for(int i = 2; i<graph[root].size();i++){
            IntHolder tmp = new IntHolder(Long.valueOf("0"));
            long z = findDiameter(graph,graph[root].get(i),vis,tmp);
            if(max < tmp.value){
                max = tmp.value;
            }
            if( maxDia < z){
                maxDia = z;
            }
            if(tmp.value > lh.value && tmp.value < rh.value){
                lh.setValue(tmp.value);
            }
            if(tmp.value < lh.value && tmp.value > rh.value){
                rh.setValue(tmp.value);
            }
            if(tmp.value > lh.value && tmp.value > rh.value){
                lh.setValue(max(lh.value,rh.value));
                rh.setValue(tmp.value);
            }
        }
        h.setValue(max+1);
        return max(maxDia,lh.value+rh.value+1);
    }

    static int max(int a, int b, int c){
        if(a >= b && a >= c){
            return a;
        }else if(b >= a && b >=c){
            return b;
        }else {
            return c;
        }
    }

    static long max(long a, long b, long c){
        if(a >= b && a >= c){
            return a;
        }else if(b >= a && b >=c){
            return b;
        }else {
            return c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int x = 0 ; x < t; x++){
            int n = sc.nextInt();
            ArrayList<Integer> graph[] = new ArrayList[n];
            for(int i = 0; i < n-1; i++){
                int a = sc.nextInt();
                int b = sc.nextInt();
                if(graph[a] == null)
                    graph[a] = new ArrayList<>();
                if(graph[b] ==  null)
                    graph[b] = new ArrayList<>();
                graph[a].add(b);
                graph[b].add(a);
            }
            boolean[] vis = new boolean[n];
            IntHolder h = new IntHolder(Long.valueOf("0"));
            System.out.println((int)(Math.ceil(findDiameter(graph,0,vis,h)/2)));
        }
    }
}
class IntHolder
{
    public Long value;

    IntHolder(Long value) {
        this.value = value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}