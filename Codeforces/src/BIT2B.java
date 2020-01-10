import java.util.Scanner;

public class BIT2B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = sc.nextInt();
            }
            int x = sc.nextInt();
            long bBox = 0, aBox = 0;
            if(n == 1){
                aBox = 1;
            }else{
                int prevPos = -1;
                long prevPending = 0;
                for(int i = n-1; i >=0 ; i--){
                    long b = arr[i];
                    long a = b*x;
                    if(aBox + bBox == n)
                        break;
                    if(prevPos+1 > i){
                        break;
                    }
                    if(i == prevPos+1){
                        if(aBox >= bBox)
                            aBox++;
                        else
                            bBox++;
                        break;
                    }
                    else {
                        bBox++;
                    }
                    if(prevPending != 0 && prevPending > a){
                        prevPending -= a;
                    }else if (prevPending != 0 && a >= prevPending){
                        a -= prevPending;
                        prevPending = 0;
                        aBox++;
                        prevPos++;
                        int tmp = prevPos;
                        if(prevPos>=i)
                            break;
                        for(int k = prevPos+1; k < i; k++){
                            if(a == 0)
                                break;
                            if( a >= arr[k]){
                                a -= arr[k];
                                aBox++;
                                tmp = k;
                            }else{
                                prevPending = arr[k] - a;
                                a = 0;
                                break;
                            }
                        }
                        prevPos = tmp;
                    }else{
                        int tmp = prevPos;
                        if(prevPos>=i)
                            break;
                        for(int k = prevPos+1; k < i; k++){
                            if(a == 0)
                                break;
                            if( a >= arr[k]){
                                a -= arr[k];
                                aBox++;
                                tmp = k;
                            }else{
                                prevPending = arr[k] - a;
                                a = 0;
                                break;
                            }
                        }
                        prevPos = tmp;
                    }
                }
            }
            System.out.println(aBox +" "+ bBox);
            t--;
        }
    }
}
