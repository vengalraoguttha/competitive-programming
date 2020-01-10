package heaps;

import java.util.*;

public class IPCTRAIN_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-->0){
            int n = sc.nextInt();
            int d = sc.nextInt();
            int[][] cons = new int[n][3];
            long total_sadness = 0;
            HashMap<Integer, List<Integer>> dayWiseTrainers = new HashMap<>();
            for(int i = 0; i < n;i++){
                cons[i][0] = sc.nextInt();
                cons[i][1] = sc.nextInt();
                cons[i][2] = sc.nextInt();
                total_sadness += (cons[i][1] * (long)cons[i][2]);
                if(dayWiseTrainers.get(cons[i][0]) == null){
                    List<Integer> trainers = new ArrayList<>();
                    trainers.add(i);
                    dayWiseTrainers.put(cons[i][0],trainers);
                }else{
                    dayWiseTrainers.get(cons[i][0]).add(i);
                }
            }

            PriorityQueue<HeapObject> heap = new PriorityQueue<>(new HeapComparator());

            // for each day
            for(int i = 1; i <= d; i++){
                // add the trains that arrive on ith day
                if(dayWiseTrainers.get(i) != null){
                    for(Integer trainer :  dayWiseTrainers.get(i)){
                        heap.add(new HeapObject(cons[trainer][2],cons[trainer][1]));
                    }
                }

                // peek the trainer with max sadness
                HeapObject object = heap.peek();
                if(object != null){
                    total_sadness -= object.sadness;
                    object.totalLectures--;
                    if(object.totalLectures == 0)
                        heap.poll();
                }
            }

            System.out.println(total_sadness);
        }
    }
}
class HeapComparator implements Comparator<HeapObject>{

    @Override
    public int compare(HeapObject o1, HeapObject o2) {
        return o2.sadness - o1.sadness;
    }
}
class HeapObject{
    int sadness;
    int totalLectures;
    public HeapObject(int sadness, int totalLectures){
        this.sadness = sadness;
        this.totalLectures = totalLectures;
    }
}