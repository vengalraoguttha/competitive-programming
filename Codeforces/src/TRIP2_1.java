import java.util.*;
import java.io.*;
import java.text.*;
public class TRIP2_1{
    //SOLUTION BEGIN
    void pre() throws Exception{}
    void solve(int TC) throws Exception{
        int n = ni(), k = ni();
        int[] a = new int[n];
        for(int i = 0; i< n; i++)a[i] = ni();
        if(n == 1){
            pn("YES");
            if(a[0] == -1)a[0] = 1;
            pn(a[0]);return;
        }
        if(k == 2){
            int p = -1;
            while(p+1 < n && a[p+1] == -1)p++;
            if(p == n-1)a[p] = 1;
            else if(p>=0)a[p] = 3-a[p+1];
            while(--p>=0)a[p] = 3-a[p+1];

            for(int i = 1; i< n; i++){
                if(a[i] == -1)a[i] = 3-a[i-1];
            }
            boolean possible = true;
            for(int i = 1; i< n; i++)possible &= a[i] != a[i-1];
            if(possible){
                pn("YES");
                for(int i:a)p(i+" ");pn("");
            }else pn("NO");
            return;
        }

        pn("YES");
        for(int i = 0; i< n; i++){
            if(a[i] != -1)continue;
            if(i == 0){
                a[i] = 1;
                if(a[i+1] == 1)a[i]++;
            }else if(i == n-1){
                a[i] = 1;
                if(a[i-1] == 1)a[i]++;
            }else{
                a[i] = 1;
                if(a[i-1] == 1 || a[i+1] == 1){
                    a[i] = 2;
                    if(a[i-1] == 2 || a[i+1] == 2)a[i] = 3;
                }
            }
        }
        for(int i:a)p(i+" ");pn("");
    }
    //SOLUTION END
    void hold(boolean b)throws Exception{if(!b)throw new Exception("Hold right there, Sparky!");}
    DecimalFormat df = new DecimalFormat("0.00000000000");
    static boolean multipleTC = true;
    FastReader in;PrintWriter out;
    void run() throws Exception{
        in = new FastReader();
        out = new PrintWriter(System.out);
        //Solution Credits: Taranpreet Singh
        int T = (multipleTC)?ni():1;
        pre();for(int t = 1; t<= T; t++)solve(t);
        out.flush();
        out.close();
    }
    public static void main(String[] args) throws Exception{
        new TRIP2_1().run();
    }
    int bit(long n){return (n==0)?0:(1+bit(n&(n-1)));}
    void p(Object o){out.print(o);}
    void pn(Object o){out.println(o);}
    void pni(Object o){out.println(o);out.flush();}
    String n()throws Exception{return in.next();}
    String nln()throws Exception{return in.nextLine();}
    int ni()throws Exception{return Integer.parseInt(in.next());}
    long nl()throws Exception{return Long.parseLong(in.next());}
    double nd()throws Exception{return Double.parseDouble(in.next());}

    class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws Exception{
            br = new BufferedReader(new FileReader(s));
        }

        String next() throws Exception{
            while (st == null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch (IOException  e){
                    throw new Exception(e.toString());
                }
            }
            return st.nextToken();
        }

        String nextLine() throws Exception{
            String str = "";
            try{
                str = br.readLine();
            }catch (IOException e){
                throw new Exception(e.toString());
            }
            return str;
        }
    }
}