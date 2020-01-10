import java.util.Scanner;

public class DSTAPLS {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int i =0 ; i < t; i++){
            long n = sc.nextLong();
            long k = sc.nextLong();
            if(k == 1){
                System.out.println("NO");
            }else {
                long tmp = n/k;
                if(n % k ==0 && tmp %k ==0){
                    System.out.println("NO");
                }else{
                    System.out.println("YES");
                }
            }
        }
    }
}
