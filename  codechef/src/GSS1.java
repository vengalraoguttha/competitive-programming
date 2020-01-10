import java.util.Scanner;

public class GSS1 {
    public static void constructSegmentTree(Node node, int arr[], int left, int right){
        if(node == null)
            node = new Node(-1);
        if(left == right){
            node.setValue(left);
            node.setLeftIndex(left);
            node.setRightIndex(right);
            return;
        }
        constructSegmentTree(node.left,arr,left,left+(right-left)/2);
        constructSegmentTree(node.right,arr,left+(right-left)/2+1,right);

        node.setRightIndex(right);
        node.setLeftIndex(left);

        if(arr[node.left.value]>arr[node.right.value]){
            node.setValue(node.left.value);
        }else{
            node.setValue(node.right.value);
        }
    }

    public static int querySegmentTree(Node node, int start, int end, int arr[]){
        if(node.leftIndex> end || node.rightIndex < start){
            return -1;
        }
        if(node.leftIndex > start && node.rightIndex < end){
            return node.value;
        }
        int p = querySegmentTree(node.left,start,node.left.rightIndex,arr);
        int q = querySegmentTree(node.right,node.right.leftIndex,end,arr);
        if(p == -1){
            return q;
        }
        if(q == -1){
            return p;
        }
        if(arr[p] < arr[q]){
            return q;
        }else{
            return p;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }
        Node segmentTree = new Node(-1);
        constructSegmentTree(segmentTree,arr,0,n-1);
        int m = sc.nextInt();
        for(int i =0; i <m ;i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            int q = querySegmentTree(segmentTree,s,e,arr);
            System.out.println(arr[q]);
        }
    }
}
class Node{
    int value;
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

    public void setValue(int value) {
        this.value = value;
    }

    public void setLeftIndex(int leftIndex) {
        this.leftIndex = leftIndex;
    }

    public void setRightIndex(int rightIndex) {
        this.rightIndex = rightIndex;
    }
}