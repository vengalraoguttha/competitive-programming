import java.util.Scanner;

public class MSNSADM1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int i =0 ; i < t; i++){
            int n = sc.nextInt();
            int[] goals = new int[n];
            int[] fouls = new int[n];
            int max = 0;
            for(int j =0; j<n ;j++){
                goals[j] = sc.nextInt();
            }
            for(int j =0; j<n ;j++){
                fouls[j] = sc.nextInt();
                if(20*goals[j]-10*fouls[j]>max){
                    max = 20*goals[j]-10*fouls[j];
                }
            }
            System.out.println(max);
        }
    }
}
