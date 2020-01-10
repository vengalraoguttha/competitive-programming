import java.io.IOException;
import java.util.Arrays;

public class GDSUB {
        public static void main(String[] args) throws IOException {
            Reader sc = new Reader();
            int n = sc.nextInt();
            int k = sc.nextInt();

            int[] arr = new int[n];

            int[] times = new int[8001];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
                times[arr[i]]++;
            }

//            Arrays.sort(arr);

            int mod = 1000000007;

            long[][] counts = new long[8001][8001];
            boolean[] visited = new boolean[8001];
            int[] startInd = new int[8001];
            int count = 0;
//            for(int i = 0; i < arr.length ; i++){
//                boolean flag = false;
//                if(!visited[(arr[i])]){
//                    flag = true;
//                    count++;
//                    visited[arr[i]] = true;
//                }
//                for(int l = 1; l <= count ; l++){
//                    if(l == 1){
//                        counts[i][l] = (i != 0) ? (counts[i-1][l]+1)%mod : 1;
//                    }else{
//                        if(flag){
//                            counts[i][l] = (counts[i-1][l] + counts[i-1][l-1])%mod;
//                            startInd[l-1] = counts[i-1][l-1];
//                        }
//                        else{
//                            counts[i][l] = (counts[i-1][l] + startInd[l-1])%mod;
//                        }
//
//                    }
//                }
//            }

            int prev = 0;
            for (int i = 1; i < 8001; i++) {
                if (times[i] == 0)
                    continue;
                boolean flag = false;
                if (!visited[i]) {
                    flag = true;
                    count++;
                    visited[i] = true;
                }
                for (int l = 1; l <= count; l++) {
                    if (l == 1) {
                        counts[i][l] = (i != 0) ? (counts[prev][l] + times[i]) % mod : 1;
                    } else {
                        counts[i][l] = (counts[prev][l] + (times[i]%mod) * (counts[prev][l - 1]%mod)) % mod;
//                        if(flag){
//                            counts[i][l] = (counts[i-1][l] + counts[i-1][l-1])%mod;
//                            startInd[l-1] = counts[i-1][l-1];
//                        }
//                        else{
//                            counts[i][l] = (counts[i-1][l] + startInd[l-1])%mod;
//                        }

                    }
                }
                prev = i;
            }

            long val = 0;
            for (int i = 0; i <= min(k,8000); i++) {
                val += counts[prev][i];
                val = val % mod;
            }
            System.out.println(val + 1);
        }

        static int min(int a,int b){
            if( a < b)
                return a;
            return b;
        }
}
