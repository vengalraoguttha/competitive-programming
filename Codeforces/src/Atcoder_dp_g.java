import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Atcoder_dp_g {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] graph = new int[n][n];
        int[][] lens = new int[n][n];

        for(int i =0; i < n; i++){
            for(int j = 0; j < n ; j++){
                lens[i][j] = Integer.MIN_VALUE;
            }
        }

        for(int i = 0; i < m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[x-1][y-1] = 1;
            lens[x-1][y-1] = 1;
        }

        for(int i =0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k =0; k < n; k++){
                    if(lens[i][j] < lens[i][k] + lens[k][j] && lens[i][k] != Integer.MIN_VALUE && lens[k][j] != Integer.MIN_VALUE){
                        lens[i][j] = lens[i][k] + lens[k][j];
                    }
                }
            }
        }

        int ans = Integer.MIN_VALUE;
        for(int i =0; i < n; i++){
            for (int j =0; j < n; j++){
                if(ans < lens[i][j]){
                    ans = lens[i][j];
                }
            }
        }
        System.out.println(ans);
    }
}
