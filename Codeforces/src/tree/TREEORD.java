package tree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class TREEORD {
    static class Node{
        int value;
        Node left,right;
    }

    private static Node getTree(int[] pre, int[] in, int pos, int s, int e,boolean[] visited){
        if(pos >= pre.length)
            return null;
        if(s > e){
            return null;
        }
        Node n = new Node();
        n.value = pre[pos];
        visited[pos] = true;
        int split = -1;
        for(int i = s; i <= e; i++){
            if(pre[pos] == in[i]){
                split = i;
                break;
            }
        }
        if(split != -1){
            while (pos < pre.length && visited[pos]){
                pos++;
            }
            n.left = getTree(pre,in,pos,s,split-1,visited);
            while (pos < pre.length && visited[pos]){
                pos++;
            }
            n.right = getTree(pre,in,pos,split+1,e,visited);
        }

        return n;
    }

    static int index = 0;

    private static boolean postOrderCompare(Node n,int[] post){
        if(n.left != null && !postOrderCompare(n.left,post)){
            return false;
        }
        if(n.right != null && !postOrderCompare(n.right,post)){
            return false;
        }
        if(post[index] != n.value){
            index++;
            return false;
        }
        index++;
        return true;
    }

    public static void main(String[] args) throws IOException{
        Reader sc = new Reader();
        int n = sc.nextInt();
        int[] pre = new int[n];
        int[] post = new int[n];
        int[] inorder = new int[n];
        for(int i = 0; i < n; i++){
            pre[i] = sc.nextInt();
        }
        for(int i = 0; i < n; i++){
            post[i] = sc.nextInt();
        }
        for(int i = 0; i < n; i++){
            inorder[i] = sc.nextInt();
        }

        /**
         * construct the tree using pre-order and in-order and check the post-order.
         */
        boolean[] visited = new boolean[pre.length];
        Node node = getTree(pre,inorder,0,0,n-1,visited);
        System.out.println(postOrderCompare(node,post) ? "yes" : "no");
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
