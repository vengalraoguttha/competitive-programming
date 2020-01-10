import java.util.Scanner;

public class GRID {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0){
            int n = sc.nextInt();
            char arr[][] = new char[n][n];
            int view[][] = new int[n][n];
            for(int i =0;i < n ;i++){
                String s = sc.next();
                for(int j =0; j < n; j++){
                    arr[i][j] = s.charAt(j);
                }
            }

            for(int i =n-1;i >= 0;i--){
                boolean flag = false;
                for(int j =n-1; j >= 0; j--){
                    if(arr[i][j] == '#'){
                        flag = true;
                    }
                    if(!flag){
                        view[i][j]++;
                    }
                }
            }

            for(int j =n-1;j >= 0 ;j--){
                boolean flag = false;
                for(int i =n-1; i >= 0; i--){
                    if(arr[i][j] == '#'){
                        flag = true;
                    }
                    if(!flag){
                        view[i][j]++;
                    }
                }
            }

            int count = 0;

            for(int i =0;i < n ;i++){
                for(int j =0; j < n; j++){
                    if(view[i][j] == 2){
                        count++;
                    }
                }
            }

            System.out.println(count);

            t--;
        }
    }
}
