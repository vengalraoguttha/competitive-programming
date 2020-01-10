import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class JourneyToMoon {

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
    static long journeyToMoon(int n, int[][] astronaut) {
        BigInteger cost  = BigInteger.valueOf(0);
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < astronaut.length ; i++){
            if(graph[astronaut[i][0]] == null){
                graph[astronaut[i][0]] = new ArrayList<>();
            }
            graph[astronaut[i][0]].add(astronaut[i][1]);
            try {
                if(graph[astronaut[i][1]] == null){
                    graph[astronaut[i][1]] = new ArrayList<>();
                }
            }catch (Exception e){
            }
            graph[astronaut[i][1]].add(astronaut[i][0]);
        }
        List<Long> sizes = new ArrayList<>();
        long total = 0;
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(visited[i]){
                continue;
            }
            // find the size of connected component.
            long size = bfs(graph,i,visited);
            cost = cost.subtract(BigInteger.valueOf(size).multiply(BigInteger.valueOf(size)));
            total += size;
            sizes.add(size);
        }
        cost = (cost.add(BigInteger.valueOf(total).multiply(BigInteger.valueOf(total)))).divide(BigInteger.valueOf(2));
        return cost.longValue();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] np = scanner.nextLine().split(" ");

        int n = Integer.parseInt(np[0]);

        int p = Integer.parseInt(np[1]);

        int[][] astronaut = new int[p][2];

        for (int i = 0; i < p; i++) {
            String[] astronautRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int astronautItem = Integer.parseInt(astronautRowItems[j]);
                astronaut[i][j] = astronautItem;
            }
        }

        long result = journeyToMoon(n, astronaut);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}