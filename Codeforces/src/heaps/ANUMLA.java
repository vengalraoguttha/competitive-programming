package heaps;

import java.util.*;

public class ANUMLA {
//    // can be optimised further
//    static void removeSubSetSums(int[] array, int found, List<Integer> pq, int sum){
//        if(found < 0){
//            pq.remove(Integer.valueOf(sum));
//            return;
//        }
//        removeSubSetSums(array,found-1,pq,sum);
//        removeSubSetSums(array,found-1,pq,sum+array[found]);
//    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0){
            int n = sc.nextInt();
            int nn = (int) Math.pow(2,n);
            List<Integer> pq = new ArrayList<>();
            int[] array = new int[n];
            int found = 0;
            boolean isSingle = false;
            for(int i = 0; i < nn; i++){
                int x = sc.nextInt();
                if(n == 1 && i == 1){
                    System.out.println(x);
                    isSingle = true;
                }
                pq.add(x);
            }

            pq.sort(null);

            if(isSingle)
                continue;

            pq.remove(0);

            array[found++] = pq.remove(0);
            array[found++] = pq.remove(0);
            List<Integer> setSum = new ArrayList<>();
            List<Integer> cSet = new ArrayList<>();
            setSum.add(array[0]);
            setSum.add(array[1]);
            setSum.add(array[0]+array[1]);
            cSet.add(array[0]);
            cSet.add(array[1]);
            cSet.add(array[0]+array[1]);

            while (!pq.isEmpty()){
                if(found == n){
                    break;
                }
                for(Integer sum : cSet){
                    pq.remove(sum);
                }
//                removeSubSetSums(setSum,found-1,pq,0);
                if(pq.size() >= 1){
                    array[found++] = pq.remove(0);
                    int size = setSum.size();
                    cSet.clear();
                    for(int i = 0; i < size; i++){
                        int val = setSum.get(i)+array[found-1];
                        setSum.add(val);
                        cSet.add(val);
                    }
                    setSum.add(array[found-1]);
                }
            }

            for(int i = 0; i < found; i++)
                System.out.print(array[i]+" ");
            System.out.println();
        }
    }
}

class IntegerndingPriority implements Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
}