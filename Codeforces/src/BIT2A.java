import java.util.Scanner;

public class BIT2A {
    public static int binarySearh(int[] arr, int x, int s, int e){
        if(s <= e){
            int m = s + (e-s)/2;
            if(arr[m] == x && e != s){
                int tmp =  binarySearh(arr,x,m+1,e);
                if(tmp != -1)
                    return tmp;
                return m;
            }else if(arr[m] == x){
                return m;
            }else if(arr[m] < x){
                return  binarySearh(arr,x,m+1,e);
            }else{
                return  binarySearh(arr,x,s,m-1);
            }

        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0){
            int n = sc.nextInt();
            int[] arr = new int[n];

            for(int i = 0; i < n; i++){
                arr[i] = sc.nextInt();
            }

            for(int i = 0; i < n; i++){
                System.out.print((n-1-binarySearh(arr,arr[i],0,n-1))+" ");
            }

            t--;
        }
    }
}
