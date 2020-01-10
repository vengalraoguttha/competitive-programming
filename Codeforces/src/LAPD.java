import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LAPD {

    static int max(int a, int b){
        if(a > b)
            return a;
        return b;
    }

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int t = sc.nextInt();

        long mod = 1000000007;
        List<Input> inputs = new ArrayList<>();
        int index = 0;
        while (t > 0){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            Input input = new Input();
            input.a = a;
            input.b = b;
            input.c = c;
            input.pos = index;
            index++;

            inputs.add(input);

            t--;
        }

        Collections.sort(inputs,new SortInput());

        int prevC = 1;
        long val = 0;
        int[] pos = new int[inputs.size()];

        for(int ind = 0; ind < inputs.size(); ind++){
            Input input = inputs.get(ind);
            val += calculate(input.a,input.b,input.c,mod,prevC);
            val %= mod;
            prevC = input.c;
            input.result = val;
            pos[input.pos] = ind;
        }

        System.out.println();

        for(int i = 0; i < pos.length; i ++){
            System.out.println(inputs.get(pos[i]).result);
        }
    }

    static long calculate(int a, int b, int c, long mod, int prevC){
        long val = 0;
        for(int i = prevC+1; i <= c; i++){
            if(i - 1 > b*b){
                val += ((c-i+1)*(a-1)*b)%mod;
                break;
            }
            for(int j = 1; j <= b; j++){
                double expa = 1 + (1.0*j*j)/(1.0*i -1);
                int ina = (int) Math.ceil(expa);
                if(ina == (int) expa){
                    ina++;
                }
                if(a - ina +1 > 0){
                    val += (a - ina + 1);
                    val %= mod;
                }
            }
        }
        return val;
    }
}
