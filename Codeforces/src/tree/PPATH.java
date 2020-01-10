package tree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class PPATH {

    private static boolean isPrime(int x){
        for(int i = 2; i <= 100; i++){
            if( (x % i) == 0)
                return false;
        }
        return true;
    }

    static boolean is_met = false;

    static Queue<Data> queue;

    private static void bfs(int c, int end, boolean[] visited,int depth){
        visited[c] = true;

//        System.out.println(c+" "+depth);

        if(queue.isEmpty())
            return;

        Data data = queue.poll();

        if(data.val == end){
            System.out.println(data.depth);
            is_met = true;
        }

        c = data.val;
        depth = data.depth;

        if(!is_met){
            int val = Integer.MAX_VALUE;
            for(int i = 1; i <= 9; i++){
                int x = (i*1000) + (c%1000);
                if(!visited[x] && isPrime(x)){
                    visited[x] = true;
                    Data d = new Data();
                    d.val = x;
                    d.depth = depth+1;
                    queue.add(d);
                    if(x == end){
                        is_met = true;
                    }
                }
            }

            for(int i = 0; i <= 9; i++){
                int x = ((c/1000)*1000) + (i*100) + (c%100);
                if(!visited[x] && isPrime(x)){
                    visited[x] = true;
                    Data d = new Data();
                    d.val = x;
                    d.depth = depth+1;
                    queue.add(d);
                    if(x == end){
                        is_met = true;
                    }
                }
            }

            for(int i = 0; i <= 9; i++){
                int x = ((c/100)*100) + (i*10) + (c%10);
                if(!visited[x] && isPrime(x)){
                    visited[x] = true;
                    Data d = new Data();
                    d.val = x;
                    d.depth = depth+1;
                    queue.add(d);
                    if(x == end){
                        is_met = true;
                    }
                }
            }

            for(int i = 0; i <= 9; i++){
                int x = ((c/10)*10) + (i);
                if(!visited[x] && isPrime(x)){
                    visited[x] = true;
                    Data d = new Data();
                    d.val = x;
                    d.depth = depth+1;
                    queue.add(d);
                    if(x == end){
                        is_met = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        Reader sc = new Reader();
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int s = sc.nextInt();
            int e = sc.nextInt();
            boolean[] visited = new boolean[10000];

            queue = new LinkedList<>();

            Data d = new Data();
            d.val = s;
            d.depth = 0;
            is_met = false;

            queue.add(d);
            while (!queue.isEmpty())
                bfs(s,e,visited,0);
            if(!is_met){
                System.out.println("Impossible");
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

    static class Data{
        int val, depth;
    }
}
