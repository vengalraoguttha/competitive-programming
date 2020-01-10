import java.util.Scanner;

public class Knapsack2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w = sc.nextInt();
        int[] wt = new int[n];
        int[] val = new int[n];

        for(int i = 0; i < n; i++){
            wt[i] = sc.nextInt();
            val[i] = sc.nextInt();
        }

        int[][] dp = new int[n+1][w+1];

        for(int i = 0; i < n; i++){
            dp[i][w] = 0;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 0; j < w; j++){
                if(j - wt[i-1] >= 0){
                    dp[i][j-wt[i-1]] =dp[i-1][j-wt[i-1]] + val[i-1];
                    dp[i][j] = dp[i-1][j];
                }
//                else{
//                    dp[i][0] = Math.max(dp[i][0],dp[i][j]);
//                }
            }
        }

        for(int i = 0; i <= n; i++){
            for(int j = 0; j < w+1; j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println(dp[n-1][0]);
    }
}
