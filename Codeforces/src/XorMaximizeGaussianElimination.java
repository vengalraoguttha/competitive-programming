import java.util.ArrayList;
import java.util.Scanner;

public class XorMaximizeGaussianElimination {
    static int length(int a){
        int len = 0;
        while (a > 0){
            len++;
            a = a>>1;
        }
        return len;
    }

    static int max(int a, int b){
        if( a > b)
            return a;
        return b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            int arr[] = new int[n];
            int len[] = new int[n];
            int maxLen = 0;
            for(int i =0; i < n; i++){
                arr[i] = sc.nextInt();
                len[i] = length(arr[i]);
                maxLen = max(maxLen,len[i]);
            }

            int x[] = new int[100];

            ArrayList<Integer>[] buckets = new ArrayList[maxLen+1];
            for(int i =0; i < n; i++){
                if(buckets[len[i]]==null)
                    buckets[len[i]] = new ArrayList<>();
                buckets[len[i]].add(arr[i]);
            }

            int index = 0;

            for(int i = maxLen; i >= 1 ; i--){
                if(buckets[i] != null && buckets[i].size() > 0){
                    x[index] = buckets[i].get(0);
                    for(int l = 1; l < buckets[i].size(); l++){
                        int tmp = x[index]^buckets[i].get(l);
                        int ll = length(tmp);
                        if(buckets[ll] == null)
                            buckets[ll] = new ArrayList<>();
                        buckets[ll].add(tmp);
                    }
                    index++;
                }
            }

            int ans = k;
            for(int i = 0; i < index; i++){
                if( ans < (ans^x[i])){
                    ans = ans^x[i];
                }
            }

            System.out.println(ans);

            t--;
        }
    }
}
