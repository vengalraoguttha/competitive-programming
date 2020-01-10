import java.io.IOException;

public class LPDA_2 {
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int t = sc.nextInt();

        long mod = 1000000007;
        while (t > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            int count = 0;
            for(int i = 2 ; i <= min(a,b); i++){
                for(int j = 2; j <= min(b,c); j++){
                    count += min(b,(i-1)*(j-1)-1);
                }
            }

            count += (a - min(a,b))*(c - min(b,c));

            System.out.println(count);

            t--;
        }
    }

    static int min(int a, int b){
        if(a < b)
            return a;
        return b;
    }
}
