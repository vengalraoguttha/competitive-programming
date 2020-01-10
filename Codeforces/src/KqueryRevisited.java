import java.io.IOException;
import java.util.*;

public class KqueryRevisited {

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();

        int[] ans = new int[k];

        List<Query> queries = new ArrayList<>();

        for(int i =0; i <k; i++){
            Query query = new Query();
            query.i = sc.nextInt();
            query.j = sc.nextInt();
            query.k = sc.nextInt();
            query.queryPosition = i;
            queries.add(query);
        }

        for(int i = 0; i < n; i++){
            Query query = new Query();
            query.k = arr[i];
            query.index = i;
            queries.add(query);
        }

        Collections.sort(queries,new Comparator<Query>() {
            @Override
            public int compare(Query a, Query b) {
                return b.k.compareTo(a.k);
            }
        });

        SegmentTree segmentTree = new SegmentTree(new int[n],arr.length);
        for(int i = 0; i < queries.size(); i++){
            Query query = queries.get(i);
            if(query.i == null && query.j == null){
                // array element
                segmentTree.update(query.index,query.k);
            }else{
                // query element
                ans[query.queryPosition]=(int)segmentTree.getValue(query.i-1,query.j-1);
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i =0; i < k;i++){
            sb.append(ans[i]).append("\n");
        }

        System.out.println(sb.toString());
    }
}

class Query{
    Integer i,j,k;
    int index,queryPosition;
}
