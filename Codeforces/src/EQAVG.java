import java.util.*;

public class EQAVG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] arr = new int[n];
            HashMap<Integer,Integer> counters = new HashMap<>();
            for(int i =0; i < n ; i++){
                arr[i] = sc.nextInt();
                if(counters.containsKey(arr[i])){
                    counters.put(arr[i],counters.get(arr[i])+1);
                }else{
                    counters.put(arr[i],1);
                }
            }
            int c1 = 0;
            int c2 = 0;
            int c3 = 0;
            int div = n/k;
            Set<Integer> s1 = new HashSet<>();
            Set<Integer> s2 = new HashSet<>();
            int a = 0;
            for(int i = 0; i < n; i++){
                int x = counters.get(arr[i]);
                s1.add(arr[i]);
                if(x % (div) == 0){
                    if( i > 0 && counters.get(arr[i]) == counters.get(arr[i-1])){
                        a = counters.get(arr[i]);
                        c1++;
                    }
                }else if((x-1) % div == 0 && x -1 !=0){
                    c2++;
                    s2.add(arr[i]);
                }else{
                    c3++;
                }
            }
            if(c1 + c2 == n){
                System.out.println("YES");
                ArrayList<Integer> list = new ArrayList<>(s1);
                ArrayList<Integer> list2 = new ArrayList<>(s2);
                Collections.sort(list);
                Collections.sort(list2);
                for(int i = 0; i < div ; i++){
                    for(int j =0; j < list.size(); j++){
                        System.out.print(list.get(j)+" ");
                    }
                }
                if(div == 1){
                    for(int j =0; j < n%k; j++){
                        System.out.print(list.get(j)+" ");
                    }
                }else{
                    for(int i =0; i <list2.size(); i++){
                        System.out.print(list.get(i)+" ");
                    }
                }
                System.out.println("");
            }else{
                System.out.println("NO");
            }
            t--;
        }
    }
}
