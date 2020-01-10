import java.util.HashMap;
import java.util.Scanner;

public class LAPIN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while ( t > 0){
            String s = sc.next();
            int start = (s.length()&1)==1? (s.length()>>1)+1:s.length()>>1;
            HashMap<Character,Integer> map = new HashMap<>();
            for(int i = 0; i < s.length()>>1; i++){
                if(map.containsKey(s.charAt(i))){
                    map.put(s.charAt(i),map.get(s.charAt(i))+1);
                }else{
                    map.put(s.charAt(i),1);
                }
            }
            boolean flag = true;
            for(int i = start; i < s.length(); i++){
                if(map.containsKey(s.charAt(i)) && map.get(s.charAt(i))>1){
                    map.put(s.charAt(i),map.get(s.charAt(i))-1);
                }else if(map.containsKey(s.charAt(i)) && map.get(s.charAt(i))==1){
                    map.remove(s.charAt(i));
                }else{
                    flag = false;
                }
            }
            if(map.size() == 0 && flag){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
            t--;
        }
    }
}
