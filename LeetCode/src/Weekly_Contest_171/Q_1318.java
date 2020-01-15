package Weekly_Contest_171;

import java.util.Scanner;

public class Q_1318 {
    public static int minFlips(int a, int b, int c) {
        int temp = a | b;
        int diff = temp ^ c;
        int ans = 0;
        while (diff != 0){
            int val = (diff) & 1;
            int exp = (c) & 1;
            int a_val = (a) & 1;
            int b_val = (b) & 1;
            if(val == 1 && exp == 1){
                ans += 1;
            }else if(val == 1 && a_val == 1 && b_val == 1){
                ans += 2;
            }else if(val == 1){
                ans += 1;
            }
            c = c >> 1;
            a = a >> 1;
            b = b >> 1;
            diff = diff >> 1;
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(minFlips(sc.nextInt(),sc.nextInt(),sc.nextInt()));
    }
}
