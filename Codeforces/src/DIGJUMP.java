import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DIGJUMP {

    static int minimunInclude(int dis[], boolean[] spt){
        int min = Integer.MAX_VALUE;
        int min_index = -1;
        for(int i = 0;i<spt.length;i++){
            if(!spt[i] && min >= dis[i]){
                min = dis[i];
                min_index = i;
            }
        }
        return min_index;
    }

    static int dijkstra(ArrayList<Integer>[] graph, int src, int dest,ArrayList<Integer>[] mappings, String s){
        int dist[] = new int[graph.length];
        boolean spt[] = new boolean[graph.length];
        for(int i = 0; i < dist.length;i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;

        for(int count = 0; count < graph.length-1; count++){
            int u = minimunInclude(dist,spt);

            spt[u] = true;

            if(graph[u] != null)
            for(Integer integer : graph[u]){
                if(!spt[integer] && dist[u] != Integer.MAX_VALUE && dist[integer]>dist[u]+1){
                    dist[integer] = dist[u] + 1;
                }
            }
            for(Integer integer : mappings[Integer.valueOf(s.charAt(u)+"")]){
                if(!spt[integer] && dist[u] != Integer.MAX_VALUE && dist[integer]>dist[u]+1){
                    dist[integer] = dist[u] + 1;
                }
            }
        }

        return dist[dest];
    }

    static int bfs(ArrayList<Integer>[] graph, int src,ArrayList<Integer>[] mappings,String s){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        int[] level = new int[graph.length];
        queue.add(src);
        visited[src] = true;
        level[src] = 0;
        while (!queue.isEmpty()){
            int u = queue.remove();
            if(graph[u] != null)
            for(Integer integer : graph[u]){
                if(!visited[integer]){
                    level[integer] = level[u] + 1;
                    visited[integer] = true;
                    queue.add(integer);
                }
                if(integer == graph.length -1){
                    return level[integer];
                }
            }

            for(Integer integer : mappings[Integer.valueOf(s.charAt(u)+"")]){
                if(!visited[integer]){
                    level[integer] = level[u] + 1;
                    visited[integer] = true;
                    queue.add(integer);
                }
                if(integer == graph.length -1){
                    return level[integer];
                }
            }
        }
        return level[graph.length-1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        ArrayList<Integer>[] graph = new ArrayList[s.length()];
        ArrayList<Integer>[] mappings = new ArrayList[10];

        for(int i = 0; i < s.length() ;i ++){
            if(graph[i] == null){
                graph[i] = new ArrayList<>();
            }
            if(i-1 >= 0){
                if(graph[i-1] == null){
                    graph[i-1] = new ArrayList<>();
                }
//                graph[i].add(i-1);
                graph[i-1].add(i);
            }
//            if(mappings[Integer.valueOf(s.charAt(i)+"")] != null){
//                for(Integer integer:mappings[Integer.valueOf(s.charAt(i)+"")]){
////                    graph[i].add(integer);
//                    graph[integer].add(i);
//                }
//            }
            if(mappings[Integer.valueOf(s.charAt(i)+"")] == null){
                mappings[Integer.valueOf(s.charAt(i)+"")] = new ArrayList<>();
            }
            mappings[Integer.valueOf(s.charAt(i)+"")].add(i);
        }
        System.out.println(bfs(graph,0,mappings,s));
    }
}
