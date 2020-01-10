import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DESTCELL_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            sb.append(n*m+" ");
            for(int k = 1; k < n*m ; k++){
                Set<DestIn> set = new HashSet<>();
                for(int i = 0; i < n*m ; i += (k+1)){
                    DestIn row_ = new DestIn();
                    row_.a = i / m;
                    row_.b = i % m;
                    DestIn col_ = new DestIn();
                    col_.a = i % n;
                    col_.b = i / n;
                    set.add(row_);
                    set.add(col_);
                }
                sb.append(set.size()+" ");
            }
            sb.append("\n");
            t--;
        }
        System.out.println(sb.toString());
    }
}
class DestIn{
    int a,b;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + a;
        result = prime * result + b;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DestIn other = (DestIn) obj;
        if (a != other.a)
            return false;
        if (b != other.b)
            return false;
        return true;
    }
}
