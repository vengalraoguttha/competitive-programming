import java.util.Scanner;

public class BIT2B_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();
            int x = sc.nextInt();

            long a = 0;
            long b = 0;
            int i =0 , j = n-1;
            int aBox = 0;
            int bBox = 0;
            while (i <= j){
                if(a < x*b ){
                    aBox++;
                    a += arr[i];
                    i++;
                }else if( a > x*b){
                    bBox++;
                    b += arr[j];
                    j--;
                }else{
                    if(aBox >= bBox){
                        aBox++;
                        a += arr[i];
                        i++;
                    }else{
                        bBox++;
                        b += arr[j];
                        j--;
                    }
                }
            }

            System.out.println(aBox+" "+bBox);
        }
    }
}
