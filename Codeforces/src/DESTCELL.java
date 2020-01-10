import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DESTCELL {
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int t = sc.nextInt();
        while (t > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();

            boolean[][] hits = new boolean[n][m];
            System.out.print(n*m+" ");
            for(int i = 1; i < (m*n) ; i++){
                // i indicates k in the problem.
                int count = 0;
//                for(int f = 0; f < n; f++){
//                    for(int s = 0; s < m ; s++){
//                        // (f,s) represents the cell need to find if that cell can be destroyed or not.
//                        if((f*m+s)%(i+1) == 0 || (s*n+f)%(i+1) == 0){
//                            count++;
//                        }
//                    }
//                }
                HashMap<String , String> map = new HashMap<>();
                for(int ind = 0; ind < m*n ; ind+=(i+1)){
                    int f = ind/m;
                    int s = ind%m;

                    int f1 = ind % n;
                    int s1 = ind/n;

                    if(!hits[f][s] && (f*m+s)%(i+1) == 0){
                        hits[f][s] = true;
                        count++;
                    }

                    if(!hits[f1][s1] && (s1*n+f1)%(i+1) == 0){
                        hits[f1][s1] = true;
                        count++;
                    }
                }

                for(int ind = 0; ind < m*n ; ind+=(i+1)){
                    int f = ind/m;
                    int s = ind%m;

                    int f1 = ind % n;
                    int s1 = ind/n;

                    if(hits[f][s]){
                        hits[f][s] = false;
                    }

                    if(hits[f1][s1]){
                        hits[f1][s1] = false;
                    }
                }
                System.out.print(count+" ");
            }
            System.out.println("");
            t--;
        }
    }
}
