import java.io.*;
import java.math.*;
import java.net.URL;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RoadsAndLibraries {

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
        return size-1;
    }

    static long min(long a, long b){
        if(a<b)
            return a;
        return b;
    }

    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {

        long cost  = 0;
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < cities.length ; i++){
            if(graph[cities[i][0]-1] == null){
                graph[cities[i][0]-1] = new ArrayList<>();
            }
            graph[cities[i][0]-1].add(cities[i][1]-1);
            try {
                if(graph[cities[i][1]-1] == null){
                    graph[cities[i][1]-1] = new ArrayList<>();
                }
            }catch (Exception e){
            }
            graph[cities[i][1]-1].add(cities[i][0]-1);
        }
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(visited[i]){
                continue;
            }
            cost += c_lib;
            // find the size of connected component.
            long size = bfs(graph,i,visited);
            cost += min((c_road*size),c_lib*(size));
        }
        return cost;
    }



    public static void main(String[] args) throws IOException {

        URL url = new URL("https://hr-testcases-us-east-1.s3.amazonaws.com/25952/input02.txt?AWSAccessKeyId=AKIAJ4WZFDFQTZRGO3QA&Expires=1566121599&Signature=t95YF8PZoElhuyjDdpIthCFZgvE%3D&response-content-type=text%2Fplain");

        Scanner scanner = new Scanner(url.openStream());
        int q = scanner.nextInt();

        for (int qItr = 0; qItr < q; qItr++) {

            int n = scanner.nextInt();

            int m = scanner.nextInt();

            int c_lib = scanner.nextInt();

            int c_road = scanner.nextInt();

            int[][] cities = new int[m][2];
            System.out.println(n+" "+m+" "+c_lib+" "+c_road);
            for (int i = 0; i < m; i++) {

                for (int j = 0; j < 2; j++) {
                    int citiesItem = scanner.nextInt();
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);
            System.out.println(result);

        }

        scanner.close();
    }
}