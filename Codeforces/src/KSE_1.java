import java.util.ArrayList;
import java.util.Scanner;

public class KSE_1 {
    static class Edge{
        int a,b;
    }
    static int find(int parent[], int i)
    {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    static void Union(int parent[], int x, int y)
    {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }

    static int isCycle( ArrayList<Integer>[] graph,ArrayList<Edge> edges)
    {
        int parent[] = new int[graph.length];

        for (int i=0; i<graph.length; i++)
            parent[i]=-1;

        for (int i = 0; i < edges.size(); i++)
        {
            int x = find(parent, edges.get(i).a);
            int y = find(parent, edges.get(i).b);

            if (x == y)
                return 1;

            Union(parent, x, y);
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int id = 1;
        while(t > 0){
            int n = sc.nextInt();
            long m = sc.nextLong();

            ArrayList<Integer>[] graph = new ArrayList[n];
            ArrayList<Edge> edges = new ArrayList<>();

            int count = 0;
            for(long i = 0; i < m ;i++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                Edge e = new Edge();
                e.a = x-1;
                e.b = y-1;
                edges.add(e);
                if(graph[x-1] == null)
                    graph[x-1] = new ArrayList<>();
                if(graph[y-1] == null)
                    graph[y-1] = new ArrayList<>();
                graph[x-1].add(y-1);
                graph[y-1].add(x-1);
                count++;
                int cycle = isCycle(graph,edges);
                if(cycle == 1){
                    count--;
                    graph[x-1].remove(graph[x-1].size()-1);
                    graph[y-1].remove(graph[y-1].size()-1);
                    edges.remove(edges.size()-1);
                }
            }

            System.out.println("Case #"+id+": "+(count + (n-1-count)*2));

            id++;
            t--;
        }
    }
}