import java.util.Arrays;
import java.util.Scanner;

public class JNEXT_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while ( t-- > 0) {
            int n = sc.nextInt();
            Integer[] number = new Integer[n];
            for(int i = 0; i < n; i++){
                number[i] = sc.nextInt();
            }
            if(n == 1){
                System.out.println("-1");
                continue;
            }
            boolean notExists = false;
            int i;
            for(i = n-2; i >= 0; i--){
                if(number[i] < number[i+1]){
                    break;
                }
                if(i==0){
                    System.out.println("-1");
                    notExists = true;
                }
            }

            if(notExists){
                continue;
            }

            int ind = i+1;
            for(int j = i+1 ; j < n ; j++){
                if(number[ind] > number[j] && number[j] > number[i]){
                    ind = j;
                }
            }

            int temp = number[i];
            number[i] = number[ind];
            number[ind] = temp;
            Arrays.sort(number,i+1,n);

            StringBuilder sb = new StringBuilder();
            for(i = 0; i < n; i++){
                sb.append(number[i]);
            }
            System.out.println(sb.toString());
        }
    }
}
