import java.io.IOException;
import java.util.Scanner;

public class Atcoder_dp_f {

    static StringBuilder[][] dp;

    static StringBuilder  max(StringBuilder a, StringBuilder b){
        if(a != null && b != null)
            if(a.length() >= b.length())
                return a;
            else
                return b;
        else{
            if( a == null )
                return b;
            else
                return a;
        }
    }

    static StringBuilder calculate(String s, String t, int i, int j){
        StringBuilder sb = new StringBuilder();
        if(i < 0 || j < 0){
            return sb.append("");
        }else {
            if(dp[i][j] != null){
                return dp[i][j];
            }else if(s.charAt(i)==t.charAt(j)){
                dp[i][j] = new StringBuilder(calculate(s,t,i-1,j-1).append(s.charAt(i)));
                return dp[i][j];
            }
            else {
                dp[i][j] = new StringBuilder(max(calculate(s,t,i,j-1),calculate(s,t,i-1,j)));
                return dp[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        String s = sc.readLine();
        String t = sc.readLine();
        dp = new StringBuilder[s.length()][t.length()];

        StringBuilder ans = calculate(s,t,s.length()-1,t.length()-1);
        System.out.println(ans);
    }
}
