import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class KS1 {

//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        int t = sc.nextInt();
//
//        for(int x =0 ; x < t; x++){
//            int n = sc.nextInt();
//            int[] arr = new int[n];
//            int[] inc_xor = new int[n];
//            int[] dec_xor = new int[n];
//            MyObj[] inc_myob = new MyObj[n];
//            for(int i =0; i < n ; i++){
//                arr[i] = sc.nextInt();
//                if(i==0){
//                    inc_xor[i] = arr[i];
//                }else {
//                    inc_xor[i] = inc_xor[i-1]^arr[i];
//                }
//            }
//            for(int i =n-1; i >= 0 ; i--){
//                inc_myob[i] = new MyObj();
//                if(i==n-1){
//                    dec_xor[i] = arr[i];
//                }else {
//                    dec_xor[i] = dec_xor[i+1]^arr[i];
//                }
//                inc_myob[i].index = i;
//                inc_myob[i].value = dec_xor[i];
//            }
//            int total_xor = inc_xor[n-1];
////            if(total_xor == 0){
////                System.out.println(n-1);
////                continue;1
////            }
//
//            int count = 0 ;
//            for(int i =0 ; i < n ; i++){
//                if((inc_xor[i]) == 0){
//                    count+=i;
//                }
//                for(int j = 0; j < i-1 ; j++){
//                    if((inc_xor[i]^inc_xor[j]) == 0){
//                        count+=(i-j-1) >0 ? i-j-1 : 0;
//                    }
//                }
//            }
//            System.out.println(count);
//        }
//    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int x =0 ; x < t; x++){
            int n = sc.nextInt();
            int[] arr = new int[n];
            int[] inc_xor = new int[n];
            int[] dec_xor = new int[n];
            MyObj[] inc_myob = new MyObj[n];
            for(int i =0; i < n ; i++){
                inc_myob[i] = new MyObj();
                arr[i] = sc.nextInt();
                if(i==0){
                    inc_xor[i] = arr[i];
                }else {
                    inc_xor[i] = inc_xor[i-1]^arr[i];
                }
                inc_myob[i].index = i;
                inc_myob[i].value = inc_xor[i];
            }

            int total_xor = inc_xor[n-1];

            int count = 0;
            Arrays.sort(inc_myob,new MyComparator());
            HashMap<String , Integer> map = new HashMap<>();
            for(int i = 1 ; i < n ;i ++){
                int end_ind = Arrays.binarySearch(inc_myob,0,n,new MyObj((inc_xor[i])),new MyComparator2());
                int tmp = end_ind;
                while(tmp >= 0 && tmp <= n-1 && ((inc_xor[i])) == inc_myob[tmp].value){
                    if(inc_myob[tmp].index != i && !map.containsKey(i+" "+end_ind) && !map.containsKey(end_ind+" "+i))
                        count += Math.abs(i-1-tmp);
                    tmp++;
                }
                tmp = end_ind-1;
                while(tmp >= 0 && tmp <= n-1 && ((inc_xor[i])) == inc_myob[tmp].value && !map.containsKey(i+" "+end_ind) && !map.containsKey(end_ind+" "+i)){
                    count += Math.abs(i-1-tmp);
                    tmp--;
                }
                if(inc_xor[i] == 0){
                    count += i;
                }
                map.put(end_ind+" "+i,0);
                map.put(i+" "+end_ind,0);
            }
            System.out.println(count);
        }
    }
}

class MyObj{
    Integer value,index;

    public MyObj(){
    }

    public MyObj(int value){
        this.value = value;
    }
}

class MyComparator implements Comparator<MyObj> {

    @Override
    public int compare(MyObj o1, MyObj o2) {
        if(o1.value != o2.value)
            return o1.value.compareTo(o2.value);
        else
            return o2.index.compareTo(o2.index);
    }
}

class MyComparator2 implements Comparator<MyObj> {

    @Override
    public int compare(MyObj o1, MyObj o2) {
        return o1.value.compareTo(o2.value);
    }
}