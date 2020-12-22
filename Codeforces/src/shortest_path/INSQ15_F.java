package shortest_path;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class INSQ15_F {

    public static void main(String[] args) throws IOException{
        Reader sc = new Reader();
        int n = sc.nextInt();
        int r = sc.nextInt();
        int[] h = new int[n];
        int[] c = new int[n];
        for(int i = 0; i < n; i++){
            h[i] = sc.nextInt();
        }
        for(int i = 0; i < n; i++){
            c[i] = sc.nextInt();
        }
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < r; i++){
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            if(graph[x] == null){
                graph[x] = new ArrayList<>();
            }
            if(graph[y] == null){
                graph[y] = new ArrayList<>();
            }
            graph[x].add(y);
            graph[y].add(x);
        }

        PriorityQueue<Model> pq = new PriorityQueue<>(new AscendingOrder());
        int[] f = new int[n];
        boolean[] visited = new boolean[n];
        for(int i = 1; i < n; i++){
            f[i] = Integer.MAX_VALUE;
        }
        pq.add(new Model(0,0));
        while (!pq.isEmpty()){
            Model x = pq.poll();
            visited[x.to] = true;
            if(f[x.to] > x.value)
                f[x.to] = x.value;
            for(Integer next : graph[x.to]){
                if(visited[next]) continue;
                if( (x.direction > 0) && (h[next] - h[x.to] >= 0)){
                    pq.add(new Model(next,x.value,1));
                }else if( (x.direction < 0) && (h[next] - h[x.to] <= 0)){
                    pq.add(new Model(next,x.value,-1));
                }else{
                    if((h[next] - h[x.to]) > 0)
                        pq.add(new Model(next,x.value+Math.abs(c[x.to]),1));
                    else if((h[next] - h[x.to]) < 0)
                        pq.add(new Model(next,x.value+Math.abs(c[x.to]),1));
                    else if(x.value == 0)
                        pq.add(new Model(next,x.value+c[0],x.direction));
                    else
                        pq.add(new Model(next,x.value,x.direction));
                }
            }
        }
        if(f[n-1] == Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        System.out.println(f[n-1]);
    }

    static class Model{
        int to,value;
        int direction; // 0 -> not started , +ve -> up, -ve down
        Model(int to,int value){
            this.to = to;
            this.value = value;
        }

        Model(int to,int value,int direction){
            this.to = to;
            this.value = value;
            this.direction = direction;
        }
    }

    static class AscendingOrder implements Comparator<Model>{
        @Override
        public int compare(Model o1, Model o2) {
            return o1.value-o2.value;
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
