public class LazySegmentTree {

    int N;
    LazySegmentTreeNode[] nodes;

    LazySegmentTree(int n, int arr[]){
        this.N = N;
        nodes = new LazySegmentTreeNode[treeHeight(N)];
        buildTree(arr, 1, 0, N-1);
    }

    int treeHeight(int n){
        int size = 1;
        for(; size < n; size <<=1);
        return size << 1;
    }

    public void buildTree(int arr[], int stIndex, int lo, int hi) {
        if(nodes[stIndex] == null){
            nodes[stIndex] = new LazySegmentTreeNode();
        }
        if (lo == hi) {
            nodes[stIndex].assignLeaf(arr[lo]);
            return;
        }

        int left = 2 * stIndex, right = left + 1, mid = (lo + hi) / 2;
        buildTree(arr, left, lo, mid);
        buildTree(arr, right, mid + 1, hi);
        nodes[stIndex].merge(nodes[left], nodes[right]);
    }


}

class LazySegmentTreeNode {
    // variables to store aggregate statistics and
    // any other information required to merge these
    // aggregate statistics to form parent nodes
    int min;

    int lazy;

    void assignLeaf(int value) {
        // T is the type of input array element
        // Given the value of an input array element,
        // build aggregate statistics for this leaf node
        min = value;
    }

    void merge(LazySegmentTreeNode left, LazySegmentTreeNode right) {
        // merge the aggregate statistics of left and right
        // children to form the aggregate statistics of
        // their parent node
        min = min(left.min,right.min);
    }

    int getValue() {
        // V is the type of the required aggregate statistic
        // return the value of required aggregate statistic
        // associated with this node
        return min;
    }

    int min(int a, int b){
        if(a < b)
            return a;
        return b;
    }
}
