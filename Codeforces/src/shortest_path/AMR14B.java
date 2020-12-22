package shortest_path;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AMR14B {
    public static void main(String[] args) throws IOException{
        // calculate the value of minimum spanning tree
        // calculate the value of shortest path tree
        // and compare if they are equal.

        Reader sc = new Reader();
        int t = sc.nextInt();
        while (t-- > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();

            ArrayList<Model>[] graph = new ArrayList[n];
            for(int i = 0; i < m; i++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                long dist = sc.nextLong();

                if(graph[x] == null){
                    graph[x] = new ArrayList<>();
                }

                if(graph[y] == null){
                    graph[y] = new ArrayList<>();
                }

                Model m1 = new Model();
                m1.to = y;
                m1.from = x;
                m1.distance = dist;

                graph[x].add(m1);

                Model m2 = new Model();
                m2.to = x;
                m2.from = y;
                m2.distance = dist;

                graph[y].add(m2);
            }
            // find a MST cost;

            boolean[] visited = new boolean[n];
            int key = 0;
            long total = 0;
            PriorityQueue<Model> remaining = new PriorityQueue<>(new AscendingOrder());
            boolean flag = false;
            for(int i = 0; i < n-1; i++){
                visited[key] = true;
                if(graph[key] != null){
                    for(Model model : graph[key]){
                        if(!visited[model.to]){
                            remaining.add(model);
                        }
                    }
                }
                Long val = Long.MAX_VALUE;
                while (remaining.peek() != null && visited[remaining.peek().to]){
                    remaining.poll();
                }
                if(remaining.peek() != null){
                    Model mm = remaining.poll();
                    val = mm.distance;
                    key = mm.to;
                }
                if(val == Long.MAX_VALUE){
                    flag = true;
                    break;
                }
                total += val;
            }

            if(flag){
                System.out.println("NO");
                continue;
            }

            visited = new boolean[n];
            long[] distances = new long[n];
            for(int i = 1; i < n; i++){
                distances[i] = Long.MAX_VALUE;
            }
            remaining.clear();

            key = 0;

            long shortestCost = 0;
            for(int i = 0;i < n-1; i++){
                visited[key] = true;
                if(graph[key] != null){
                    for(Model model : graph[key]){
                        if(!visited[model.to]){
                            Model model1 = new Model();
                            model1.to = model.to;
                            model1.from = model.from;
                            model1.distance = distances[model.to];
                            if( distances[key] != Long.MAX_VALUE  && model.distance + distances[key] <= distances[model.to]){
                                model1.distance = model.distance + distances[key];
                                distances[model.to] = model.distance + distances[key];
                            }else
                                continue;

                            model1.d = model.distance;
                            remaining.add(model1);
                        }
                    }
                }
                long cost = Long.MAX_VALUE;

                while (remaining.peek() != null && visited[remaining.peek().to]){
                    remaining.poll();
                }

                if(remaining.peek() != null){
                    Model mm = remaining.poll();
                    cost = mm.d;
                    key = mm.to;
                }

                if(cost == Long.MAX_VALUE){
                    flag = true;
                    break;
                }
                shortestCost += cost;
            }

            if(flag){
                System.out.println("NO");
                continue;
            }

            if(shortestCost == total){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }

    static class Model{
        int from,to;
        long distance;
        long d;
    }

    static class AscendingOrder implements Comparator<Model>{

        @Override
        public int compare(Model o1, Model o2) {
            if(o1.distance > o2.distance){
                return 1;
            }else if(o1.distance < o2.distance){
                return -1;
            }
            return 0;
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
