import java.util.Scanner;

public class LECANDY {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0){
            int n = sc.nextInt();
            long c = sc.nextInt();
            long count = 0;
            for(int i = 0; i < n; i++){
                count += sc.nextInt();
            }
            if(c >= count)
                System.out.println("Yes");
            else
                System.out.println("No");
            t--;
        }
    }
}
