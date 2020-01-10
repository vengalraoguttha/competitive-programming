import java.util.*;

public class ONP {

    private static void makeRPNForm(StringBuilder sb, LinkedList<Character> queue, Character previous){
        while (queue.size() > 0){
            Character ch = queue.removeFirst();
            if(ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^'){
                if(previous == null){
                    makeRPNForm(sb,queue,ch);
                }else if(previous == '+')
                    makeRPNForm(sb,queue,ch);
                else if(previous == '-' && ch != '+'){
                    makeRPNForm(sb,queue,ch);
                }else if(previous == '*' && ch != '+' && ch != '-'){
                    makeRPNForm(sb,queue,ch);
                }else if(previous == '/'&& ch != '+' && ch != '-' && ch != '*'){
                    makeRPNForm(sb,queue,ch);
                }else if(previous == '^'&& ch != '+' && ch != '-' && ch != '*' && ch != '/'){
                    makeRPNForm(sb,queue,ch);
                }else{
                    queue.push(ch);
                    return;
                }
                sb.append(ch);
            }else if(ch != ')' && ch != '('){
                sb.append(ch);
            }else if(ch == ')'){
                return;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0){
            String input = sc.next();

            LinkedList<Character> queue = new LinkedList<>();

            for(int i = 0; i < input.length(); i++){
                queue.add(input.charAt(i));
            }

            StringBuilder sb = new StringBuilder();

            makeRPNForm(sb,queue,null);

            System.out.println(sb.toString());
        }
    }
}
