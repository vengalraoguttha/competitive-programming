import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Atcoder_dp_g_1 {

    static int max(int a, int b){
        if( a > b)
            return a;
        return b;
    }

    static int[] dp;
    static int calculate(List<Integer>[] graph,int i){
        if(dp[i] != -1){
            return dp[i];
        }
        int path = 0;
        if(graph[i] != null){
            for(Integer ii : graph[i]){
                path = max(path,1+calculate(graph,ii));
            }
        }
        dp[i] = path;
        return dp[i];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Integer>[] graph = new ArrayList[n];
        dp = new int[n];

        int[] dist = new int[n];

        for(int i = 0; i < n; i++){
            dist[i] = Integer.MIN_VALUE;
            dp[i] = -1;
        }

        for(int i = 0; i < m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(graph[x-1] == null)
                graph[x-1] = new ArrayList<Integer>();
            graph[x-1].add(y-1);
        }

        int max = 0;
        for(int i = 0; i < n; i++){
            max = max(max,calculate(graph,i));
        }

        System.out.println(max);
    }
}
