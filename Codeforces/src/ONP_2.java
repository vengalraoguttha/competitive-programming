import java.util.Scanner;
import java.util.Stack;

public class ONP_2 {

    static StringBuilder getPrecedentOp(Stack<Character> stack,Character ch){
        StringBuilder sb = new StringBuilder();
        while (ch == '/' && !stack.isEmpty() && stack.peek() == '^'){
            sb.append(stack.pop());
        }

        while (ch == '*' && !stack.isEmpty() && (stack.peek() == '^' || stack.peek() == '/')){
            sb.append(stack.pop());
        }

        while (ch == '-' && !stack.isEmpty() && (stack.peek() == '^' || stack.peek() == '/' || stack.peek() == '*')){
            sb.append(stack.pop());
        }

        while (ch == '+' && !stack.isEmpty() && (stack.peek() == '^' || stack.peek() == '/' || stack.peek() == '*' || stack.peek() == '-')){
            sb.append(stack.pop());
        }

        while (ch == ')' && !stack.isEmpty() && (stack.peek() != '(')){
            sb.append(stack.pop());
        }

        return sb;
    }

    private static String getOnp(String exp){
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < exp.length(); i++){
            if(exp.charAt(i) == '('){
                stack.push(exp.charAt(i));
            }else if(exp.charAt(i) == '^' || exp.charAt(i) == '/' || exp.charAt(i) == '*' || exp.charAt(i) == '-' || exp.charAt(i) =='+'){
                sb.append(getPrecedentOp(stack,exp.charAt(i)));
                stack.push(exp.charAt(i));
            }else if(exp.charAt(i) == ')'){
                sb.append(getPrecedentOp(stack,exp.charAt(i)));
                stack.pop();
            }else{
                sb.append(exp.charAt(i));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-->0){
            String s = sc.next();
            System.out.println(getOnp(s));
        }
    }
}
