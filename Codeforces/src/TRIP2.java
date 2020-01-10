import java.io.IOException;
import java.util.Scanner;

public class TRIP2 {

    static void solve(int n, int k, int[] arr){
        int[] ans = new int[n];
        if( n == 1){
            System.out.println("YES");
            if(arr[0] == -1){
                ans[0] = 1;
                System.out.println("1");
            }else{
                ans[0] = arr[0];
                System.out.println(ans[0]);
            }
        }else{
            if(k == 2){
                for(int i = 0; i < n ; i++){
                    ans[i] = arr[i];
                    if(ans[i] == -1 && i % 2==0){
                        ans[i] = 2;
                    }else if(ans[i] == -1){
                        ans[i] = 1;
                    }
                }

                boolean flag = false;
                for(int i = 1; i < n ; i++){
                    if( ans[i] == ans[i-1]){
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    System.out.println("YES");
                    System.out.print(ans[0]+" ");
                    for(int i = 1; i < n ; i++){
                        System.out.print(ans[i]+" ");
                    }
                    System.out.println("");
                    return;
                }

                for(int i = 0; i < n ; i++){
                    ans[i] = arr[i];
                    if(ans[i] == -1 && i % 2==0){
                        ans[i] = 1;
                    }else if(ans[i] == -1){
                        ans[i] = 2;
                    }
                }

                flag = false;
                for(int i = 1; i < n ; i++){
                    if( ans[i] == ans[i-1]){
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    System.out.println("YES");
                    System.out.print(ans[0]+" ");
                    for(int i = 1; i < n ; i++){
                        System.out.print(ans[i]+" ");
                    }
                    System.out.println("");
                    return;
                }else{
                    System.out.println("NO");
                    return;
                }
            }else{
                for(int i = 0; i < n ; i++){
                    ans[i] = arr[i];
                    if(ans[i] == -1){
                        int fit = bestFit(i,ans,arr);
                        if( fit == 0){
                            System.out.println("NO");
                            return;
                        }
                        ans[i] = fit;
                    }
                }

                System.out.println("YES");
                for(int i = 0; i < n ; i++){
                    System.out.print(ans[i]+" ");
                }
                System.out.println("");
                return;
            }
        }
    }

    static int bestFit(int i, int[] ans,int[] arr){
        if( i == 0){
            if(arr[i+1] == 1){
                return 2;
            }else{
                return 1;
            }
        }else if(i == ans.length -1){
            if(ans[i-1] == 1){
                return 2;
            }else{
                return 1;
            }
        }else{
            for(int ind = 1; ind <= 3; ind++){
                if(ind != ans[i-1] && ind != arr[i+1]){
                    return ind;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int t = sc.nextInt();
        while (t > 0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = sc.nextInt();
            }
            solve(n,k,arr);
            t--;
        }
    }
}
