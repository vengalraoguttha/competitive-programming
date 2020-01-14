package jan2019_long_codechef;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class CHFDORA {

    public static void main(String[] args) throws IOException{
        Reader sc = new Reader();
        int t = sc.nextInt();
        while (t-- > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[][] arr = new int[n][m];

            for(int i =0; i < n; i++){
                for(int j =0; j < m; j++){
                    arr[i][j] = sc.nextInt();
                }
            }

            long ans = 0;

            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    int min = Math.min(i,n-1-i);
                    min = Math.min(min,j);
                    min = Math.min(min,m-1-j);
                    for(int k = 1;k <= min; k++){
                        if(arr[i-k][j] == arr[i+k][j] && arr[i][j-k] == arr[i][j+k])
                            ans++;
                        else
                            k = min+1;
                    }
                }
            }

            ans += n*m;
            System.out.println(ans);
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
