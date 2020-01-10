import java.util.Arrays;
import java.util.Scanner;

public class COPS {

//    static int min(int a, int b){
//        if( a < b)
//            return a;
//        return b;
//    }
//
//    static int max(int a, int b){
//        if( a > b)
//            return a;
//        return b;
//    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0){
            int m = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            int prevMax = 0;
            int count = 0;

            int[] arr = new int[m];
            for(int i = 0; i < m ;i++){
                arr[i] = sc.nextInt();
            }

            Arrays.sort(arr);

            for(int i = 0; i < m ;i++){
                int pos = arr[i];
                int currentMin = pos - x*y > 0 ? pos - x*y : 1;
                int currentMax = pos + x*y < 100 ? pos + x*y : 100;
                count += currentMin - prevMax -1 > 0 ? currentMin - prevMax -1 : 0;
                prevMax = currentMax;
            }
            count += 100 - prevMax > 0 ? 100 - prevMax : 0;
            System.out.println(count);
            t--;
        }
    }
}
