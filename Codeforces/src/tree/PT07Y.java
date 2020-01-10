package tree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PT07Y {
    private static boolean dfs(List<Integer>[] graph,int i,int parent,boolean[] visited){
        visited[i] = true;
        boolean ans = true;
        if(graph[i] != null){
            for(Integer next : graph[i]){
                if(next != parent && visited[next])
                    return false;
                else if(next != parent){
                    ans = ans && dfs(graph,next,i,visited);
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) throws IOException{
        Reader sc = new Reader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < m; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            if(graph[u-1] == null)
                graph[u-1] = new ArrayList<>();
            if(graph[v-1] == null)
                graph[v-1] = new ArrayList<>();
            graph[u-1].add(v-1);
            graph[v-1].add(u-1);
        }

        boolean[] visited = new boolean[n];

        if(dfs(graph,0,-1,visited)){
            for(int i = 0; i < n; i++){
                if(!visited[i]){
                    System.out.println("NO");
                    return;
                }
            }
            System.out.println("YES");
        } else{
            System.out.println("NO");
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
