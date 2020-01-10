import java.util.Scanner;

public class Atcoder_dp_a {

    static int[] dp;

    static int min(int a,int b){
        if(a<b)
            return a;
        return b;
    }
    static int calculate(int n,int[] arr, int k){
        if(n < 1){
            return Integer.MAX_VALUE;
        }
        if(dp[n] != -1){
            return dp[n];
        }
        if(n == 1){
            dp[n] = 0;
            return dp[n];
        }else if(n == 2){
            dp[n] = Math.abs(arr[n]-arr[n-1]);
            return dp[n];
        }
        dp[n] = Math.abs(arr[n]-arr[n-1])+calculate(n-1,arr,k);
        for(int i = 2; i <=k; i++){
            if(n-i>=1){
                dp[n] = min(dp[n],Math.abs(arr[n]-arr[n-i])+calculate(n-i,arr,k));
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n+1];

        dp = new int[n+1];

        for(int i =1; i <= n; i++){
            dp[i] = -1;
            arr[i] = sc.nextInt();
        }
        System.out.println(calculate(n,arr,k));
    }
}
