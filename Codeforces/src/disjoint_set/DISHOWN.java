package disjoint_set;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class DISHOWN {
    private static int FIND(int[] sets, int i){ // including path compression
        if(sets[i] == -1)
            return i;
        sets[i] = FIND(sets,sets[i]);
        return sets[i];
    }
    private static void CREATE(int[] sets){
        Arrays.fill(sets,-1);
    }

    private static boolean UNION(int[] sets, int i, int j, int[] s){ // union and rank approach
        int x = FIND(sets,i);
        int y = FIND(sets,j);
        if( x == y)
            return false;

        if( s[x] > s[y] ){
            sets[y] = x;
        }else if(s[x] < s[y]){
            sets[x] = y;
        }

        return true;
    }
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int t = sc.nextInt();
        while (t-- > 0){
            int n = sc.nextInt();
            int[] s = new int[n];
            for(int i =0; i < n; i++){
                s[i] = sc.nextInt();
            }
            int q = sc.nextInt();

            int[] sets = new int[n];

            CREATE(sets);

            for(int i =0; i < q; i++){
                int type = sc.nextInt();
                if(type == 0){
                    if(!UNION(sets,sc.nextInt()-1,sc.nextInt()-1,s))
                        System.out.println("Invalid query!");
                }else{
                    System.out.println(FIND(sets,sc.nextInt()-1)+1);
                }
            }
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