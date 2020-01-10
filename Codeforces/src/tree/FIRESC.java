package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FIRESC {
    static int bfs(ArrayList<Integer>[] graph, int start, int n, boolean[] visited){
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        visited[start] = true;
        queue.add(start);
        while (!queue.isEmpty()){
            count++;
            int val = queue.remove();
            if(graph[val] != null)
            for(Integer integer : graph[val]){
                if(!visited[integer]){
                    visited[integer] = true;
                    queue.add(integer);
                }
            }
        }
        return count;
    }
    static FirstResult findConnectedCompos(ArrayList<Integer>[] graph,int n, int mod){
        long val = 1;
        boolean visited[] = new boolean[n];
        int count = 0;
        for(int i =0; i<n;i++){
            if(!visited[i]){
                visited[i] = true;
                val *= (bfs(graph,i,n,visited)%mod);
                val %= mod;
                count++;
            }
        }
        FirstResult firstResult = new FirstResult();
        firstResult.val = val;
        firstResult.count = count;
        return firstResult;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int mod = 1000000007;

        StringBuilder sb = new StringBuilder();
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
            FirstResult firstResult = findConnectedCompos(graph,n,mod);
            System.out.println(firstResult.count+" "+firstResult.val);
        }
    }
}
class FirstResult{
    long val;
    int count;
}