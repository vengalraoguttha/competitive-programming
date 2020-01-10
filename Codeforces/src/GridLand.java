import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class GridLand {

    // Complete the gridlandMetro function below.
    static long gridlandMetro(int n, int m, int k, int[][] track) {
        HashMap<Integer,List<Range>> map = new HashMap<>();
        BigInteger result = BigInteger.valueOf(m).multiply(BigInteger.valueOf(n));
        for(int i =0; i < k ; i++){
            if(!map.containsKey(track[i][0])){
                List<Range> list = new ArrayList<>();
                Range range = new Range();
                range.max = track[i][2];
                range.min = track[i][1];
                list.add(range);
                map.put(track[i][0],list);
            }else {
                List<Range> rangeList = map.get(track[i][0]);
                for(int index = 0; index < rangeList.size(); index++){
                    Range range = new Range();
                    if(range.min > track[i][1]){
                        range.min = track[i][1];
                        range.max = track[i][2];
                        rangeList.add(index,range);
                    }
                }
                map.put(track[i][0],rangeList);
            }
        }
        for(Integer key : map.keySet()){
            List<Range> rangeList = map.get(key);
            Stack<Range> stack = new Stack<>();
            stack.push(rangeList.get(0));
            for(int index = 1 ; index < rangeList.size(); index++){
                Range range = stack.peek();
                if(range.max < rangeList.get(index).min){
                    result = result.subtract(BigInteger.valueOf(range.max)).add(BigInteger.valueOf(range.min)).subtract(BigInteger.valueOf(1));
                    stack.push(rangeList.get(index));
                }else if(range.max < rangeList.get(index).max){
                    range.max = rangeList.get(index).max;
                }
            }
        }
        return result.longValue();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nmk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nmk[0]);

        int m = Integer.parseInt(nmk[1]);

        int k = Integer.parseInt(nmk[2]);

        int[][] track = new int[k][3];

        for (int i = 0; i < k; i++) {
            String[] trackRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int trackItem = Integer.parseInt(trackRowItems[j]);
                track[i][j] = trackItem;
            }
        }

        long result = gridlandMetro(n, m, k, track);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

class Range{
    long min,max;
}
