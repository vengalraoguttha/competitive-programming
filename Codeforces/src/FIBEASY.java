import java.io.IOException;

public class FIBEASY {

    static long fib(long n)
    {
        long F[][] = new long[][]{{1,1},{1,0}};
        if (n == 0)
            return 0;
        power(F, n-1);

        return F[0][0];
    }

    static void multiply(long F[][],  long M[][])
    {
        long x =  F[0][0]*M[0][0] + F[0][1]*M[1][0];
        long y =  F[0][0]*M[0][1] + F[0][1]*M[1][1];
        long z =  F[1][0]*M[0][0] + F[1][1]*M[1][0];
        long w =  F[1][0]*M[0][1] + F[1][1]*M[1][1];

        F[0][0] = x%10;
        F[0][1] = y%10;
        F[1][0] = z%10;
        F[1][1] = w%10;
    }

    /* Optimized version of power() in method 4 */
    static void power(long F[][], long n)
    {
        if( n == 0 || n == 1)
            return;
        long M[][] = new long[][]{{1,1},{1,0}};

        power(F, n/2);
        multiply(F, F);

        if (n%2 != 0)
            multiply(F, M);
    }

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int t = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        while (t > 0){
            long n = sc.nextLong();

            sb.append(fib((highestPowerof2(n)%60)-1)%10).append("\n");
            t--;
        }
        System.out.println(sb.toString());
    }

    static long highestPowerof2(long n)
    {

        return Long.highestOneBit(n);
//        long p = (long) (Math.log(n) /
//                Math.log(2));
//        return (long) Math.pow(2, p);
    }
}
