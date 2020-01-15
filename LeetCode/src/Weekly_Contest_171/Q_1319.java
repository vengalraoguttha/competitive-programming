package Weekly_Contest_171;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q_1319 {
    static class Model{
        int can_be_used_cables;
    }

    private static Model dfs(List<Integer>[] graph, int parent, boolean[] visited, int current){
        visited[current] = true;
        int count = 0;
        if(graph[current] != null)
            for(Integer next : graph[current]){
                if(!visited[next]){
                    Model m = dfs(graph,current,visited,next);
                    count += m.can_be_used_cables;
                }else if(next != parent && parent != -1){
                    count++;
                }
            }
        Model model = new Model();
        model.can_be_used_cables = count;

        return model;
    }

    public static int makeConnected(int n, int[][] connections) {
        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < connections.length; i++){
            if(graph[connections[i][0]] == null){
                graph[connections[i][0]] = new ArrayList<>();
            }
            if(graph[connections[i][1]] == null){
                graph[connections[i][1]] = new ArrayList<>();
            }

            graph[connections[i][0]].add(connections[i][1]);
            graph[connections[i][1]].add(connections[i][0]);
        }

        boolean[] visited = new boolean[n];

        int components = 0;
        int cables = 0;
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                components++;
                cables += dfs(graph,-1,visited,i).can_be_used_cables;
            }
        }
        if(components > 0)
            components--;
        if(components <= cables){
            return Math.min(cables,components);
        }else{
            return -1;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] a = new int[m][2];
        for(int i = 0; i < m; i++){
            a[i][0] = sc.nextInt();
            a[i][1] = sc.nextInt();
        }
        System.out.println(makeConnected(n,a));
    }
}
