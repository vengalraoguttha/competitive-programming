package segment_trees;

public class SumInRangeST {
    class Node{
        int left,right,value;
    }

    Node[] tree;

    private void init(int[] arr){
        tree = new Node[4*arr.length];
    }

    SumInRangeST(int[] arr){
        init(arr);
        construct(arr,0,arr.length-1,1);
    }

    private Node merge(Node left, Node right){
        Node parent = new Node();
        parent.left = left.left;
        parent.right = right.right;
        parent.value = left.value + right.value;

        return parent;
    }

    private void construct(int[] arr, int lo, int hi, int index){
        if(lo == hi){
            tree[index] = new Node();
            tree[index].left = lo;
            tree[index].right = hi;
            tree[index].value = arr[lo];
        }else{
            int mid = lo+(hi-lo)/2;
            construct(arr,lo,mid, 2*index);
            construct(arr,mid+1,hi,2*index+1);
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

    public void update(int pos,int val){
        update(pos,val,1);
    }

    private void update(int pos, int val,int index){
        if(tree[index].left == pos && tree[index].right == pos){
            tree[index].value = val;
            return;
        }
        if(tree[index].right < pos || tree[index].left > pos)
            return;
        update(pos,val,2*index);
        update(pos,val,2*index+1);
        tree[index] = merge(tree[2*index],tree[2*index+1]);
    }
}
