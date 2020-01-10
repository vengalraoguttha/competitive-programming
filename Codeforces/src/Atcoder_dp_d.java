import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Atcoder_dp_d {

//    static long[][] dp;

    static TreeMap<String,Long> ddp = new TreeMap<>();

    static long max(long a, long b){
        if(a > b)
            return a;
        return b;
    }

    static int KnapSack(int val[], int wt[], int n, int W)
    {
        // array to store final result
        //dp[i] stores the profit with KnapSack capacity "i"
        int []dp = new int[W+1];

        //initially profit with 0 to W KnapSack capacity is 0
        Arrays.fill(dp, 0);

        // iterate through all items
        for(int i=0; i < n; i++)

            //traverse dp array from right to left
            for(int j = W; j >= wt[i]; j--)
                dp[j] = Math.max(dp[j] , val[i] + dp[j - wt[i]]);

    /*above line finds out maximum of dp[j](excluding ith element value)
    and val[i] + dp[j-wt[i]] (including ith element value and the
    profit with "KnapSack capacity - ith element weight") */
        return dp[W];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w = sc.nextInt();

//        dp = new long[n][w+1];

        int[][] arr = new int[n][2];
        int[] val = new int[n];
        int[] wt = new int[n];

        for(int i = 0; i < n; i++){
            wt[i] = sc.nextInt();
            val[i] = sc.nextInt();
        }

//        for(int i = 0; i < n; i++){
//            for(int j = 0; j < w+1 ; j++){
//                dp[i][j] = -1;
//            }
//        }

        System.out.println(KnapSack(val,wt,n,w));
    }
}
class Data{
    int size,weight;
}