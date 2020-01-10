import java.util.Scanner;

public class DBOY {

    static int dp[][] = new int[1001][500];

    static int calculate(int x, int n,int size, int cap[]){
        if( x == 0){
            dp[x][n] = 0;
            return dp[x][n];
        }

        if(n >= size || x < 0){
            return Integer.MAX_VALUE;
        }

        if(dp[x][n] != -1){
            return dp[x][n];
        }

        int a = calculate(x-cap[n],n,size,cap);
        if( a != Integer.MAX_VALUE){
            a++;
        }

        dp[x][n] = min(a,calculate(x,n+1,size,cap));

        return dp[x][n];
    }

    static int min(int a, int b){
        if( a < b ){
            return a;
        }
        return b;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int x =0; x < t; x++){
            int n = sc.nextInt();
            int dist[] = new int[n];
            int Cap[] = new int[n];

            for(int i =0; i <1001; i++){
                for(int j =0; j < 500; j++){
                    dp[i][j] = -1;
                }
            }

            for(int i = 0; i < n;i++){
                dist[i] = sc.nextInt();
            }
            for(int i = 0; i < n;i++){
                Cap[i] = sc.nextInt();
            }

            int count = 0;
            for(int i = 0; i < n ; i++){
                count += calculate(2*dist[i],0,n,Cap);
            }
            System.out.println(count);
        }
    }
}
