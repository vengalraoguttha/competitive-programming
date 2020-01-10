public class SegmentTree {

    SegmentTreeNode[] nodes;
    int N;
    int[] arr = new int[1000001];

    SegmentTree(int arr[], int N) {
        this.N = N;
        nodes = new SegmentTreeNode[getSegmentTreeSize(N)];
        buildTree(arr, 1, 0, N-1);
    }

    int getSegmentTreeSize(int N) {
        int size = 1;
        for (; size < N; size <<= 1);
        return size << 1;
    }

    public void buildTree(int arr[], int stIndex, int lo, int hi) {
        if(nodes[stIndex] == null){
            nodes[stIndex] = new SegmentTreeNode();
        }
        if (lo == hi) {
            nodes[stIndex].assignLeaf(arr[lo]);
            if(nodes[stIndex].gcd < 1000001)
                this.arr[nodes[stIndex].gcd]++;
            return;
        }

        int left = 2 * stIndex, right = left + 1, mid = (lo + hi) / 2;
        buildTree(arr, left, lo, mid);
        buildTree(arr, right, mid + 1, hi);
        nodes[stIndex].merge(nodes[left], nodes[right]);
        if(nodes[stIndex].gcd < 1000001)
            this.arr[nodes[stIndex].gcd]++;
    }

    Object getValue(int lo, int hi) {
        if(lo > hi)
            return new Counts();
        SegmentTreeNode result = getValue(1, 0, N-1, lo, hi);
        return result.getValue();
    }

    void update(int index, int value) {
        update(1, 0, N-1, index, value);
    }

    SegmentTreeNode getValue(int stIndex, int left, int right, int lo, int hi) {
        if (left == lo && right == hi)
            return nodes[stIndex];

        int mid = (left + right) / 2;
        if (lo > mid)
            return getValue(2*stIndex+1, mid+1, right, lo, hi);
        if (hi <= mid)
            return getValue(2*stIndex, left, mid, lo, hi);

        SegmentTreeNode leftResult = getValue(2*stIndex, left, mid, lo, mid);
        SegmentTreeNode rightResult = getValue(2*stIndex+1, mid+1, right, mid+1, hi);
        SegmentTreeNode result = new SegmentTreeNode();
        result.merge(leftResult, rightResult);
        return result;
    }

    void update(int stIndex, int lo, int hi, int index, int value) {
        if (lo == hi) {
            nodes[stIndex].assignLeaf(value);
            return;
        }

        int left = 2 * stIndex, right = left + 1, mid = (lo + hi) / 2;
        if (index <= mid)
            update(left, lo, mid, index, value);
        else
            update(right, mid+1, hi, index, value);

        nodes[stIndex].merge(nodes[left], nodes[right]);
    }

}

class SegmentTreeNode {
    // variables to store aggregate statistics and
    // any other information required to merge these
    // aggregate statistics to form parent nodes
    int gcd;

    int lazy;

    int gcd(int a, int b)
    {
        return (a % b == 0) ?
                Math.abs(b) : gcd(b,a%b);
    }

    void assignLeaf(int value) {
    // T is the type of input array element
    // Given the value of an input array element,
    // build aggregate statistics for this leaf node
        gcd = value;
    }

    void merge(SegmentTreeNode left, SegmentTreeNode right) {
    // merge the aggregate statistics of left and right
    // children to form the aggregate statistics of
    // their parent node
        gcd = gcd(left.gcd,right.gcd);
    }

    Object getValue() {
    // V is the type of the required aggregate statistic
    // return the value of required aggregate statistic
    // associated with this node
        return gcd;
    }

    int min(int a, int b){
        if(a < b)
            return a;
        return b;
    }
}

class Counts{
    int even, odd;
}