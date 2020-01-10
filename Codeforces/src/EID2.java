import java.util.*;

public class EID2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int  t = sc.nextInt();
        while (t > 0){
            int a1 = sc.nextInt();
            int a2 = sc.nextInt();
            int a3 = sc.nextInt();
            int c1 = sc.nextInt();
            int c2 = sc.nextInt();
            int c3 = sc.nextInt();

            int arr[] = new int[3];

            int a[] = new int[3];
            int aa[] = new int[3];
            int cc[] = new int[3];
            boolean[] used = new boolean[3];

            arr[0] = a1;
            arr[1] = a2;
            arr[2] = a3;

            aa[0] = a1;
            aa[1] = a2;
            aa[2] = a3;

            cc[0] = c1;
            cc[1] = c2;
            cc[2] = c3;

            Arrays.sort(arr);
            for(int i = 0; i < arr.length;i++){
                if(!used[0] && arr[i] == a1){
                    used[0] = true;
                    a[i] = 1;
                }else if(!used[1] && arr[i] == a2){
                    used[1] = true;
                    a[i] = 2;
                }else if(!used[2] && arr[i] == a3){
                    used[2] = true;
                    a[i] = 3;
                }
            }

            boolean f = true;
            for(int i  =0; i < 2; i++){
                if( aa[a[i]-1] < aa[a[i+1]-1] && !( cc[a[i]-1] < cc[a[i+1]-1])){
                    f = false;
                    System.out.println("NOT FAIR");
                    break;
                }

                if( aa[a[i]-1] == aa[a[i+1]-1] && !( cc[a[i]-1] == cc[a[i+1]-1])){
                    f = false;
                    System.out.println("NOT FAIR");
                    break;
                }
            }

            if(f){
                System.out.println("FAIR");
            }
            t--;
        }
    }
}
