import java.util.Scanner;

public class RHSAT {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        int ans = 0;
        while (q > 0){
            int n = sc.nextInt();
            int base = 9;
            int count = 1;
            int val = 0;
            while((n - count*base) > 0){
                n = n - count*base;
                val += base;
                base *= 10;
                count++;
            }

            int num = (int) Math.ceil((n*1.0)/count);

            num = val + num;

            int pos = n % count;

            if( pos == 0){
                ans += num%10;
            }else{
                ans += Integer.valueOf(""+String.valueOf(num).charAt(pos-1));
            }

            q--;
        }
        System.out.println(ans);
    }
}
