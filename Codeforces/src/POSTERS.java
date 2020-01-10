import java.util.*;

public class POSTERS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0){
            int n = sc.nextInt();
            Query[] queries = new Query[n];
            int max =0;
            int min = Integer.MAX_VALUE;

            Set<Integer> ss = new HashSet<>();
            for(int i=0; i < n;i++){
                int l = sc.nextInt();
                int r = sc.nextInt();
                Query q = new Query();
                q.i = l;
                q.j = r;
                queries[i] = q;
                max = max(max,r);
                min = min(min,l);
                ss.add(l);
                ss.add(r);
                ss.add(l-1);
//                ss.add(l+1);
//                ss.add(r-1);
                ss.add(r+1);
            }

            ArrayList<Integer> list = new ArrayList<>(ss);
            Collections.sort(list);

            SegmentTreeLazy segmentTree = new SegmentTreeLazy(list.size(),new int[list.size()]);
            for(int i=0; i < n;i++){
                segmentTree.update(1,0,list.size()-1,Collections.binarySearch(list,queries[i].i),Collections.binarySearch(list,queries[i].j),i+1);
//                for(int j=0; j < list.size();j++){
//                    int a = segmentTree.query(1,0,list.size()-1,j,j);
//                    System.out.print(a+" ");
//                }
//                System.out.println("");
            }

            Set<Integer> set = new HashSet<>();
            for(int i=0; i < list.size();i++){
                int a = segmentTree.query(1,0,list.size()-1,i,i);
                if(a != 0)
                    set.add(a);
            }

            System.out.println(set.size());
            t--;
        }
    }

    static int max(int a, int b){
        if( a > b)
            return a;
        return b;
    }

    static int min(int a, int b){
        if( a < b)
            return a;
        return b;
    }
}
