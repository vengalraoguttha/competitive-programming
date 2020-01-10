import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CHGORAM {
    private static int bfs3Steps(ArrayList<Integer>[] graph, int vertex ,int p1, int p2, int p3){
        Queue<Integer> q = new LinkedList<>();
        q.addAll(graph[vertex]);
        int count = 0;
        while (!q.isEmpty()){
            int head = q.remove();
            ArrayList<Integer> list = graph[head];
            for(int i =0; i < list.size(); i++){
                int x = list.get(i);
                count += compare(p1,p2,p3,vertex,head,x);
            }
        }
        return count;
    }

    private static int compare(int p1, int p2, int p3, int vertex, int head, int x){
        int change = 0;
        if(p1 < p2 && vertex < head){
            change++;
        }
        if(p1 > p2 && vertex > head){
            change++;
        }
        if(p1 < p3 && vertex < x){
            change++;
        }
        if(p1 > p3 && vertex > x){
            change++;
        }
        if(p2 < p3 && head < x){
            change++;
        }
        if(p2 > p3 && head > x){
            change++;
        }
        if(change == 3){
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int x = 0; x < t; x++){
            int n = sc.nextInt();
            int p1 = sc.nextInt();
            int p2 = sc.nextInt();
            int p3 = sc.nextInt();
            ArrayList<Integer>[] graph = new ArrayList[n];
            for(int i =0 ; i < n-1 ;i++){
                int u = sc.nextInt();
                int v = sc.nextInt();
                if(graph[u-1] == null){
                    graph[u-1] = new ArrayList();
                }
                if(graph[v-1] == null){
                    graph[v-1] = new ArrayList();
                }
                graph[u-1].add(v-1);
                graph[v-1].add(u-1);
            }
            int c = 0;
            for(int i =0 ; i < n ;i ++){
                c += bfs3Steps(graph,i,p1,p2,p3);
            }
            System.out.println(c);
        }
    }
}
