import java.util.Scanner;

public class ANGGRA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0){
            int n =  sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            long ans = 0;
            for(int i = 0; i < n; i++){
                long sum = 0;
                long count = 0;
                for(int j = 0; j < m; j++){
                    sum += j;
                    if(sum % m == 0){
                        count++;
                    }
                    if(count >= k){
                        ans += ((n-1-i)*m)%1000000007;
                    }
                }
            }
            System.out.println(ans);
        }
    }
}
