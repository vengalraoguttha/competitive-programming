package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BUGLIFE {

    static boolean bfs(ArrayList<Integer>[] graph, int start, int n, boolean[] visited){
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        int color[] = new int[n];
        visited[start] = true;
        queue.add(start);
        if(color[start] == 0){
            color[start] = 1;
        }
        while (!queue.isEmpty()){
            count++;
            int val = queue.remove();
            if(graph[val] != null)
                for(Integer integer : graph[val]){
                    if(!visited[integer]){
                        visited[integer] = true;
                        queue.add(integer);
                        if(color[integer] == 0){
                            color[integer] = getColor(color[val]);
                        }else if(color[integer] == getColor(getColor(color[val]))){
                            return false;
                        }
                    }else{
                        if(color[integer] == getColor(getColor(color[val]))){
                            return false;
                        }
                    }
                }
        }
        return true;
    }

    static int getColor(int color){
        if( color == 1)
            return 2;
        return 1;
    }

    static boolean findConnectedCompos(ArrayList<Integer>[] graph,int n){
        long val = 1;
        boolean visited[] = new boolean[n];
        boolean result = true;
        for(int i =0; i<n;i++){
            if(!visited[i]){
                visited[i] = true;
                if(!(bfs(graph,i,n,visited))){
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int x = 0; x < t; x++){
            int n = sc.nextInt();
            int m = sc.nextInt();
            ArrayList<Integer>[] graph = new ArrayList[n];
            for (int i = 0; i <m;i++){
                int l = sc.nextInt();
                int k = sc.nextInt();
                l--;
                k--;
                if(graph[l] == null)
                    graph[l] = new ArrayList<>();
                if(graph[k] == null)
                    graph[k] = new ArrayList<>();
                graph[l].add(k);
                graph[k].add(l);
            }
            System.out.println("Scenario #"+(x+1)+":");
            if(findConnectedCompos(graph,n)){
                System.out.println("No suspicious bugs found!");
            }else{
                System.out.println("Suspicious bugs found!");
            }
        }
    }
}