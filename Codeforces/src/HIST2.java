import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class HIST2 {

    static int dp[][];
    static int times[][];

    static boolean isBitSet(int mask, int i){
        return (mask & (1 << i)) > 0;
    }

    static int countSetBits(int mask){
        int count = 0;
        while (mask > 0){
            count++;
            mask = (mask&(mask-1));
        }
        return count;
    }

    static int setBit(int mask, int i){
        return mask | (1<<i);
    }

    static int calculate(int arr[], int mask , int current){
        if(dp[mask][current] != -1){
            return dp[mask][current];
        }else{
            for(int i =0; i < arr.length ; i++){
                if(!isBitSet(mask,i)){
                    if((setBit(mask,i)) == ((1<<arr.length)-1)){
                        times[mask][current] = 1;
                        dp[mask][current] = Math.abs(arr[current]-arr[i])+arr[i];
                        return dp[mask][current];
                    }else{
                        int val = calculate(arr,setBit(mask,i),i)+Math.abs(arr[current]-arr[i]);
                        if( val > dp[mask][current]){
                            dp[mask][current] = val;
                            times[mask][current] = 0;
                        }
                        if( val == dp[mask][current]){
                            times[mask][current] += times[setBit(mask,i)][i];
                        }
                    }
                }
            }
            return dp[mask][current];
        }
    }

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        StringBuilder sb = new StringBuilder();
        while (true){
            int n = sc.nextInt();
            if(n == 0){
                break;
            }
            dp = new int[1<<16][16];
            times = new int[1<<16][16];
            for(int i =0; i < (1<<16); i++){
                for(int j =0; j< 16; j++){
                    dp[i][j] = -1;
                }
            }
            int arr[] = new int[n];
            for(int i =0; i < n; i++){
                arr[i] = sc.nextInt();
            }

            int max = 0,time = 0;
            for(int i =0; i < arr.length ; i++){
                int val = calculate(arr,1<<i,i)+arr[i];
                if( val > max){
                    max = val;
                    time = times[1<<i][i];
                }else if(max == val){
                    time += times[1<<i][i];
                }
            }
            sb.append((max+2*n) +" "+ time);
        }
        System.out.println(sb.toString());
    }
}

/** Faster input **/
class Reader {
    final private int BUFFER_SIZE = 1 << 32;private DataInputStream din;private byte[] buffer;private int bufferPointer, bytesRead;
    public Reader(){din=new DataInputStream(System.in);buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;}
    public Reader(String file_name)throws IOException{din=new DataInputStream(new FileInputStream(file_name));buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;}
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