import java.util.*;

public class MATHL {

    static int max(int a, int b){
        if( a > b )
            return a;
        return b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[] arr= new int[t];
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < t; i++){
            arr[i] = sc.nextInt();
            max = max(max,arr[i]);
        }

        long[] res = new long[max+1];
        long[] g = new long[max+1];

        res[1] = 1;
        g[1] = 1;

        long mod = 1000000007;

        for(int i = 2; i <= max; i++){
            res[i] = ((res[i-1]*g[i-1]%mod)*i)%mod;
            g[i] = (g[i-1]*i)%mod;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < t; i++){
            sb.append(res[arr[i]]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
class MATHLIn{
    int a, pos;
    long ans;
}