import java.util.*;

public class EQAVG_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            HashMap<Integer,Integer> counts = new HashMap<>();
            for(int i = 0;i < n; i++){
                int x = sc.nextInt();
                if(counts.containsKey(x)){
                    counts.put(x,counts.get(x)+1);
                }else{
                    counts.put(x,1);
                }
            }

            if(counts.keySet().size() > k){
                System.out.println("NO");
            }else{
                boolean f = false;
                for(Integer key : counts.keySet()){
                    if(counts.get(key) < n/k){
                        System.out.println("NO");
                        f = true;
                        break;
                    }
                }

                if(f){
                    t--;
                    continue;
                }

                System.out.println("YES");
                List<Ins> list = new ArrayList<>();
                for(Integer key : counts.keySet()){
                    list.add(new Ins(key,counts.get(key)));
                }

                Collections.sort(list, new Comparator<Ins>() {
                    @Override
                    public int compare(Ins o1, Ins o2) {
                        return o2.count - o1.count;
                    }
                });

                for(int i = 0; i < (n/k)*k; i ++){
                    System.out.print(list.get(i%list.size()).val+" ");
                }

                for(int i = 0; i < n%k; i ++){
                    System.out.print(list.get(i%list.size()).val+" ");
                }

                System.out.println("");
            }

            t--;
        }
    }
}
class Ins{
    int val,count;

    public Ins(int val, int count){
        this.val = val;
        this.count = count;
    }
}
