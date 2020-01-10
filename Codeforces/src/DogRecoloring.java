import java.util.HashMap;
import java.util.Scanner;

public class DogRecoloring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        if(s.length() > 26){
            System.out.println("Yes");
            return;
        }
        HashMap<Character,Integer> map = new HashMap<>();
        int count = 0;
        for(int i = 0; i< n; i++){
            if(!map.containsKey(s.charAt(i))){
                count++;
                map.put(s.charAt(i),0);
            }
        }

        if(n != count || n == 1){
            System.out.println("Yes");
            return;
        }

        System.out.println("No");
    }
}
