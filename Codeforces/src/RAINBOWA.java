import java.util.Scanner;

public class RAINBOWA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0){
            int n = sc.nextInt();

            int[] arr = new int[n];

            for(int i  =0; i <n ;i++){
                arr[i] = sc.nextInt();
            }

            int start = 0, end = n-1;

            int element = 1;

            boolean flag = true;
            while ( start <= end){
                if(arr[start] != arr[end]){
                    flag = false;
                    break;
                }
                if(element == arr[start] && element == arr[end]){
                    start++;
                    end--;
                }else{
                    element++;
                    if(element == arr[start] && element == arr[end]){
                        start++;
                        end--;
                    }else {
                        flag = false;
                        break;
                    }

                }
            }

            if(flag && element == 7){
                System.out.println("yes");
            }else {
                System.out.println("no");
            }

            t--;
        }
    }
}
