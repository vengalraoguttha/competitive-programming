import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class GUESSNUM {
    public static void main(String[] args) throws IOException{
        Reader sc = new Reader();
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            long a = sc.nextLong();
            long m = sc.nextLong();
            int count;
            List<Long> set = new ArrayList<>();
            long x = (long) Math.ceil(Math.sqrt(m));
            for(long i = 1; i <= x; i++){
                if(m % i == 0 && (((m/i)-1)%a == 0) && (((m/i)-1)/a > 0)){
                    set.add((((m/i)-1)/a)*i);
                }

                if(m % i == 0){
                    long ll = m/i;

                    if(m % ll == 0 && (((m/ll)-1)%a == 0) && (((m/ll)-1)/a>0)){
                        set.add((((m/ll)-1)/a)*ll);
                    }
                }
            }
            count = set.size();
            sb.append(count).append("\n");
            if(count > 0){
                set.sort(null);
                for(Long integer : set){
                    sb.append(integer).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
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
