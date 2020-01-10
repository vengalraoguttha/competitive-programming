package segment_trees;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class GSS2 {
    static class Query{
        int x,y;
    }

    static class QuerySorter implements Comparator<Query>{

        @Override
        public int compare(Query o1, Query o2) {
            return o1.y - o2.y;
        }
    }

    public static void main(String[] args) throws IOException {
        GSS1.Reader sc = new GSS1.Reader();
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i =0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int m = sc.nextInt();

        MaximumSumAllSetsNoRepeationST maximumSumAllSetsST = new MaximumSumAllSetsNoRepeationST(new int[n]);

        StringBuilder sb = new StringBuilder();

        Query[] queries = new Query[m];

        for(int i = 0; i < m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            queries[i] = new Query();
            queries[i].x = x-1;
            queries[i].y = y-1;
        }

        Arrays.sort(queries,new QuerySorter());

        int start = 0;

        for(Query query : queries){
            for(; start <= query.y; start++){
                maximumSumAllSetsST.update(start,arr[start]);
            }
            sb.append(maximumSumAllSetsST.query(query.x,query.y)).append("\n");
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
