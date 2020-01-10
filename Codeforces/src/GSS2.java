//import java.util.HashSet;
//import java.util.Scanner;
//import java.util.Set;
//
//public class GSS2 {
//    public static Node constructSegmentTree(Node node, int arr[], int left, int right){
//        if(node == null)
//            node = new Node(-1);
//        if(left == right){
//            node.setValue(arr[left]);
//            node.setLeftIndex(left);
//            node.setRightIndex(right);
//            node.prefixSum = arr[left];
//            node.prefixSet.add(arr[left]);
//            node.suffixSum = arr[left];
//            node.suffixSet.add(arr[left]);
//            node.currentSum = arr[left];
//            node.currentSet.add(arr[left]);
//            return node;
//        }
//
//        node.left = constructSegmentTree(node.left,arr,left,left+(right-left)/2);
//        node.right = constructSegmentTree(node.right,arr,left+(right-left)/2+1,right);
//        node.setRightIndex(right);
//        node.setLeftIndex(left);
//        Set<Integer> intersection = new HashSet<Integer>(node.left.suffixSet);
//        intersection.retainAll(node.right.prefixSet);
//        long sum = 0;
//        for(Integer integer : intersection){
//            sum += integer;
//        }
//        node.setValue(max(node.left.value,node.right.value,node.left.suffixSum+node.right.prefixSum-sum));
//        intersection = new HashSet<Integer>(node.left.currentSet);
//        intersection.retainAll(node.right.currentSet);
//        sum = 0;
//        for(Integer integer : intersection){
//            sum += integer;
//        }
//
//        node.currentSum = node.left.currentSum+node.right.currentSum - sum;
//        Set<Integer> union = new HashSet<Integer>(node.left.currentSet);
//        union.addAll(node.right.currentSet);
//        node.currentSet = union;
//
//        intersection = new HashSet<Integer>(node.right.currentSet);
//        intersection.retainAll(node.left.suffixSet);
//        sum = 0;
//        for(Integer integer : intersection){
//            sum += integer;
//        }
//        if(node.right.currentSum+node.left.suffixSum-sum > node.right.suffixSum){
//            node.suffixSum = node.right.currentSum+node.left.suffixSum-sum;
//            union = new HashSet<Integer>(node.right.currentSet);
//            union.addAll(node.left.suffixSet);
//            node.suffixSet = union;
//        }else{
//            node.suffixSum = node.right.suffixSum;
//            node.suffixSet = node.right.suffixSet;
//        }
//
//        intersection = new HashSet<Integer>(node.left.currentSet);
//        intersection.retainAll(node.right.prefixSet);
//        sum = 0;
//        for(Integer integer : intersection){
//            sum += integer;
//        }
//
//        if(node.left.currentSum+node.right.prefixSum-sum > node.left.prefixSum){
//            node.prefixSum = node.left.currentSum+node.right.prefixSum-sum;
//            union = new HashSet<Integer>(node.left.currentSet);
//            union.addAll(node.right.prefixSet);
//            node.prefixSet = union;
//        }else {
//            node.prefixSum = node.left.prefixSum;
//            node.prefixSet = node.left.prefixSet;
//        }
//
//        return node;
//    }
//
//    static long max(long a, long b,long c){
//        if(a >= b && a >= c){
//            return a;
//        }else if(b >= a && b >= c){
//            return b;
//        }
//        return c;
//    }
//
//    public static Node querySegmentTree(Node tree, int start, int end, int arr[]){
//        if(tree.leftIndex> end || tree.rightIndex < start){
//            return null;
//        }
//        if(tree.leftIndex >= start && tree.rightIndex <= end){
//            return tree;
//        }
//        Node p = querySegmentTree(tree.left,start,end,arr);
//        Node q = querySegmentTree(tree.right,start,end,arr);
//        if(p == null){
//            return q;
//        }else if(q == null){
//            return p;
//        }
//        Node node = new Node(-1);
//
//        Set<Integer> intersection = new HashSet<Integer>(p.suffixSet);
//        intersection.retainAll(q.prefixSet);
//        long sum = 0;
//        for(Integer integer : intersection){
//            sum += integer;
//        }
//        node.setValue(max(p.value,q.value,p.suffixSum+q.prefixSum-sum));
//        intersection = new HashSet<Integer>(p.currentSet);
//        intersection.retainAll(q.currentSet);
//        sum = 0;
//        for(Integer integer : intersection){
//            sum += integer;
//        }
//
//        node.currentSum = p.currentSum+q.currentSum - sum;
//        Set<Integer> union = new HashSet<Integer>(p.currentSet);
//        union.addAll(q.currentSet);
//        node.currentSet = union;
//
//        intersection = new HashSet<Integer>(q.currentSet);
//        intersection.retainAll(p.suffixSet);
//        sum = 0;
//        for(Integer integer : intersection){
//            sum += integer;
//        }
//        if(q.currentSum+p.suffixSum-sum > q.suffixSum){
//            node.suffixSum = q.currentSum+p.suffixSum-sum;
//            union = new HashSet<Integer>(q.currentSet);
//            union.addAll(p.suffixSet);
//            node.suffixSet = union;
//        }else{
//            node.suffixSum = q.suffixSum;
//            node.suffixSet = q.suffixSet;
//        }
//
//        intersection = new HashSet<Integer>(p.currentSet);
//        intersection.retainAll(q.prefixSet);
//        sum = 0;
//        for(Integer integer : intersection){
//            sum += integer;
//        }
//
//        if(p.currentSum+q.prefixSum-sum > p.prefixSum){
//            node.prefixSum = p.currentSum+q.prefixSum-sum;
//            union = new HashSet<Integer>(p.currentSet);
//            union.addAll(q.prefixSet);
//            node.prefixSet = union;
//        }else {
//            node.prefixSum = p.prefixSum;
//            node.prefixSet = p.prefixSet;
//        }
//
//        return node;
//    }
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int arr[] = new int[n];
//        boolean allneg = true;
//        for(int i = 0 ; i < n ; i++){
//            arr[i] = sc.nextInt();
//            if(arr[i]>=0){
//                allneg = false;
//            }
//        }
//        Node segmentTree = new Node(-1);
//        segmentTree = constructSegmentTree(segmentTree,arr,0,n-1);
//        int m = sc.nextInt();
//        StringBuilder sb = new StringBuilder();
//        for(int i =0; i <m ;i++){
//            int s = sc.nextInt();
//            int e = sc.nextInt();
//            if(!allneg){
//                Node q = querySegmentTree(segmentTree,s-1,e-1,arr);
//                sb.append(q.value<0?0:q.value);
//                System.out.println(q.value);
//            }else{
//                sb.append(0);
//            }
//            sb.append("\n");
//        }
//        System.out.println(sb.toString());
//    }
//}
//
//class Node{
//    long value, prefixSum, suffixSum,currentSum;
//    int leftIndex, rightIndex;
//    Set<Integer> suffixSet = new HashSet<>();
//    Set<Integer> prefixSet = new HashSet<>();;
//    Set<Integer> currentSet = new HashSet<>();;
//    Node left, right;
//    public Node(int value){
//        this.value = value;
//    }
//
//    public void setLeft(Node left) {
//        this.left = left;
//    }
//
//    public void setRight(Node right) {
//        this.right = right;
//    }
//
//    public void setValue(long value) {
//        this.value = value;
//    }
//
//    public void setLeftIndex(int leftIndex) {
//        this.leftIndex = leftIndex;
//    }
//
//    public void setRightIndex(int rightIndex) {
//        this.rightIndex = rightIndex;
//    }
//}