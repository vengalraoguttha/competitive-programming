import java.util.Scanner;

public class FRGTNLNG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            String[] words = new String[n];
            for(int i =0; i <n ;i++){
                words[i] = sc.next();
            }
            boolean[] contains = new boolean[n];
            for (int i = 0; i < k ; i++){
                int l = sc.nextInt();
                for(int j = 0; j < l; j++){
                    String w = sc.next();
                    for(int x = 0; x < n; x++){
                        if(words[x].equals(w)){
                            contains[x] = true;
                        }
                    }
                }
            }
            for(int i =0; i < n ;i++){
                if(contains[i])
                    System.out.print("YES ");
                else
                    System.out.print("NO ");
            }
            System.out.println("");
            t--;
        }
    }
}
