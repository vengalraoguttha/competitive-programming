package segment_trees;

/**
 * this class contains code to find maximum sum of all subsets in a given range using segment tree.
 */
class MaximumSumAllSetsST {
    class Node{
        Integer index,left,right;
        Long value; // contains the maximum of sum of all sub arrays in range left to right inclusive
        Long sum; // total sum of all the elements in the given range.
        Long prefix,suffix; // maximum prefix and suffix sums in given range
    }

    Node[] tree;
    int size;

    private void init(int n){
        tree = new Node[4*n];
    }

    private void init(int[] arr){
        tree = new Node[4*arr.length];
        size = arr.length;
    }

    private Node merge(Node left, Node right){
        Node parent = new Node();
        parent.sum = left.sum + right.sum;
        parent.suffix = Math.max(left.suffix,left.sum+right.suffix);
        parent.prefix = Math.max(right.prefix,left.prefix+right.sum);
        parent.value = Math.max(Math.max(left.value,right.value),left.prefix+right.suffix);
        parent.left = left.left;
        parent.right = right.right;

        return parent;
    }

    MaximumSumAllSetsST(int[] arr){
        init(arr);
        construct(arr,0,arr.length-1,1);
    }

    private void construct(int[] arr, int low, int high,int index){
        if(low == high){
            tree[index] = new Node();
            tree[index].value = (long) arr[low];
            tree[index].left = low;
            tree[index].right = high;
            tree[index].sum = (long) arr[low];;
            tree[index].prefix = (long) arr[low];;
            tree[index].suffix = (long) arr[low];;
        }else{
            int mid = low + (high-low)/2;
            construct(arr,low,mid,2*index);
            construct(arr,mid+1,high,2*index+1);
            tree[index] = merge(tree[2*index],tree[2*index+1]);
        }
    }

    public long query(int start, int end){
        Node res =  queryFrom(start,end,1);
        if(res != null)
            return res.value;
        return 0;
    }

    private Node queryFrom(int start, int end, int index){
        if(tree[index].left > end || tree[index].right < start)
            return null;
        if(tree[index].left >= start && tree[index].right <= end)
            return tree[index];

        Node left = queryFrom(start,end,2*index);
        Node right = queryFrom(start,end,2*index+1);

        if(left == null)
            return right;
        if(right == null)
            return left;
        return merge(left,right);
    }
}
