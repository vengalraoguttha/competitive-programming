package segment_trees;

import java.util.Scanner;

public class SerejaAndBrackets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        MaximumCorrectBracketSubsequenceLengthST st = new MaximumCorrectBracketSubsequenceLengthST(s.toCharArray());
        int q = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (q-- > 0){
            int l = sc.nextInt();
            int r = sc.nextInt();
            sb.append(2*st.query(l-1,r-1)).append("\n");
        }
        System.out.println(sb.toString());
    }
}
