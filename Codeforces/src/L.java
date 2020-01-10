import java.util.Arrays;
import java.util.Scanner;

public class L {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }

        long[][][] dp = new long[n][n][2]; // dp[i][j] -> x - y value in i to j

        for(int i = 0; i < n; i++){
            dp[i][i][0] = a[i];
            dp[i][i][1] = -a[i];
        }

        for(int i = n-1; i >= 0; i--){
            for(int j = i+1; j < n; j++){
                if(i < n-1 && j >= 1){
                    dp[i][j][0] = Math.max(a[i]+ dp[i+1][j][1],a[j] + dp[i][j-1][1]);
                    dp[i][j][1] = Math.min(- a[i]+ dp[i+1][j][0],- a[j] + dp[i][j-1][0]);
                }
                else if(i < n-1){
                    dp[i][j][0] = a[i]+ dp[i+1][j][1];
                    dp[i][j][1] = a[i]+ dp[i+1][j][0];
                }
                else if(j > 1){
                    dp[i][j][0] = a[j] + dp[i][j-1][1];
                    dp[i][j][1] = a[j] + dp[i][j-1][0];
                }
            }
        }

        System.out.println(dp[0][n-1][0]);
    }
}
