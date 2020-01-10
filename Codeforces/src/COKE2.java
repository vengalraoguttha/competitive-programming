import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class COKE2 {
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int t = sc.nextInt();

        while (t>0){
            int n = sc.nextInt();
            int q = sc.nextInt();
            int k = sc.nextInt();
            int l = sc.nextInt();
            int r = sc.nextInt();

            int[][] cans = new int[n][2];

            int[] cost = new int[n];
            int[] arr = new int[n];

            for(int i =0; i < n; i++){
                cans[i][0] = sc.nextInt();
                cans[i][1] = sc.nextInt();
                cost[i] = cans[i][1];
                arr[i] = cans[i][0];
            }

//            SegmentTree segmentTree = new SegmentTree(arr,cost,cost.length);

            for(int i = 1; i <= q; i++){
                int val = Integer.MAX_VALUE;
                for(int ind =0 ; ind < n; ind++){
                    int c = cans[ind][0];
                    int p = cans[ind][1];
                    if( c > k ){
                        int x = c - r;
                        int y = c - l;
                        c = max(k,c-i);
                    }else if(c < k){
                        c = min(k,c+i);
                    }
                    if( c >= l && c <= r){
                        val = min(val,p);
                    }
                }
                if(val == Integer.MAX_VALUE){
                    System.out.print("-1 ");
                }else{
                    System.out.print(val+" ");
                }
            }

            System.out.println("");
            t--;
        }
    }

    static int max(int a , int b){
        if( a < b)
            return b;
        return a;
    }

    static int min(int a , int b){
        if( a < b)
            return a;
        return b;
    }
}
