package jan2019_long_codechef;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class English {
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int t = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        while (t-- > 0){
            int n = sc.nextInt();

            Trie trie = new Trie();

            for(int i = 0; i < n; i++){
                String s = sc.readLine();
                String reverse = new StringBuilder(s).reverse().toString();
                StringBuilder pal = new StringBuilder();
                for(int ind = 0; ind  < s.length(); ind++){
                    pal.append(s.charAt(ind)).append(reverse.charAt(ind));
                }
                trie.insert(pal.toString());
            }

            sb.append(trie.process()).append("\n");
        }
        System.out.println(sb.toString());
    }

    /** Faster input **/
    static class Reader {
        final private int BUFFER_SIZE = 1 << 32;private DataInputStream din;private byte[] buffer;private int bufferPointer, bytesRead;
        public Reader(){din=new DataInputStream(System.in);buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;}
        public Reader(String file_name)throws IOException {din=new DataInputStream(new FileInputStream(file_name));buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;}
        public String readLine()throws IOException{byte[] buf=new byte[1024*128];int cnt=0,c;
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

class Node{
    char a;
    int count;
    int depth;
    List<Node> next = new ArrayList<>();
}

class Data{
    long completed;
    long ans;
}

class Trie{
    Node root;

    public Trie(){
        root = new Node();
        root.a = 'P';
        root.depth = 0;
    }

    public void insert(String input){
        Node x = root;
        for(int i = 0; i < input.length(); i++) {
            x = insert(input.charAt(i), x);
        }
    }

    private Node insert(char a, Node parent){

        for(Node child : parent.next){
            if(child.a == a){
                child.count++;
                return child;
            }
        }

        Node current = new Node();
        current.a = a;
        current.count++;
        parent.next.add(current);
        current.depth = parent.depth + 1;
        return current;
    }

    public long process(){
        return dfs(root).ans;
    }

    private Data dfs(Node root){
        long completed = 0;
        long ans =  0;
        for(Node child : root.next){
            Data data =  dfs(child);
            completed += data.completed;
            ans += data.ans;
        }

        long pending = root.count - completed;
        if(pending > 0)
            ans += (pending/2)*(root.depth/2)*(root.depth/2);
        Data data = new Data();
        data.ans = ans;
        data.completed = completed + pending - pending%2;
        return data;
    }
}