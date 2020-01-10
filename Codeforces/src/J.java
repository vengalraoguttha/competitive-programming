import java.util.Arrays;
import java.util.Scanner;

public class J {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];

        int total = 0;
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
            total += a[i];
        }

        double[][][] dp = new double[n][total+1][4];

        for(int i = 0; i < n; i++){
            dp[i][1][1] = n;
        }

        for(int i = 2; i < total+1 ; i++){
            // i --> i sushi to eat
            for(int j = 1; j < n; j++){
                // j --> current dish.
                for(int k = 0; k < 4; k++){
                    if(k > 0){
                        dp[j][i][k] = (dp[j-1][i-1][0]+dp[j-1][i-1][1]);
                        dp[j][i][k] += (dp[j][i-1][k-1])/2.0;
                    }else{
                        dp[j][i][k] = (dp[j-1][i-1][k]+n);
                    }
                }
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < total +1 ; j ++){
                System.out.print(Arrays.toString(dp[i][j])+" ");
            }
            System.out.println();
        }

        for(int i = 0; i < n; i++){
            System.out.println(dp[i][total][a[i]]);
        }

        System.out.println(Arrays.toString(dp[n-1][total]));
    }
}
