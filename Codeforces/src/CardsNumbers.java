import java.util.Scanner;

public class CardsNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[2*n];
        int valmap[] = new int[5001];
        int result[] = new int[2*n];
        int prevIndex[] = new int[5001];
        int k = 0;
        for(int i = 0; i < 2*n ; i++){
            arr[i] = sc.nextInt();
            if(valmap[arr[i]]%2 == 1){
                result[k] = prevIndex[arr[i]]+1;
                k++;
                result[k] = i+1;
                k++;
                valmap[arr[i]]++;
            }else{
                prevIndex[arr[i]] = i;
                valmap[arr[i]]++;
            }
        }
        for(int i = 1; i <= 5000; i++ ){
            if(valmap[i] %2 == 1){
                System.out.println("-1");
                return;
            }
        }
        for(int i = 0 ; i < 2*n ; i= i+2){
            System.out.println(result[i]+" "+result[i+1]);
        }
    }
}
