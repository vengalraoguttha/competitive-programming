//import java.util.Scanner;
//
//public class SerejaAndBrackets {
//
//    static Node buid(Node tree,String s,int i,int j){
//        if(tree == null)
//            tree = new Node();
//        tree.leftIndex = i;
//        tree.rightIndex = j;
//        if( i == j){
//            tree.value = 0;
//            if(s.charAt(i)=='(')
//                tree.left_b++;
//            else
//                tree.right_b++;
//            return tree;
//        }
//        tree.left = buid(tree.left,s,i,i+(j-i)/2);
//        tree.right = buid(tree.right,s,i+(j-i)/2+1,j);
//
//        tree.value = tree.left.value + tree.right.value + min(tree.left.left_b,tree.right.right_b);
//        tree.left_b = tree.left.left_b + tree.right.left_b - min(tree.left.left_b,tree.right.right_b);
//        tree.right_b = tree.left.right_b + tree.right.right_b - min(tree.left.left_b,tree.right.right_b);
//
//        return tree;
//    }
//
//    static Node query(Node tree,String s,int i,int j){
//        if(tree.leftIndex > j || tree.rightIndex < i){
//            return null;
//        }
//
//        if(tree.leftIndex >= i && tree.rightIndex <= j){
//            return tree;
//        }
//
//
//        Node p = query(tree.left,s,i,j);
//        Node q = query(tree.right,s,i,j);
//
//        if(p == null){
//            return q;
//        }
//
//        if(q == null){
//            return p;
//        }
//
//        Node node = new Node();
//        node.value = p.value + q.value + min(p.left_b,q.right_b);
//        node.left_b = p.left_b + q.left_b - min(p.left_b,q.right_b);
//        node.right_b = p.right_b + q.right_b - min(p.left_b,q.right_b);
//        return node;
//    }
//
//    static int min(int a, int b){
//        if(a<b)
//            return a;
//        return b;
//    }
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String sequence = sc.next();
//        Node tree = new Node();
//        tree = buid(tree,sequence,0,sequence.length()-1);
//        int n = sc.nextInt();
//        StringBuilder sb = new StringBuilder();
//        for(int i =0; i <n;i++){
//            int x = sc.nextInt();
//            int y = sc.nextInt();
//            sb.append(2*query(tree,sequence,x-1,y-1).value);
//            sb.append("\n");
//        }
//        System.out.println(sb.toString());
//    }
//}
//class Node{
//    int left_b,right_b,value;
//    int leftIndex,rightIndex;
//    Node left, right;
//}