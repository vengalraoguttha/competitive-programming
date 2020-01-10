import java.util.Scanner;

public class CNOTE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int  t = sc.nextInt();
        while (t > 0){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int k = sc.nextInt();
            int n = sc.nextInt();
            int diff = x - y;
            boolean flag = false;
            for(int i = 0; i < n ;i++){
                int p = sc.nextInt();
                int c = sc.nextInt();
                if( (diff <= p) && (c <= k)){
                    flag = true;
                }
            }
            System.out.println(flag?"LuckyChef":"UnluckyChef");
            t--;
        }
    }
}
