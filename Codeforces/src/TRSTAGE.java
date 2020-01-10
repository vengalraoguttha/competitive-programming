import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TRSTAGE {
    static double[][] cost;
    static int[][] graph;
    static int[] hourseCount;
    static List<Edge> edges;
//    static void calculate(int mask, int n,int e){
//        if(e >= edges.size() || mask == (1<<n)-1)
//            return;
//        int u = edges.get(e).start;
//        int v = edges.get(e).end;
//        for(int i =0; i < n ; i++){
//            if((mask&(1<<i))==0){
//                if(cost[u][mask] != Integer.MAX_VALUE && (cost[u][mask] + (1.0*graph[u][v]/hourseCount[i]) < cost[v][mask|(1<<i)])){
//                    cost[v][mask|(1<<i)] = cost[u][mask] + (1.0*graph[u][v]/hourseCount[i]);
//                    calculate((mask|(1<<i)),n,e+1);
//                }else{
//                    calculate(mask,n,e+1);
//                }
//            }
//        }
//    }
    static double modifiedDijkstra(int[][] graph, int source, int destination,List<Edge> edges,int[] hourseCount){
        PriorityQueue<TrNode> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new TrNode(source,0,0));
        boolean [][] visited = new boolean [1 << hourseCount.length][graph.length];
        double min = -1;
        while (!priorityQueue.isEmpty()){
            TrNode node = priorityQueue.remove();
            if (visited [node.mask][node.id]) continue;
            visited [node.mask][node.id] = true;
            if (node.id == destination) {min = node.dist; break;}
            for (int i = 0; i < graph.length; i++) {
                if (graph [node.id][i] == 0) continue;
                for (int j = 0; j < hourseCount.length; j++) {
                    if ((node.mask & (1 << j)) > 0) continue;
                    priorityQueue.add (new TrNode (i,node.mask | (1 << j) ,node.dist + ((double)graph [node.id][i] / hourseCount [j])));
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int p = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            if( n == 0 && m == 0 && p == 0 && a == 0 && b == 0){
                break;
            }
            hourseCount = new int[n];
            for(int i = 0; i < n; i++){
                hourseCount[i] = sc.nextInt();
            }
            graph = new int[m][m];
            edges = new ArrayList<>();
            cost = new double[m][1<<n];
            for(int i = 0; i < p ; i++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                int z = sc.nextInt();

                Edge edge = new Edge();
                edge.start = x-1;
                edge.end = y-1;
                edge.weight = z;
                edges.add(edge);
                edge = new Edge();
                edge.start = y-1;
                edge.end = x-1;
                edge.weight = z;
                edges.add(edge);

                graph[x-1][y-1] = z;
                graph[y-1][x-1] = z;
            }

//            for(int k = 0; k < m-1; k++)
//            for(int i =0; i < n ; i++){
//                calculate((1<<i),n,1);
//            }
//            System.out.println(cost.length);;
            double ans = modifiedDijkstra(graph,a-1,b-1,edges,hourseCount);
            System.out.println(ans == -1 ? "Impossible" : ans);
        }
    }
}
class TrNode implements Comparable<TrNode>{
    int id,mask;
    public double dist;
    TrNode(int id, int mask, double dist){
        this.id = id;
        this.dist = dist;
        this.mask = mask;
    }

    public int compareTo (TrNode other) {
        if (dist > other.dist) return 1;
        if (dist < other.dist) return -1;
        return 0;
    }
}
class Edge{
    int start,end,weight;
}
