package tree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GCPC11J {

    /**
     * another approach to this problem is given at http://hmrockstar.blogspot.com/2016/11/gcpc11j-time-to-live-solution.html
     * 1) pick any vertex random and find longest from it let it be u.
     * 2) using u find longest path vertex (let v) then u-v is the diameter (need to understand the correctness of this logic)
     */

    private static int heightTree(List<Integer>[] graph, int pos,int parent){
        int height = 0;
        if(graph[pos] != null){
            for(Integer next : graph[pos]){
                if(next == parent)
                    continue;
                height = Math.max(height,heightTree(graph,next,pos));
            }
        }
        return height+1;
    }

    private static int diameter(List<Integer>[] graph,int pos,int parent, int[] height){ // calculate height in diameter only else will get TLE
        int diameter = 0;
        int[] arr = new int[3];
        if(graph[pos] != null){
            for(Integer next : graph[pos]){
                if(next == parent)
                    continue;
                int[] h = new int[1];
                diameter = Math.max(diameter,diameter(graph,next,pos,h));
                arr[0] = h[0];
                Arrays.sort(arr);
            }
        }
        height[0] = Math.max(arr[1],arr[2])+1;
        return Math.max(diameter,arr[1]+arr[2]+1);
    }

    public static void main(String[] args) throws IOException{
        Reader sc = new Reader();
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            List<Integer>[] graph = new ArrayList[n];
            for(int i = 0; i < n-1; i++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                if(graph[x] == null)
                    graph[x] = new ArrayList<>();
                if(graph[y] == null)
                    graph[y] = new ArrayList<>();
                graph[x].add(y);
                graph[y].add(x);
            }

            sb.append((int) Math.ceil(diameter(graph,0,-1,new int[]{0})/2)).append("\n");
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
