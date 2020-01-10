import java.util.Scanner;

public class TADELIVE {
    static int X,Y;
    static int dp[][][];
    static int calculate(int x , int y, int i, int a[], int b[]){
        if(i == a.length){
            return 0;
        }
        if( x < X && y < Y){
            dp[x][y][i] = max(calculate(x+1,y,i+1,a,b)+a[i],calculate(x,y+1,i+1,a,b)+b[i]);
        }else if( x < X){
            dp[x][y][i] = calculate(x+1,y,i+1,a,b)+a[i];
        }else{
            dp[x][y][i] = calculate(x,y+1,i+1,a,b)+b[i];
        }
        return dp[x][y][i];
    }

    static int max(int a, int b){
        if( a > b)
            return a;
        return b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int a[] = new int[n];
        int b[] = new int[n];
        for(int i = 0; i<n ; i++){
            a[i] = sc.nextInt();
        }
        for(int i =0; i<n; i++){
            b[i] = sc.nextInt();
        }
        X = x;
        Y = y;
        dp = new int[n+1][n+1][n+1];
        System.out.println(calculate(0,0,0,a,b));
    }
}
