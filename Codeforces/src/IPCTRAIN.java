import java.util.*;

public class IPCTRAIN {
    private static void swap(int i, int j, HeapObject[] heap){
        int k = heap[i].sadness;
        int l = heap[i].totalLectures;
        heap[i] = heap[j];
        heap[j] = new HeapObject(k,l);
    }

    private static void max_heapify_bottomup(int i, HeapObject[] heap){
        if(i == 0)
            return;
        int parent = i/2;

        if(heap[parent].sadness < heap[i].sadness){
            swap(parent,i,heap);
            max_heapify_bottomup(parent,heap);
        }
    }

    private static void max_heapify(int i, HeapObject[] heap){
        int left = 2*i;
        int right = 2*i + 1;
        int largest = i;
        if(heap[largest].sadness < heap[left].sadness)
            largest = left;
        if(heap[i].sadness < heap[right].sadness)
            largest = right;

        if(i != largest){
            swap(largest,i,heap);
            max_heapify(largest,heap);
        }
    }

    private static void max_heapify(int i, HeapObject[] heap, int currentHeapSize){
        int left = 2*i;
        int right = 2*i + 1;
        int largest = i;
        if(left < currentHeapSize && heap[largest].sadness < heap[left].sadness)
            largest = left;
        if(right < currentHeapSize && heap[i].sadness < heap[right].sadness)
            largest = right;

        if(i != largest){
            swap(largest,i,heap);
            max_heapify(largest,heap,currentHeapSize);
        }
    }

    private static void build_heap(HeapObject[] heap){
        for(int i = heap.length/2; i >= 0; i--){
            max_heapify(i,heap);
        }
    }

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
                total_sadness += (cons[i][1] * cons[i][2]*1L);
                if(dayWiseTrainers.get(cons[i][0]) == null){
                    List<Integer> trainers = new ArrayList<>();
                    trainers.add(i);
                    dayWiseTrainers.put(cons[i][0],trainers);
                }else{
                    dayWiseTrainers.get(cons[i][0]).add(i);
                }
            }

            // for each day
            int currentHeapSize = 0;
            HeapObject[] heap = new HeapObject[n];
            for(int i = 1; i <= d; i++){
                // pick the trainer with max sadness.
                if(dayWiseTrainers.get(i) != null){
                    for(Integer trainer :  dayWiseTrainers.get(i)){
                        heap[currentHeapSize] = new HeapObject(cons[trainer][2],cons[trainer][1]);
                        max_heapify_bottomup(currentHeapSize,heap);
                        currentHeapSize++;
                    }
                }
                if(heap[0] != null && heap[0].totalLectures > 0){
                    total_sadness -= heap[0].sadness;
                    heap[0].totalLectures--;
                    if(heap[0].totalLectures == 0){
                        swap(0,currentHeapSize-1,heap);
                        currentHeapSize--;
                        max_heapify(0,heap, currentHeapSize);
                    }
                }
            }

            System.out.println(total_sadness);
        }
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