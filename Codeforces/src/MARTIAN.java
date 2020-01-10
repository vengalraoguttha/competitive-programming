import java.util.Scanner;

public class MARTIAN {

    static long max(long a, long b){
        if( a > b)
            return a;
        return b;
    }
    static long[][] dp;
    static long calculate(int i, int j,long[][] grid_yeyenum,long[][] grid_bloggium,long[][] grid_yeyenum_sum,long[][] grid_bloggium_sum){
        if( i < 0 || j < 0)
            return 0;
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        if( i == 0 && j == 0) {
            dp[i][j] = max(grid_yeyenum[0][0], grid_bloggium[0][0]);
            return dp[i][j];
        }
        dp[i][j] = max(calculate(i-1,j,grid_yeyenum,grid_bloggium,grid_yeyenum_sum,grid_bloggium_sum)+grid_yeyenum_sum[i][j],
                calculate(i,j-1,grid_yeyenum,grid_bloggium,grid_yeyenum_sum,grid_bloggium_sum)+grid_bloggium_sum[i][j]);
        return dp[i][j];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true){
            int n = sc.nextInt();
            int m = sc.nextInt();

            if(n == 0 && m == 0)
                break;
            long[][] grid_yeyenum = new long[n][m];
            long[][] grid_bloggium = new long[n][m];
            long[][] grid_yeyenum_sum = new long[n][m];
            dp = new long[n][m];

            for(int i = 0; i < n ; i++){
                for(int j =0; j < m ; j++){
                    dp[i][j] = -1;
                    grid_yeyenum[i][j] = sc.nextInt();
                    if(j == 0)
                        grid_yeyenum_sum[i][j] = grid_yeyenum[i][j];
                    else
                        grid_yeyenum_sum[i][j] = grid_yeyenum_sum[i][j-1] + grid_yeyenum[i][j];
                }
            }

            long[][] grid_bloggium_sum = new long[n][m];
            for(int i = 0; i < n ; i++){
                for(int j =0; j < m ; j++){
                    grid_bloggium[i][j] = sc.nextInt();
                }
            }

            for(int j = 0; j < m ; j++){
                for(int i =0; i < n ; i++){
                    if(i == 0)
                        grid_bloggium_sum[i][j] = grid_bloggium[i][j];
                    else
                        grid_bloggium_sum[i][j] = grid_bloggium_sum[i-1][j] + grid_bloggium[i][j];
                }
            }

            System.out.println(calculate(n-1,m-1,grid_yeyenum,grid_bloggium,grid_yeyenum_sum,grid_bloggium_sum));
        }
    }
}
