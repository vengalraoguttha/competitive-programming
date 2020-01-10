package disjoint_set;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class JABO {

    private static int FIND(int[] sets, int i){ // including path compression
        if(sets[i] == -1)
            return i;
        sets[i] = FIND(sets,sets[i]);
        return sets[i];
    }
    private static void CREATE(int[] sets){
        Arrays.fill(sets,-1);
    }

    private static boolean UNION(int[] sets, int i, int j, int[] rank, int[] volts){ // union and rank approach
        int x = FIND(sets,i);
        int y = FIND(sets,j);
        if( x == y)
            return false;

        if(rank[x] == rank[y]){
            rank[x]++;
            sets[y] = x;
            volts[x] = volts[x] + volts[y];
        }else if( rank[x] > rank[y] ){
            sets[y] = x;
            volts[x] = volts[x] + volts[y];
        }else if(rank[x] < rank[y]){
            sets[x] = y;
            volts[y] = volts[x] + volts[y];
        }

        return true;
    }

    private static int getVal(char a, char b){
        int val = 0;
        if(a >= 97){
            val = (52*val)+a-71;
        }else{
            val = (52*val)+a-65;
        }

        if(b >= 97){
            val = (52*val)+b-71;
        }else{
            val = (52*val)+b-65;
        }

        return val;
    }

    public static void main(String[] args) throws IOException{
        Reader sc = new Reader();
        int n = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();
        int[] sets = new int[r*5*c];
        int[] rank = new int[r*5*c];
        int[] volt = new int[r*5*c];

        CREATE(sets);

        for(int i =1; i < 5*r; i++){
            if(i % 5 == 0)
                continue;
            for(int j = 0; j < c; j++){
                int currentPos = (i*c) + j;
                int parentPos = ((i-1)*c) + j;
                UNION(sets,currentPos,parentPos,rank,volt);
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++){
            String query = sc.readLine();
            char type = query.charAt(0);
            if(type == 'W'){
                UNION(sets,(getVal(query.charAt(1),query.charAt(2))-1+(c*(getVal(query.charAt(3),query.charAt(4))-1))),
                        (getVal(query.charAt(5),query.charAt(6))-1+(c*(getVal(query.charAt(7),query.charAt(8))-1))),
                        rank,volt);
            }else if(type == 'V'){
                volt[FIND(sets,(getVal(query.charAt(1),query.charAt(2))-1+(c*(getVal(query.charAt(3),query.charAt(4))-1))))]++;
            }else if(type == 'R'){
                volt[FIND(sets,(getVal(query.charAt(1),query.charAt(2))-1+(c*(getVal(query.charAt(3),query.charAt(4))-1))))]--;
            }else{
                if((volt[FIND(sets,(getVal(query.charAt(1),query.charAt(2))-1+(c*(getVal(query.charAt(3),query.charAt(4))-1))))]
                *volt[FIND(sets,(getVal(query.charAt(5),query.charAt(6))-1+(c*(getVal(query.charAt(7),query.charAt(8))-1))))] == 0)
                &&(volt[FIND(sets,(getVal(query.charAt(1),query.charAt(2))-1+(c*(getVal(query.charAt(3),query.charAt(4))-1))))]
                        +volt[FIND(sets,(getVal(query.charAt(5),query.charAt(6))-1+(c*(getVal(query.charAt(7),query.charAt(8))-1))))]>0)){
                    sb.append("ON").append("\n");
                }else{
                    sb.append("OFF").append("\n");
                }
            }
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
