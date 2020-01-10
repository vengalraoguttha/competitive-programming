import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class STPAR {

    private static int min(int a, int b){
        if( a < b )
            return a;
        return b;
    }

    private static int max(int a, int b){
        if( a > b )
            return a;
        return b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true){
            int n = sc.nextInt();

            if( n == 0 )
                return;

            int[] arr = new int[n];

            Stack<Integer> stack = new Stack<>();

            for(int i = 0; i < n; i++){
                arr[i] = sc.nextInt();
            }

            stack.push(arr[0]);

            boolean flag = true;

            int out = 1;

            for(int i = 1; i < n; i++){
                while (stack.size() > 0 && stack.peek() < arr[i]){
                    int val = stack.pop();
                    if(out == val){
                        out++;
                    }else{
                        flag = false;
                        break;
                    }
                }
                if(!flag){
                    break;
                }
                stack.push(arr[i]);
            }

            if(!flag){
                System.out.println("no");
                continue;
            }

            while (stack.size() != 0){
                int val = stack.pop();
                if(out == val){
                    out++;
                }else{
                    flag = false;
                    break;
                }
            }

            if(!flag){
                System.out.println("no");
                continue;
            }

            System.out.println("yes");
        }
    }
}
