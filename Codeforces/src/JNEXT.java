import java.util.Scanner;

public class JNEXT {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while ( t-- > 0){
            int n = sc.nextInt();
            int[] number = new int[n];
            int min = Integer.MAX_VALUE;
            for(int i = 0; i < n; i++){
                number[i] = sc.nextInt();
                if(min > number[i]){
                    min = number[i];
                }
            }

            if(min == 0){
                continue;
            }

            int carry = min;

            for(int i = n-1; i >= 0; i--){
                number[i] = carry + number[i];
                carry = number[i]/10;
                number[i] %= 10;
            }

            if(carry != 0){
                System.out.print(carry+" ");
            }
            for(int i = 0; i < n; i++){
                System.out.print(number[i]+" ");
            }
            System.out.println();
        }
    }
}
