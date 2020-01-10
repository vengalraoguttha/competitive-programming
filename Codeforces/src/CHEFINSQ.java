import java.io.IOException;

public class CHEFINSQ {

    static int min(int a, int b)
    {
        return (a < b) ? a : b;
    }

    static long binomialCoeff(int n, int k)
    {
        long[][] C = new long[n + 1][k + 1];
        int i, j;

        // Caculate value of Binomial Coefficient
        // in bottom up manner
        for (i = 0; i <= n; i++)
        {
            for (j = 0; j <= min(i, k); j++)
            {
                // Base Cases
                if (j == 0 || j == i)
                    C[i][j] = 1;

                    // Calculate value using previously
                    // stored values
                else
                    C[i][j] = C[i - 1][j - 1] +
                            C[i - 1][j];
            }
        }

        return C[n][k];
    }

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int t = sc.nextInt();
        while (t > 0){

            int n = sc.nextInt();
            int k = sc.nextInt();

            int[] arr = new int[n];

            int[] counts = new int[101];

            for(int i = 0; i < n ; i++){
                arr[i] = sc.nextInt();
                counts[arr[i]]++;
            }

            for(int i = 1; i < 101; i++){
                if(k <= counts[i]){
                    System.out.println(binomialCoeff(counts[i],k));
                    break;
                }
                k -= counts[i];
            }

            t--;
        }
    }
}
