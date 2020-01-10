import java.util.Scanner;

public class BULLETS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0){
            int n = sc.nextInt();
            int ans = 0;
            for(int i =0; i < n; i++){
                ans = ans ^ (sc.nextInt());
            }
            if( ans == 0){
                System.out.println("Isa");
            }else{
                System.out.println("Gaitonde");
            }
            t--;
        }
    }
}
