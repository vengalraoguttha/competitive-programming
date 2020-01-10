import java.util.*;

public class EQAVG_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            int arr[] = new int[n];
            Set<Integer> set = new HashSet<>();
            Set<Integer> ss = new HashSet<>();
            HashMap<Integer,Integer> map = new HashMap<>();
            boolean alleq = true;
            for(int i =0; i < n; i++){
                arr[i] = sc.nextInt();
                set.add(arr[i]);
                if(map.containsKey(arr[i])){
                    map.put(arr[i],map.get(arr[i])+1);
                }else{
                    map.put(arr[i],1);
                }
                if( i > 0 && arr[i] != arr[i-1]){
                    alleq = false;
                }
            }

            if(alleq){
                System.out.println("YES");
                for(int i = 0; i <n ;i++){
                    System.out.print(arr[i]+" ");
                }
                System.out.println("");
                t--;
                continue;
            }

            if(set.size() > k){
                System.out.println("NO");
                t--;
                continue;
            }

            for(Integer key : map.keySet()){
                ss.add(map.get(key));
            }

//            if(ss.size() != 2){
//                System.out.println("NO");
//                t--;
//                continue;
//            }

            int x[] = new int[2];
            int index = 0;
            for(Integer in : ss){
                x[index] = in;
                index++;
            }

            if( (x[0] != x[1]+1) && (x[1] != x[0]+1)){
                System.out.println("NO");
                t--;
                continue;
            }

            if( x[0] == x[1] + 1){
                int tmp = x[0];
                x[0] = x[1];
                x[1] = tmp;
            }

            List<Integer> s1 = new ArrayList<>();
            List<Integer> s2 = new ArrayList<>();

            for(Integer key : map.keySet()){
                s1.add(key);
                if(map.get(key) == x[1]){
                    s2.add(key);
                }
            }

            s1.sort(null);
            s2.sort(null);

            System.out.println("YES");

            for(int j =0; j < x[0]; j++)
            for(int i = 0; i < s1.size(); i++){
                System.out.print(s1.get(i)+" ");
            }

            for(int i = 0; i < s2.size(); i++){
                System.out.print(s2.get(i)+" ");
            }

            System.out.println("");

            t--;
        }
    }
}
