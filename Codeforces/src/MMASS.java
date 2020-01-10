import java.util.Scanner;
import java.util.Stack;

public class MMASS {
    static int getVal(Character ch){
        if(ch == 'C'){
            return 12;
        }else if( ch == 'H'){
            return 1;
        }else{
            return 16;
        }
    }
    static int calculateMass(String s){
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(String.valueOf(s.charAt(i)));
            }else if(s.charAt(i) == ')'){
                int val = 0;
                while (!stack.peek().equals("(")){
                    val += Integer.valueOf(stack.pop());
                }
                stack.pop();
                stack.push(String.valueOf(val));
            }else if(s.charAt(i) == 'H' || s.charAt(i) == 'C' || s.charAt(i) == 'O'){
                stack.push(String.valueOf(getVal(s.charAt(i))));
            }else{
                stack.push(String.valueOf(Integer.valueOf(stack.pop())*Integer.parseInt(String.valueOf(s.charAt(i)))));
            }
        }

        int val = 0;

        while (!stack.isEmpty()){
            val += Integer.valueOf(stack.pop());
        }

        return val;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(calculateMass(sc.next()));
    }
}
