package jan2019_long_codechef;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class CHEFPSA {
    static long[] dp = new long[100001];
    private static void fact(int n, long mod){
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = (i*dp[i-1])%mod;
        }
    }

    private static long pow(long x,long n,long mod){
        if(n == 0)
            return 1;
        if(n == 1)
            return x;
        long ans = pow(x,n/2,mod);
        if((n&1) == 0){
            return (ans*ans)%mod;
        }else{
            return (((ans*ans)%mod)*x)%mod;
        }
    }

    public static void main(String[] args) throws IOException{
        Reader sc = new Reader();
        int t = sc.nextInt();

        fact(100000,1000000007);

        StringBuilder sb = new StringBuilder();

        while (t-- > 0){
            int n = sc.nextInt();
            int x = 2*n;
            long[] arr = new long[x];
            long sum = 0;

            HashMap<Long,Integer> counters = new HashMap<>();

            for(int i = 0; i < x; i++){
                arr[i] = sc.nextLong();
                sum += arr[i];
                if(counters.containsKey(arr[i])){
                    counters.put(arr[i],counters.get(arr[i])+1);
                }else{
                    counters.put(arr[i],1);
                }
            }

            if(sum % (n+1) != 0){
                sb.append(0).append("\n");
                continue;
            }

            sum = sum/(n+1);

            if(n == 1){
                counters.remove(sum);
            }

            if(n == 1 && !counters.isEmpty()){
                sb.append(0).append("\n");
                continue;
            }else if(n == 1){
                sb.append(1).append("\n");
                continue;
            }

            // remove the 2 total sums

            if(!counters.containsKey(sum) || counters.get(sum) < 2){
                sb.append(0).append("\n");
                continue;
            }

            if(counters.get(sum) > 2){
                counters.put(sum,counters.get(sum)-2);
            }else{
                counters.remove(sum);
            }

            int p = 0;

            List<Integer> list = new ArrayList<>();

            boolean ff = false;
            for(int i = 0; i < x; i++){
                if(counters.isEmpty())
                    break;
                if(counters.get(arr[i]) != null && !counters.get(arr[i]).equals(counters.get(sum - arr[i]))){
                    ff = true;
                    sb.append(0).append("\n");
                    break;
                }
                if(counters.containsKey(arr[i])){
                    if(arr[i] != sum - arr[i]){
                        p += counters.get(arr[i]);
                    }
                    if(counters.get(arr[i]) > 1){
                        if(arr[i] != sum - arr[i])
                            list.add(counters.get(arr[i]));
                        else
                            list.add(counters.get(arr[i])/2);
                    }

                    counters.remove(arr[i]);
                    counters.remove(sum-arr[i]);
                }
            }

            if(ff)
                continue;

            long mod = 1000000007;

            long ans = pow(2,p,mod);

            ans = (ans*dp[n-1])%mod;

            for(Integer integer : list){
                ans = (ans * pow(dp[integer],mod-2,mod))%mod;
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }



    static class Data{
        long a, b;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Data data = (Data) o;
            return a == data.a &&
                    b == data.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    static class AscendingLongOrder implements Comparator<Data> {

        @Override
        public int compare(Data o1, Data o2) {
            if(o2.a-o1.a>0)
                return -1;
            else if(o2.a-o1.a==0)
                return 0;
            return 1;
        }
    }

    /** Faster input **/
    static class Reader {
        final private int BUFFER_SIZE = 1 << 32;private DataInputStream din;private byte[] buffer;private int bufferPointer, bytesRead;
        public Reader(){din=new DataInputStream(System.in);buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;}
        public Reader(String file_name)throws IOException {din=new DataInputStream(new FileInputStream(file_name));buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;}
        public String readLine()throws IOException{byte[] buf=new byte[1024*16];int cnt=0,c;
            while((c=read())!=-1){if(c=='\n')break;buf[cnt++]=(byte)c;}return new String(buf,0,cnt);}
        public int nextInt()throws IOException{int ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');
            if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;}
        public long nextLong()throws IOException{long ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');
            if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;}
        public double nextDouble()throws IOException{double ret=0,div=1;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c = read();do {ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');
            if(c=='.')while((c=read())>='0'&&c<='9')ret+=(c-'0')/(div*=10);if(neg)return -ret;return ret;}
        private void fillBuffer()throws IOException{bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);if(bytesRead==-1)buffer[0]=-1;}
        private byte read()throws IOException{if(bufferPointer==bytesRead)fillBuffer();return buffer[bufferPointer++];}
        public void close()throws IOException{if(din==null) return;din.close();}
    }
}
