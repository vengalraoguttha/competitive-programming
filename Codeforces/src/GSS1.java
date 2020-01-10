import java.util.Scanner;

public class GSS1 {
    public static Node constructSegmentTree(Node node, int arr[], int left, int right){
        if(node == null)
            node = new Node(-1);
        if(left == right){
            node.setValue(arr[left]);
            node.setLeftIndex(left);
            node.setRightIndex(right);
            node.prefixSum = arr[left];
            node.suffixSum = arr[left];
            node.currentSum = arr[left];
            return node;
        }

        node.left = constructSegmentTree(node.left,arr,left,left+(right-left)/2);
        node.right = constructSegmentTree(node.right,arr,left+(right-left)/2+1,right);
        node.setRightIndex(right);
        node.setLeftIndex(left);

        node.setValue(max(node.left.value,node.right.value,node.left.suffixSum+node.right.prefixSum));
        node.currentSum = node.left.currentSum+node.right.currentSum;
        node.suffixSum = node.right.currentSum+node.left.suffixSum > node.right.suffixSum ? node.right.currentSum+node.left.suffixSum : node.right.suffixSum;
        node.prefixSum = node.left.currentSum+node.right.prefixSum > node.left.prefixSum ? node.left.currentSum+node.right.prefixSum : node.left.prefixSum;

        return node;
    }

    static long max(long a, long b,long c){
        if(a >= b && a >= c){
            return a;
        }else if(b >= a && b >= c){
            return b;
        }
        return c;
    }

    public static Node querySegmentTree(Node tree, int start, int end, int arr[]){
        if(tree.leftIndex> end || tree.rightIndex < start){
            return null;
        }
        if(tree.leftIndex >= start && tree.rightIndex <= end){
            return tree;
        }
        Node p = querySegmentTree(tree.left,start,end,arr);
        Node q = querySegmentTree(tree.right,start,end,arr);
        if(p == null){
            return q;
        }else if(q == null){
            return p;
        }
        Node node = new Node(-1);
        node.setValue(max(p.value,q.value,p.suffixSum+q.prefixSum));
        node.currentSum = p.currentSum+q.currentSum;
        node.suffixSum = q.currentSum+p.suffixSum > q.suffixSum ? q.currentSum+p.suffixSum : q.suffixSum;
        node.prefixSum = p.currentSum+q.prefixSum > p.prefixSum ? p.currentSum+q.prefixSum : p.prefixSum;

        return node;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }
        Node segmentTree = new Node(-1);
        segmentTree = constructSegmentTree(segmentTree,arr,0,n-1);
        int m = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int i =0; i <m ;i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            Node q = querySegmentTree(segmentTree,s-1,e-1,arr);
            sb.append(q.value);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}

class Node{
    long value, prefixSum, suffixSum,currentSum;
    int leftIndex, rightIndex;
    Node left, right;
    public Node(int value){
        this.value = value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public void setLeftIndex(int leftIndex) {
        this.leftIndex = leftIndex;
    }

    public void setRightIndex(int rightIndex) {
        this.rightIndex = rightIndex;
    }
}