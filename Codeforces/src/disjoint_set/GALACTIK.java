package disjoint_set;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class GALACTIK {
    private static void CREATE(int[] sets,int[] ranks){
        for(int i = 0; i < sets.length; i++){
            sets[i] = -1;
            ranks[i] = 0;
        }
    }

    private static int FIND(int[] sets, int i){
        if(sets[i] == -1)
            return i;
        return FIND(sets,sets[i]);
    }

    private static void UNIONS(int[] sets, int[] ranks, int i, int j, int[] tax){
        int x = FIND(sets,i);
        if(i != x)
            sets[i] = x; // path compression
        int y = FIND(sets,j);
        if(j != y)
            sets[j] = y; // path compression
        if(x == y)
            return;
        if(ranks[x] == ranks[y]){
            ranks[x]++;
            sets[y] = x;
            if((tax[x] < 0 || tax[x] > tax[y])&& tax[y]>=0){
                tax[x] = tax[y];
            }
        }else if(ranks[x] > ranks[y]){
            ranks[y]++;
            sets[x] = y;
            if((tax[y] < 0 || tax[y] > tax[x]) && tax[x]>=0){
                tax[y] = tax[x];
            }
        }else{
            ranks[x]++;
            sets[y] = x;
            if((tax[x] < 0 || tax[x] > tax[y]) && tax[y]>=0){
                tax[x] = tax[y];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] sets = new int[n];
        int[] ranks = new int[n];
        int[][] connections = new int[m][2];
        int[] tax = new int[n];

        for(int i = 0; i < m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            connections[i][0] = a;
            connections[i][1] = b;
        }

        for (int i =0 ; i < n; i++){
            tax[i] = sc.nextInt();
        }

        CREATE(sets,ranks);

        for(int i = 0; i < m; i++){
            UNIONS(sets,ranks,connections[i][0]-1,connections[i][1]-1,tax);
        }

        int sum = 0;
        int count = 0;
        int min = Integer.MAX_VALUE;
        boolean f = false;
        for(int i = 0; i < n; i++){
            if(sets[i] == -1 && tax[i] >= 0){
                count++;
                sum += tax[i];
                min = Math.min(min,tax[i]);
                if(f){
                    System.out.println(-1);
                    return;
                }
            }else if(sets[i] == -1 && tax[i] < 0){
                count++;
                f = true;
                if(count > 1){
                    System.out.println(-1);
                    return;
                }
            }
        }
        if(f && count>1){
            System.out.println(-1);
            return;
        }else if(f && count == 1){
            System.out.println(0);
            return;
        }
        System.out.println(sum+((count-2)*min));
    }
}

/** Faster input **/
class Reader {
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