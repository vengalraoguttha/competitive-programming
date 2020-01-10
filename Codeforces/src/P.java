import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class P {

    static long mod = 1000000007;
    static int allVisited(boolean[] v,boolean[] c){
        for(int i= 0; i < v.length; i++){
            if(!v[i]){
                return i;
            }
        }
        return -1;
    }

    static long calculate(ArrayList<Integer>[] graph,int x, boolean[] c, boolean[] v){

        int a = allVisited(v,c);
        if(a == -1){
            return 1;
        }else{
            x = a;
        }

        // paint x with some valid color;
        v[x] = true;

        long ans = 0;
        // case 1 : paint current vertex to white;
        boolean isVisted = false;
        if(graph[x] == null)
            return 2;
        for(Integer i : graph[x]){
            if(!v[i]){
                isVisted = true;
                ans += calculate(graph,i,c,v)%mod;
                ans %= mod;
                break;
            }
        }

        if(!isVisted){
            ans += calculate(graph,x,c,v);
        }

        // case 2 : paint current vertex to black
        boolean canBePaintedBlack = true;
        for(Integer i : graph[x]){
            if(c[i]){
                // can only paint x with white
                canBePaintedBlack = false;
            }
        }

        if(canBePaintedBlack){
            c[x] = true;
            for(Integer i : graph[x]){
                if(!v[i]){
                    ans += calculate(graph,i,c,v)%mod;
                    ans %= mod;
                    break;
                }
            }
            c[x] = false;
        }

        if(canBePaintedBlack && !isVisted){
            c[x] = true;
            ans += calculate(graph,x,c,v);
            c[x] = false;
        }

        v[x] = false;

//        System.out.println(x+" : "+ans+" : "+ Arrays.toString(c));

        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n-1; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            x--; y--;
            if(graph[x] == null)
                graph[x] = new ArrayList<>();
            if(graph[y] == null)
                graph[y] = new ArrayList<>();
            graph[x].add(y);
            graph[y].add(x);
        }

        boolean[] c = new boolean[n];
        boolean[] v = new boolean[n];

        System.out.println(calculate(graph,0,c,v));
    }
}
