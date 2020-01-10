import java.math.BigInteger;
import java.util.Scanner;

public class ENCODING {

    static long[][][] dp = new long[100000][10][2];

    private static long pow(long x, long n, long mod){
        if(x == 0 || x == 1){
            return x;
        }
        if( n == 0){
            return 1;
        }
        if(n ==  1){
            return x%mod;
        }
        if(n % 2 == 0){
            return pow(((x%mod)*(x%mod))%mod,n/2,mod);
        }else{
            return ((x%mod)*(pow(((x%mod)*(x%mod))%mod,n/2,mod)%mod))%mod;
        }
    }

    private static long calculate(int size, String s){
        long value = 0;
        for(int i =0 ; i < size; i++){
            if( (i == 0)|| (s.charAt(i-1) != s.charAt(i))){
                value += (Integer.valueOf(s.charAt(i)+"")*pow(10,size-1-i,1000000007));
                value = value % 1000000007;
            }
        }
        return value;
    }

    private static long encode(int size, String s, int pos, int isLess,int prevNum, long mod){
        int value = 0;
        int number = 9;
        if(isLess == 0){
            number = Integer.valueOf(s.charAt(pos)+"");
        }
        if(prevNum != -1 && dp[pos][prevNum][isLess] != 0){
            return dp[pos][prevNum][isLess];
        }
        for(int i = 0; i <= number ; i++){
            int tmp = isLess;
            if(i < number){
                tmp = 1;
            }
            if(pos < size -1){
                if(prevNum != i){
                    int a = tmp == 1?9:(Integer.valueOf(s.charAt(pos+1)+""));
                    value += ((a+1)*(i*pow(10,size-1-pos,mod))%mod + encode(size,s,pos+1,tmp,i,mod))%mod;
                }else{
                    value += encode(size,s,pos+1,tmp,i,mod) % mod;
                }
                value %= mod;
            }else if( pos == size - 1){
                if(prevNum != i){
                    value += (i*pow(10,size-1-pos,mod));
                }
                value %= mod;
            }
        }
        if(prevNum != -1)
            dp[pos][prevNum][isLess] = value;
        return value;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        long mod = 1000000007;
        for(int x =0 ; x < t; x++){
            int nL = sc.nextInt();
            String s1 = sc.next();
            int nR = sc.nextInt();
            String s2 = sc.next();
            dp = new long[100000][10][2];
            long a = encode(nR,s2,0,0,-1,mod);
            dp = new long[100000][10][2];
            long b = encode(nL,s1,0,0,-1,mod);
            System.out.println(a-b + calculate(nL,s1));
        }
    }
}
