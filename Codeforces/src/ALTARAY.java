import java.util.Scanner;

public class ALTARAY {
    static int alternating(long arr[], int dp[],int i){
        if(dp[i] != 0){
            return dp[i];
        }
        if(i < arr.length - 1){
            if(arr[i]*arr[i+1]<0){
                dp[i] = alternating(arr,dp,i+1)+1;
                return dp[i];
            }else{
                dp[i] = 1;
                return dp[i];
            }
        }
        dp[i] = 1;
        return dp[i];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int x = 0; x < t; x++){
            int n = sc.nextInt();
            long arr[] = new long[n];
            int dp[] = new int[n];
            for(int i = 0; i< n;i++){
                arr[i] = sc.nextInt();
            }
            for(int i = 0; i< n;i++){
                System.out.print(alternating(arr,dp,i)+" ");
            }
            System.out.println("");
        }
    }
}
