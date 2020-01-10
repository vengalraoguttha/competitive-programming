import java.util.Scanner;

public class GHOME {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long m = sc.nextLong();
        long n = sc.nextLong();

        System.out.println((long) ((n*(m/2))+((m%2)*Math.ceil(n*1.0/2))));
    }
}
