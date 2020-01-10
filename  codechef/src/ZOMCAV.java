import java.util.Arrays;
import java.util.Scanner;

public class ZOMCAV {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int x =0 ; x < t; x++){
            int n = sc.nextInt();
            int[] arr = new int[n];
            int[] health = new int[n];
            int[] diff = new int[n];
            for(int i = 0; i < n ; i++){
                arr[i] = sc.nextInt();
                int s_pos = i-arr[i] < 0 ? 0 : i - arr[i];
                diff[s_pos] += 1;
                int e_pos = i+arr[i] >= n ? n-1 : i+arr[i];
                if(e_pos+1 < n)
                    diff[e_pos+1] -= 1;
            }
            int[] radiation = new int[n];
            radiation[0] = diff[0];
            for(int i =1; i < n ; i++){
                radiation[i] = radiation[i-1]+diff[i];
            }
            Arrays.sort(radiation);
            boolean isNoSet = false;
            for(int i =0; i < n; i++){
                int h = sc.nextInt();
                int ind = Arrays.binarySearch(radiation,h);
                if(ind<0 && !isNoSet){
                    isNoSet = true;
                    System.out.println("NO");
                }
            }
            if(!isNoSet){
                System.out.println("YES");
            }
        }
    }
}
