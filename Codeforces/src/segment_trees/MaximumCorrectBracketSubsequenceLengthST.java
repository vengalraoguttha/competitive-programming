package segment_trees;

public class MaximumCorrectBracketSubsequenceLengthST {
    class Node{
        Integer index,left,right;
        Long value,open,close;
    }

    Node[] tree;

    private void init(char[] arr){
        tree = new Node[4*arr.length];
    }

    MaximumCorrectBracketSubsequenceLengthST(char[] arr){
        init(arr);
        construct(arr,0,arr.length-1,1);
    }

    private Node merge(Node left, Node right){
        Node parent = new Node();
        long t = Math.min(left.open,right.close);
        parent.value = left.value + right.value + t;
        parent.open = left.open + right.open - t;
        parent.close = left.close + right.close - t;
        parent.left = left.left;
        parent.right = right.right;

        return parent;
    }

    private void construct(char[] arr, int low, int high,int index){
        if(low == high){
            tree[index] = new Node();
            tree[index].value = (long) 0;
            tree[index].left = low;
            tree[index].right = high;
            if(arr[low] == '('){
                tree[index].open = (long) 1;
                tree[index].close = (long) 0;
            }else{
                tree[index].open = (long) 0;
                tree[index].close = (long) 1;
            }
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
