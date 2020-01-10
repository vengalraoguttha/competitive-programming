import java.util.Scanner;

public class Atcoder_dp_h {
    static int[][] dp;
    static int mod  = 1000000007;

    static int max(int a, int b){
        if( a > b )
            return a;
        return b;
    }

    static int calculate(String[] arr, int i, int j){

        if(i < 0 || j < 0)
            return 0;

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        if(arr[i].charAt(j) == '#'){
            dp[i][j] =  0;
            return dp[i][j];
        }

        if(i == 0 && j == 0){
            dp[i][j] = 1;
            return dp[i][j];
        }

        dp[i][j] = (calculate(arr,i-1,j)+calculate(arr,i,j-1))%mod;
        return dp[i][j];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();

        String[] arr = new String[h];
        dp = new int[h][w];

        for(int i = 0; i < h; i++){
           arr[i] = sc.next();
        }

        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                dp[i][j] = -1;
            }
        }

        System.out.println(calculate(arr,h-1,w-1));
    }
}
