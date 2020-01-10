import java.util.Scanner;

public class Atcoder_dp_c {

    static long[][] dp;

    static long max(long a, long b){
        if( a > b )
            return a;
        return b;
    }

    static long max(long a, long b, long c){
        if( a > b && a > c)
            return a;
        else if( b > a && b > c )
            return b;
        return c;
    }

    static long calculate(int[][] arr,int pos,int selection){
        if(pos >= arr.length)
            return 0;
        if(selection == -1){
            dp[pos][0] = arr[pos][0] + calculate(arr,pos+1,0);
            dp[pos][1] = arr[pos][1] + calculate(arr,pos+1,1);
            dp[pos][2] = arr[pos][2] + calculate(arr,pos+1,2);
            return max(dp[pos][0],dp[pos][1],dp[pos][2]);
        }else{
            if(selection == 0){
                if(dp[pos][1] == -1)
                dp[pos][1] = arr[pos][1] + calculate(arr,pos+1,1);
                if(dp[pos][2] == -1)
                dp[pos][2] = arr[pos][2] + calculate(arr,pos+1,2);
                return max(dp[pos][1],dp[pos][2]);
            }else if(selection == 1){
                if(dp[pos][0] == -1)
                dp[pos][0] = arr[pos][0] + calculate(arr,pos+1,0);
                if(dp[pos][2] == -1)
                dp[pos][2] = arr[pos][2] + calculate(arr,pos+1,2);
                return max(dp[pos][0],dp[pos][2]);
            }else{
                if(dp[pos][0] == -1)
                dp[pos][0] = arr[pos][0] + calculate(arr,pos+1,0);
                if(dp[pos][1] == -1)
                dp[pos][1] = arr[pos][1] + calculate(arr,pos+1,1);
                return max(dp[pos][0],dp[pos][1]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dp = new long[n][3];
        int[][] arr = new int[n][3];
        for(int i =0; i < n; i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            arr[i][2] = sc.nextInt();
            dp[i][0] = -1;
            dp[i][1] = -1;
            dp[i][2] = -1;
        }

        System.out.println(calculate(arr,0,-1));

    }
}
