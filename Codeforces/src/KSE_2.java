import java.util.Scanner;

public class KSE_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while( t > 0){
            int d = sc.nextInt();
            int s = sc.nextInt();
            int arr[][] = new int[s][2];
            int maxC = 0, maxE = 0;
            for(int i = 0; i < s; i++){
                arr[i][0] = sc.nextInt();
                arr[i][1] = sc.nextInt();
                maxC += arr[i][0];
                maxE += arr[i][1];
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < d; i++){
                int A = sc.nextInt();
                int B = sc.nextInt();
                if(A > maxC || B > maxE){
                    sb.append("N");
                }else if( (A == maxC && B == 0) || (A == 0 && B == maxE) ){
                    sb.append("Y");
                }else{

                }
            }

            t--;
        }
    }
}
