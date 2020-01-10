import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BIT2C {
    static int max(int a, int b){
        if(a > b)
            return a;
        return b;
    }

    static int getStartInt(String s, int pos){
        int x  = 0;
        int val = 0;
        for(int i = pos - 1 ; i >= 0; i--){
            if( (s.charAt(i) == '|') || (s.charAt(i) == '&') | (s.charAt(i) == '^')){
                x = i;
                break;
            }
        }
        for(int i = x+1 ; i < pos -1; i++){
            val = val*10 + s.charAt(i) - 48;
        }
        return val;
    }

    static int getEndInt(String s, int pos){
        int val = 0;

        for(int i = pos+1 ; i < s.length() ; i++){
            if( (s.charAt(i) == '|') || (s.charAt(i) == '&') | (s.charAt(i) == '^')){
                if(s.charAt(i) == '|'){

                }
                break;
            }
            val = val*10 + s.charAt(i) - 48;
        }

        return val;
    }

//    static int calculate(String s){
//        boolean f = false;
//        for(int i = 0; i < s.length(); i++){
//            if( (s.charAt(i) == '|') || (s.charAt(i) == '&') | (s.charAt(i) == '^')){
//                f = true;
//
//            }
//        }
//
//        if(!f){
//            return Integer.valueOf(s);
//        }
//    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- >0){
            String s = sc.next();

            int operand = 0;
            List<BIT2CIn> list = new ArrayList<>();
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) != '&' || s.charAt(i) != '|' || s.charAt(i) != '^')
                    operand = operand*10 + s.charAt(i)-48;
                else{
                    BIT2CIn bit2CIn = new BIT2CIn();
                    bit2CIn.operand = operand;
                    operand = 0;
                    list.add(bit2CIn);
                    bit2CIn = new BIT2CIn();
                    bit2CIn.operator = s.charAt(i);
                    list.add(bit2CIn);
                }
            }

            List<Integer> operatoesVals = new ArrayList<>();
            for(int i = 0; i < list.size(); i++){
                BIT2CIn bit2CIn = list.get(i);
                if(bit2CIn.operator != null){
                    operatoesVals.add(i);
                }
            }
        }
    }
}
class BIT2CIn{
    Integer operand;
    Character operator;
}