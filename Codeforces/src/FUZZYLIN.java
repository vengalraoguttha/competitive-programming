import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class FUZZYLIN {

    static int gcd(int a, int b)
    {
        return (a % b == 0) ?
                Math.abs(b) : gcd(b,a%b);
    }

    public static void main(String[] args) throws IOException {
        Reader sc =new Reader();
        int n = sc.nextInt();
        int[] arr = new int[n];
        boolean allEven = true;
        for(int i = 0; i < n ; i++){
            arr[i] = sc.nextInt();
            if(arr[i]%2==1)
                allEven = false;
        }

        int q = sc.nextInt();


        long[] a = new long[1000001];

        int[] dp = new int[100001];

        int x = arr[0];
        for(int i =1; i < n; i++){
            x = gcd(x,arr[i]);
        }

        for(int i =0; i < n; i++){
            dp[i] = arr[i];
            if(dp[i] < 1000001)
                a[dp[i]]++;
            for(int j =i+1; j < n; j++){
                dp[j] = gcd(dp[j-1],arr[j]);

                if( dp[j] < 1000001)
                    a[dp[j]]++;
                if(dp[j] == 1){
                    if(n-j-1 > 0)
                        a[dp[j]] += n-j-1;
                    break;
                }else if(allEven && dp[j] == 2){
                    if(n-j-1 > 0)
                        a[dp[j]] += n-j-1;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i =0; i < q; i++){
            int k = sc.nextInt();
            if( x > k){
                sb.append(0).append("\n");
                continue;
            }
            long count = 0;
            for(int l =1; l <= Math.sqrt(k) ; l++){
                if(k % l == 0){
                    if( k/l == l )
                        count += a[l];
                    else{
                        count += a[l];
                        count += a[k/l];
                    }
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb.toString());
    }
}

/*
57
59
59
61
58
61
57
61
59
61
58

 */