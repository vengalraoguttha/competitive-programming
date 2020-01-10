import java.util.ArrayList;
import java.util.Scanner;

public class P_2 {

    static long mod = 1000000007;

    static long calculate(ArrayList<Integer>[] graph, boolean[] visited, boolean[] color){
        // pick any vertex if not visited then paint with white or black comparing with its adjacent colored vertices
        if(graph.length == 1)
            return 2;

        int ans = 0;
        for(int i = 0; i < graph.length; i++){
            if(!visited[i]){
                visited[i] = true;
                boolean canBeBlack = true;
                for(Integer ind : graph[i]){
                    if(visited[ind] && color[ind])
                        canBeBlack = false;
                }
                // can always be painted with white
                ans += calculate(graph,visited,color);
                if(canBeBlack){
                    color[i] = true;
                    ans += calculate(graph,visited,color);
                    color[i] = false;
                }
                visited[i] = false;
            }
        }

        return ans > 0 ? ans : 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n-1; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            x--; y--;
            if(graph[x] == null)
                graph[x] = new ArrayList<>();
            if(graph[y] == null)
                graph[y] = new ArrayList<>();
            graph[x].add(y);
            graph[y].add(x);
        }

        boolean[] visited = new boolean[n];
        boolean[] color = new boolean[n];

        System.out.println(calculate(graph,visited,color));
    }
}
