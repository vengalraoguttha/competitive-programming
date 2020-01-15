package Weekly_Contest_171;

import java.util.Scanner;

public class Q_1320 {
    private static int[][][] dp;

    private static int calculate(int pos, int prevL,int prevR,String word){
        if(pos < 0){
            return 0;
        }
        if(dp[prevL][prevR][pos] != 0)
            return dp[prevL][prevR][pos];
        int val = word.charAt(pos) - 65;
        int valX = val / 6;
        int valY = val % 6;

        dp[prevL][prevR][pos] = Math.min((prevL == 26 ? 0 : Math.abs(valX-prevL/6))+(prevL == 26 ? 0 : Math.abs(valY-prevL%6))+calculate(pos-1,val,prevR,word),
                (prevR == 26 ? 0 : Math.abs(valX-prevR/6))+(prevR == 26 ? 0 : Math.abs(valY-prevR%6))+calculate(pos-1,prevL,val,word));

        return dp[prevL][prevR][pos];
    }
    public static int minimumDistance(String word) {
        dp = new int[27][27][word.length()];
        return calculate(word.length()-1,26,26,word);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(minimumDistance(sc.next()));
    }
}
