import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SubSetComponents {

    static int bfs(ArrayList<Integer>[] graph, int vertex, boolean[] visited){
        Queue<Integer> queue = new LinkedList<>();
        visited[vertex] = true;
        queue.add(vertex);
        int size = 0;
        while (!queue.isEmpty()){
            Integer point = queue.remove();
            size++;
            if(graph[point] != null)
                for(int i = 0; i < graph[point].size();i++){
                    if(!visited[graph[point].get(i)]){
                        queue.add(graph[point].get(i));
                        visited[(graph[point].get(i))] = true;
                    }
                }
        }
        return size;
    }

    // Complete the journeyToMoon function below.
    static long findConnectedComponents(int n, ArrayList<Integer>[] graph) {
        boolean[] visited = new boolean[n];
        int components = 0;
        for(int i = 0; i < n; i++){
            if(visited[i]){
                continue;
            }
            // find the size of connected component.
            bfs(graph,i,visited);
            components++;
        }
        return components;
    }

    static long findSubSetComponents(ArrayList<Integer>[] graph,int index, long[] d){
        if(index >= d.length){
            return findConnectedComponents(graph.length,graph);
        }
        System.out.println(index);
        long a = findSubSetComponents(makeCopy(graph),index+1,d);
        System.out.println(index+" "+a);
        long val = d[index];
        int previous_index = -1;
        int current_index = 0;
        boolean flag = false;
        while(val != 0){
            if(val %2 == 1){
                if(previous_index != -1){
                    if(graph[previous_index] == null)
                        graph[previous_index] = new ArrayList<>();
                    graph[previous_index].add(current_index);
                    if(graph[current_index] == null)
                        graph[current_index] = new ArrayList<>();
                    graph[current_index].add(previous_index);
                    flag = true;
                }
                previous_index = current_index;
            }
            val = val/2;
            current_index++;
        }
        long b = a;
        if(flag){
            b = findSubSetComponents(graph,index+1,d);
        }
        System.out.println(index+" "+b);
        return a+b;
    }

    static ArrayList<Integer>[] makeCopy(ArrayList<Integer>[] graph){
        ArrayList<Integer>[] arr = new ArrayList[graph.length];
        for(int i = 0; i < graph.length;i++){
            if(graph[i] != null)
                arr[i] = new ArrayList<>(graph[i]);
        }
        return arr;
    }

    // Complete the findConnectedComponents function below.
    static long findConnectedComponents(long[] d) {
        ArrayList<Integer>[] graph = new ArrayList[64];
        return findSubSetComponents(graph,0,d);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int dCount = scanner.nextInt();

        long[] d = new long[dCount];

        for (int i = 0; i < dCount; i++) {
            long dItem = scanner.nextLong();
            d[i] = dItem;
        }

        long components = findConnectedComponents(d);
        System.out.println(components);

        scanner.close();
    }
}