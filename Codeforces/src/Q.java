import java.util.Scanner;

public class Q {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] h = new int[n];
        int[] a = new int[n];
        int[] x = new int[n];

        for(int i = 0; i < n; i++){
            h[i] = sc.nextInt();
        }

        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
            x[h[i]-1] = a[i];
        }

        long[] dp = new long[n+1];
        long max = 0;
        for(int i = 1; i <= n; i++){
            long m = 0;
            // optimise this below loop with segment tree
            for(int j = 0; j <= h[i-1]-1; j++)
                m = Math.max(dp[j],m);
            dp[h[i-1]] = m + a[i-1];
            max = Math.max(dp[h[i-1]] , max);
//            dp[i] = dp[i-1] + x[i-1];
        }

        System.out.println(max);
    }
}
