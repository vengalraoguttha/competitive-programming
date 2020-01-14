package jan2019_long_codechef;

import java.util.Scanner;

public class DYNAMO {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0){
            int n = sc.nextInt();
            long max = (long) Math.pow(10,n);
            long a = sc.nextLong();
            long s = a + 2*max;
            System.out.println(s);
            long b = sc.nextLong();
            long c = max - b;
            System.out.println(c);
            long d = sc.nextLong();
            long e = max - d;
            System.out.println(e);
            int x = sc.nextInt();
            if(x == -1){
                break;
            }
        }
    }
}
