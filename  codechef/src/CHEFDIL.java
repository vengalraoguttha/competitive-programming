import java.util.HashMap;
import java.util.Scanner;

public class CHEFDIL {

    private static int canWin(String s){
        int count = 0;
        int tmp = 0;
        for(int i=0; i < s.length() ; i++){
            if(s.charAt(i) == '1'){
                tmp++;
            }else{
                if(tmp > 0 && tmp %2 ==1){
                    count++;
                }
                tmp = 0;
            }
        }
        if(tmp > 0 && tmp %2 ==1){
            count++;
        }
        tmp = 0;
        return count%2;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int i =0 ; i < t; i++){
            String s = sc.next();
            System.out.println(canWin(s)==1?"WIN":"LOSE");
        }
    }
}
