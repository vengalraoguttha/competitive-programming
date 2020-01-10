import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;
import java.util.Scanner;

public class SALARY {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            long count = 0;
            int min = Integer.MAX_VALUE;
            for(int i =0; i< n;i++){
                arr[i] = sc.nextInt();
                count += arr[i];
                if(min > arr[i]){
                    min = arr[i];
                }
            }
            System.out.println(count-n*min);
            t--;
        }
    }
}
