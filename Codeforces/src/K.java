import java.util.Scanner;

public class K {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] a = new int[n];

        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }

        boolean[] dp = new boolean[k+1];

        for(int i = 0; i < k+1; i++){
            for(int x : a){
                if(i >= x && !dp[i-x]){
                    dp[i] = true;
                }
            }
        }

        System.out.println(dp[k] ? "First" : "Second");
    }
}
