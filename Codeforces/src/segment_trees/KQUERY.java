package segment_trees;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class KQUERY {
    public static void main(String[] args) throws IOException{
        Reader sc = new Reader();
        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] modified = new int[n];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
            modified[i] = 1;
            if(map.containsKey(arr[i])){
                map.get(arr[i]).add(i);
            }else{
                List<Integer> l = new ArrayList<>();
                l.add(i);
                map.put(arr[i],l);
            }
        }

        Arrays.sort(arr);

        int q = sc.nextInt();

        List<Query> queries = new ArrayList<>();

        int ii = 0;
        while (q-- > 0){
            Query query = new Query();
            query.start = sc.nextInt();
            query.end = sc.nextInt();
            query.k = sc.nextInt();
            query.index = ii;
            ii++;
            queries.add(query);
        }

        queries.sort(new QueryComparator());

        int last = 0;

        SumInRangeST st = new SumInRangeST(modified);

        StringBuilder sb = new StringBuilder();

        for(Query query : queries){

            for(int i = last; i < arr.length; i++){
                if(query.k >= arr[i]){
                    for(Integer index : map.get(arr[i])){
                        st.update(index,0);
                    }
                }else{
                    last = Math.max(i-1,0);
                    break;
                }
            }

            query.result = st.query(query.start-1,query.end-1);
        }

        queries.sort(new QueryOriginalOrderComparator());

        for(Query query : queries){
            sb.append(query.result).append("\n");
        }

        System.out.println(sb);
    }

    static class Query{
        int start, end, k ,index;
        long result;
    }

    static class QueryComparator implements Comparator<Query>{

        @Override
        public int compare(Query o1, Query o2) {
            return o1.k - o2.k;
        }
    }

    static class QueryOriginalOrderComparator implements Comparator<Query>{

        @Override
        public int compare(Query o1, Query o2) {
            return o1.index - o2.index;
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
