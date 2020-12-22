package feb2020_long_codechef;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class NOCHANGE {
    public static void main(String[] args) throws IOException{
        Reader sc = new Reader();
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0){
            int n = sc.nextInt();
            long p = sc.nextLong();
            long gen = -1;
            int genInd = -1;
            long previous = -1;
            long present = -1, past = -1;
            int pIndex = -1;
            for(int i = 0; i < n; i++){
                long val = sc.nextLong();
                if(p % val != 0){
                    gen = val;
                    genInd = i;
                }
                if(i > 0 && previous != -1 && val % previous != 0){
                    present = val;
                    past = previous;
                    pIndex = i;
                }
                previous = val;
            }
            if(gen == -1 && present == -1 && past == -1)
                sb.append("NO\n");
            else if(gen != -1){
                sb.append("YES ");
                for(int j = 0; j < genInd; j++){
                    sb.append("0 ");
                }
                sb.append((p/gen)+1).append(" ");
                for(int j = genInd+1; j < n; j++){
                    sb.append("0 ");
                }
                sb.append("\n");
            }
            else{
                sb.append("YES ");
                for(int j = 0; j < pIndex-1; j++){
                    sb.append("0 ");
                }
                sb.append((p/past)-1).append(" ");
                sb.append(1).append(" ");
                for(int j = pIndex+1; j < n; j++){
                    sb.append("0 ");
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
