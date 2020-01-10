import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int merge(int[] arr, int start,int mid , int end){
        int[] a1 = new int[mid-start+1];
        int[] a2 = new int[end-mid];
        for(int i = start; i <= mid ; i++){
            a1[i-start] = arr[i];
        }

        for(int i = mid+1; i <=end;i++){
            a2[i-mid-1] = arr[i];
        }

        int s1=0,s2=0,k=start,count=0;
        while(s1 < mid-start+1 && s2 < end-mid){
            if(a1[s1]<=a2[s2]){
                arr[k]=a1[s1];
                k++;
                s1++;
            }else{
                count += (mid-start+1-s1);
                arr[k]=a2[s2];
                k++;
                s2++;
            }
        }
        while(s1<mid-start+1){
            arr[k]=a1[s1];
            k++;
            s1++;
        }
        while(s2 < end-mid){
            arr[k]=a2[s2];
            k++;
            s2++;
        }
        return count;
    }
    static int mergeSort(int[] arr, int start, int end){
        if(start<end){
            int mid = start + (end-start)/2;
            return mergeSort(arr,start,mid) + mergeSort(arr,mid+1,end)+merge(arr,start,mid,end);
        }
        return 0;
    }

    // Complete the insertionSort function below.
    static int insertionSort(int[] arr) {
        return mergeSort(arr,0,arr.length-1);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int t = scanner.nextInt();
        for(int x =0; x < t;x++){
            int n = scanner.nextInt();
            int arr[] = new int[n];
            for(int i =0; i < n; i++){
                arr[i] = scanner.nextInt();
            }
            mergeSort(arr,0,n-1);
            for(int i =0; i < n; i++){
                System.out.println(arr[i]);
            }
        }
    }
}
