package Weekly_Contest_171;

import java.util.Scanner;

public class Q_1317{

    private static boolean check(int x){
        while (x%10 != 0){
            x /= 10;
        }
        return x == 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for(int i = 1; i < n; i++){
            if(check(i) && check(n-i)){
                System.out.println("["+i+","+(n-i)+"]");
            }
        }
    }
}