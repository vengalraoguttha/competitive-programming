import java.util.Scanner;

public class GOTCHI {
    static int dp[][][];
    static int calculate(int pos, int[] arr,int currentCup, int p, int currentCupCount){
        if(dp[pos][p][currentCup] != 0){
            return dp[pos][p][currentCup];
        }
        if(currentCupCount == 0){
            dp[pos][p][currentCup] = (currentCup*arr[pos])+calculate(pos+1,arr,currentCup,p,1);
            return dp[pos][p][currentCup];
        }else{
            if(currentCup <= p && pos+1 < arr.length)
                dp[pos][p][currentCup] = min(currentCup*arr[pos]+calculate(pos+1,arr,currentCup,p,currentCupCount+1),
                    (currentCup+1)*arr[pos]+calculate(pos+1,arr,currentCup+1,p,1));
            else if(pos+1 < arr.length)
                dp[pos][p][currentCup] = currentCup*arr[pos]+calculate(pos+1,arr,currentCup,p,currentCupCount+1);
            else if(currentCup < p-1 && currentCupCount == 0)
                dp[pos][p][currentCup] = Integer.MAX_VALUE;
            else
                dp[pos][p][currentCup] = p*arr[pos];
            return dp[pos][p][currentCup];
        }
    }

    static int min(int a, int b){
        if(a < b)
            return a;
        return b;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        int[] arr = new int[n];
        for(int i =0; i <n; i++){
            arr[i] = sc.nextInt();
        }
        dp = new int[arr.length][p+1][arr.length+2];
        System.out.println(calculate(0,arr,1,p,0));
    }
}
