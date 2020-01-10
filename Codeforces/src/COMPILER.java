import java.util.Scanner;
import java.util.Stack;

public class COMPILER {

    static int calculate(String s){
        int count = 0;
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '<')
                stack.push(s.charAt(i));
            else{
                if(stack.size() == 0){
                    return count;
                }else if(stack.pop() == '<'){
                    if(stack.size() == 0){
                        count = i+1;
                    }
                }else{
                    return count;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0){
            String s = sc.next();
            System.out.println(calculate(s));
        }
    }
}
