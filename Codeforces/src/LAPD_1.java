import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class LAPD_1 {
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int t = sc.nextInt();

        long mod = 1000000007;
        while (t > 0){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            long val = 0;
            for(int i = 1; i <= b ; i++){
                if(b >= a || b >= c){
                    for(int j = 1; j <= min(a,c); j++){
                        double expa = 1 + (1.0*i*i)/(1.0*j -1);
                        int ina = (int) Math.ceil(expa);
                        if(ina == (int) expa){
                            ina++;
                        }
                        if(max(a,c) - ina +1 > 0){
                            val += (max(a,c) - ina + 1);
                            val %= mod;
                        }
                    }
                }else{
                    for(int j = 1; j <= i; j++){
                        double expa = 1 + (1.0*i*i)/(1.0*j -1);
                        int ina = (int) Math.ceil(expa);
                        if(ina == (int) expa){
                            ina++;
                        }
                        if(a - ina +1 > 0){
                            val += (a - ina + 1);
                            val %= mod;
                        }
                        if(c - ina +1 > 0){
                            val += (c - ina + 1);
                            val %= mod;
                        }
                    }
                    val += ((c-i)%mod)*((a-i)%mod)-1;
                    val %= mod;
                }
            }
            System.out.println(val);
            t--;
        }
    }

    static int min(int a, int b){
        if( a < b)
            return a;
        return b;
    }

    static int max(int a, int b){
        if(a > b)
            return a;
        return b;
    }
}

class Input{
    int a,b,c;
    int pos;
    long result;
}

class SortInput implements Comparator<Input>{

    int min(int a, int b){
        if( a < b)
            return a;
        return b;
    }

    @Override
    public int compare(Input o1, Input o2) {
        if(o1.c != o2.c){
            return o1.c - o2.c;
        }else
            return o1.b - o2.b;
    }
}