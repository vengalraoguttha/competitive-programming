import java.util.HashSet;
import java.util.Set;

public class SegmentTreeLazy {

    int N;
    int[] arr;
    int[] lazy;
    int[] t;

    int getSegmentTreeSize(int N) {
        int size = 1;
        for (; size < N; size <<= 1);
        return size << 1;
    }

    SegmentTreeLazy(int N,int[] arr){
        this.N = N;
        this.arr = arr;
        lazy = new int[getSegmentTreeSize(N)];
        t = new int[getSegmentTreeSize(N)];
        build(1,0,N-1);
    }

    void build(int node, int a, int b)
    {
        if(a>b) return;
        if (a==b)
        {
            t[node] = 0;
            return;
        }

        build(node*2, a, (a+b)/2);
        build(node*2+1,(a+b)/2+1,b);

        t[node] = 0;
    }

    int query(int node, int a, int b, int i, int j)
    {
        if(a>b||a>j||b<i) return 0;
        if (lazy[node] !=0 )
        {
            t[node] = lazy[node];
            if (a!=b)
            {
                lazy[node*2]+=lazy[node];
                lazy[node*2+1]+=lazy[node];
            }
            lazy[node]=0;
        }

        if (a>=i && b<=j) return t[node];

        int q1=query(node*2, a, (a+b)/2, i, j);
        int q2=query(node*2+1, (a+b)/2+1, b, i, j);

        return q1+q2;
    }

    void update(int node, int a, int b, int i, int j, int inc)
    {
        if(a>b) return;
        if (lazy[node]!=0)
        {
            t[node] = lazy[node];
            if (a!=b)
            {
                lazy[node*2] = lazy[node];
                lazy[node*2+1] = lazy[node];
            }
            lazy[node]=0;
        }
        if(a>b||a>j||b<i) return;

        if (a>=i && b<=j)
        {
            t[node] = inc;
            if (a!=b)
            {
                lazy[node*2] = inc;
                lazy[node*2+1] = inc;
            }
            return;
        }

        update(node*2, a, (a+b)/2, i, j, inc);
        update(node*2+1, (a+b)/2+1, b,i, j, inc);
    }

}
