import java.math.BigInteger;
import java.util.Scanner;

public class RPH001 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0){
            BigInteger b1 = new BigInteger(sc.next());
            b1 = new BigInteger((new StringBuilder(b1.toString())).reverse().toString());
            BigInteger b2 = new BigInteger(sc.next());
            b2 = new BigInteger((new StringBuilder(b2.toString())).reverse().toString());
            b1 = b1.add(b2);
            StringBuilder sb = new StringBuilder(b1.toString());
            b1 =  new BigInteger(sb.reverse().toString());
            System.out.println(b1.toString());
            t--;
        }
    }
}
