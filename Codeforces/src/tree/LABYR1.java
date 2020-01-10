package tree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LABYR1 {

    static int node;
    static int len;

    private static void dfs(List<Integer>[] graph, int i,boolean[] visited,int[] depth){
        visited[i] = true;
        depth[0]++;
        if(len < depth[0]){
            len = depth[0];
            node = i;
        }
        if(graph[i] != null){
            for(Integer next : graph[i]){
                if(!visited[next]){
                    int[] d = new int[1];
                    d[0] = depth[0];
                    dfs(graph,next,visited,d);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        Reader sc = new Reader();
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int c = sc.nextInt();
            int r = sc.nextInt();
            String[] grid = new String[r];
            for(int i = 0; i < r; i++){
                grid[i] = sc.readLine();
            }
            List<Integer>[] graph = new ArrayList[c*r];
            List<Integer> vals = new ArrayList<>();
            for(int i = 1; i < r; i++){
                for(int j = 1; j < c; j++){
                    if(grid[i].charAt(j) == '.'){
                        vals.add(i*c+j);
                    }
                    if(grid[i].charAt(j) == '.' && grid[i].charAt(j) == grid[i].charAt(j-1)){
                        if(graph[i*c+j] == null)
                            graph[i*c+j] = new ArrayList<>();
                        if(graph[i*c+j-1] == null)
                            graph[i*c+j-1] = new ArrayList<>();
                        graph[i*c+j].add(i*c+j-1);
                        graph[i*c+j-1].add(i*c+j);
                    }
                    if(grid[i].charAt(j) == '.' && grid[i].charAt(j) == grid[i-1].charAt(j)){
                        if(graph[(i-1)*c+j] == null)
                            graph[(i-1)*c+j] = new ArrayList<>();
                        if(graph[i*c+j] == null)
                            graph[i*c+j] = new ArrayList<>();
                        graph[(i-1)*c+j].add(i*c+j);
                        graph[i*c+j].add((i-1)*c+j);
                    }
                }
            }

            boolean[] visited = new boolean[c*r];

            int val = 0;
            for(Integer i : vals){
                if(!visited[i]){
                    boolean[] tmp = new boolean[c*r];
                    int[] depth = new int[1];
                    node = i;
                    len = 0;
                    dfs(graph,i,tmp,depth);
                    depth = new int[1];
                    len = 0;
                    dfs(graph,node,visited,depth);
                    val = Math.max(val,len-1);
                }
            }

            sb.append("Maximum rope length is ").append(val).append(".\n");
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
