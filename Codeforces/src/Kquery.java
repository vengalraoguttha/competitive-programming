import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Kquery {

    static KqueryNode build(KqueryNode node, int arr[], int i, int j){
        if(node == null)
            node = new KqueryNode();
        node.leftIndex = i;
        node.rightIndex = j;
        if(i == j){
            node.set.add(arr[i]);
            node.frequency[arr[i]]++;
            return node;
        }

        node.left = build(node.left,arr,i,i+(j-i)/2);
        node.right = build(node.right,arr,i+(j-i)/2+1,j);
        if(node.left.set.size() > node.right.set.size()){
            node.set = node.left.set;
            node.frequency = node.left.frequency;
            for(Integer val : node.right.set){
                node.frequency[val] += node.right.frequency[val];
            }
        }else{
            node.set = node.right.set;
            node.frequency = node.right.frequency;
            for (Integer val : node.left.set){
                node.frequency[val] += node.left.frequency[val];
            }
        }
        return node;
    }

    static KqueryNode query(KqueryNode node, int arr[], int i , int j){
        if(i > node.rightIndex || j < node.leftIndex){
            return null;
        }
        if(i <= node.leftIndex && j >= node.rightIndex){
            return node;
        }

        KqueryNode p = query(node.left,arr,i,j);
        KqueryNode q = query(node.right,arr,i,j);

        KqueryNode kqueryNode = new KqueryNode();

        if(kqueryNode.left.set.size() > kqueryNode.right.set.size()){
            kqueryNode.set = p.set;
            kqueryNode.frequency = p.frequency;
            for(Integer val : q.set){
                kqueryNode.frequency[val] += q.frequency[val];
            }
        }else{
            kqueryNode.set = q.set;
            kqueryNode.frequency = q.frequency;
            for (Integer val : p.set){
                kqueryNode.frequency[val] += p.frequency[val];
            }
        }
        return kqueryNode;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i =0; i < n;i++){
            arr[i] = sc.nextInt();
        }
        int q = sc.nextInt();
        KqueryNode tree = build(null,arr,0,n-1);
        StringBuilder sb = new StringBuilder();
        for(int i =0; i < q; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int k = sc.nextInt();
            KqueryNode node = query(tree,arr,x-1,y-1);
            int count = 0;
            for(Integer s : node.set){
                count += node.frequency[s];
            }
            sb.append(count);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}

class KqueryNode{
    int frequency[] = new int[1000000001];
    Set<Integer> set = new HashSet<>();
    int leftIndex,rightIndex;
    KqueryNode left,right;
}
