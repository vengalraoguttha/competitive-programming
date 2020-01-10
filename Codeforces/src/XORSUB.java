import java.util.Scanner;

public class XORSUB {
    static int dp[][] = new int[1001][1024];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int x = 0; x < t; x++){
            int n = sc.nextInt();
            int k = sc.nextInt();
            int arr[] = new int[n];
            for(int i = 0; i < n;i++){
                arr[i] = sc.nextInt();
            }

            dp[n][0] = 1;

            for(int i = n-1 ; i >=0 ; i--){
                for(int j = 0; j < 1024; j++){
                    dp[i][j] = dp[i+1][j] | (dp[i+1][j ^ arr[i]]);
                }
            }

            int max = k;
            for(int j = 0; j < 1024; j++){
                if(max < dp[0][j]*(j^k)){
                    max = j^k;
                }
            }

            System.out.println(max);
        }
    }
}
