import java.util.Scanner;

public class DELISH {
    static long[] minSubArray(long arr[], int end , long arr1[]){
        long sum = arr[0], min = arr[0];
        arr1[0] = min;
        for(int i = 1; i<= end ; i++){
            if(arr[i]+sum >= arr[i]){
                sum = arr[i];
                min = sum;
            }else if(arr[i]+sum < arr[i]){
                sum += arr[i];
                if(min >= sum)
                    min = sum;
            }
            arr1[i] = min;
        }
        return arr1;
    }

    static long[] minSubArray2(long arr[], int start, long arr1[]){
        long sum = arr[arr.length-1], min = arr[arr.length-1];
        arr1[arr.length-1] = min;
        for(int i = arr.length-2; i >= start ; i--){
            if(arr[i]+sum >= arr[i]){
                sum = arr[i];
                min = sum;
            }else if(arr[i]+sum < arr[i]){
                sum += arr[i];
                if(min >= sum)
                    min = sum;
            }
            arr1[i] = min;
        }
        return arr1;
    }

    static long[] maxSubArray(long arr[], int start, long arr1[]){
        long sum = arr[arr.length-1], max = arr[arr.length-1];
        arr1[arr.length-1] = max;
        for(int i = arr.length-2; i >= start ; i--){
            if(arr[i]+sum <= arr[i]){
                sum = arr[i];
                max = sum;
            }else if(arr[i]+sum > arr[i]){
                sum += arr[i];
                if(max <= sum)
                    max = sum;
            }
            arr1[i] = max;
        }
        return arr1;
    }

    static long[] maxSubArray2(long arr[], int end,long arr1[]){
        long sum = arr[0], max = arr[0];
        arr1[0] = max;
        for(int i = 1; i<= end ; i++){
            if(arr[i]+sum <= arr[i]){
                sum = arr[i];
                max = sum;
            }else if(arr[i]+sum > arr[i]){
                sum += arr[i];
                if(max <= sum)
                    max = sum;
            }
            arr1[i] = max;
        }
        return arr1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int x = 0; x < t; x++){
            int n = sc.nextInt();
            long[] d = new long[n];
            long[] sumLeft = new long[n+1];
            long[] sumRight = new long[n+1];
            sumLeft[0] = 0;
            sumRight[n] = 0;
            for(int i = 0; i < n; i++){
                d[i] = sc.nextLong();
                sumLeft[i+1] = sumLeft[i] + d[i];
            }

            for(int i = n-1; i >= 0; i--){
                sumRight[i] = sumRight[i+1] + d[i];
            }

            long arr1[] = new long[n];
            long arr2[] = new long[n];
            long arr3[] = new long[n];
            long arr4[] = new long[n];
            arr1 = minSubArray(d,n-1,arr1);
            arr2 = maxSubArray(d,0,arr2);
            arr3 = maxSubArray2(d,n-1,arr3);
            arr4 = minSubArray2(d,0,arr4);

            long max = Long.MIN_VALUE;
            for(int i = 0; i < n-1; i++){
                long a = arr1[i];
                long b = arr2[i+1];
                long c = arr3[i];
                long dd = arr4[i+1];
                max = Math.abs(a-b) > max ? Math.abs(a-b) : max;
                max = Math.abs(c-dd) > max ? Math.abs(c-dd) : max;
            }
            System.out.println(max);
        }
    }
}
