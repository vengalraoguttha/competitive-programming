import java.util.Scanner;

public class TreeOrd {

    static int findIndex(int[] inorder,int n,int val, int i, int j){
        for(int x=i; x<=j;x++){
            if(inorder[x]==val){
                return x;
            }
        }
        return -1;
    }

    static TreeOrdNode createTree(TreeOrdNode treeOrdNode, int[] preorder, int[] inorder, int n, int i, int j, int k){
        if(treeOrdNode == null)
            treeOrdNode = new TreeOrdNode();
        if(k >= n || i > j){
            return null;
        }
        treeOrdNode.value = preorder[k];
        if(i == j){
            return treeOrdNode;
        }
        int index = findIndex(inorder,n,preorder[k],i,j);
        if(index == -1){
            TreeOrdNode node = new TreeOrdNode();
            node.flag = 1;
            return node;
        }
        treeOrdNode.left = createTree(treeOrdNode.left,preorder,inorder,n,i,index-1,k+1);
        if(treeOrdNode.flag == 1){
            return treeOrdNode;
        }
        treeOrdNode.right = createTree(treeOrdNode.right,preorder,inorder,n,index+1,j,index-i+1+k);
        return treeOrdNode;
    }

    static boolean flag = true;
    static int runnPostOrder(int[] arr, int n, TreeOrdNode treeOrdNode,int l){
        int left = 0, right = 0;
        if(treeOrdNode.left != null)
            left = runnPostOrder(arr,n,treeOrdNode.left,l);
        if(treeOrdNode.right != null)
            right = runnPostOrder(arr,n,treeOrdNode.right,l+left);
        if(arr[left+right+l] != treeOrdNode.value){
            flag = false;
        }
        return left+right+1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] preorder = new int[n];
        int[] postorder = new int[n];
        int[] inorder = new int[n];

        for(int i =0; i < n;i++){
            preorder[i] = sc.nextInt();
        }

        for(int i =0; i<n;i++){
            postorder[i] = sc.nextInt();
        }

        for(int i =0; i<n;i++){
            inorder[i] = sc.nextInt();
        }

        TreeOrdNode treeOrdNode = new TreeOrdNode();
        treeOrdNode = createTree(treeOrdNode,preorder,inorder,n,0,n-1,0);
//        if(treeOrdNode.flag == 1){
//            System.out.println("no");
//        }
        runnPostOrder(postorder,n,treeOrdNode,0);
        if(flag){
            System.out.println("yes");
        }else{
            System.out.println("no");
        }
    }
}
class TreeOrdNode{
    int value;
    int flag;
    int size;
    TreeOrdNode left,right;
}