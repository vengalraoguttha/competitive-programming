import java.io.IOException;

public class CHEFK1 {
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int t = sc.nextInt();

        while (t > 0){
            long n = sc.nextLong();
            long m = sc.nextLong();

            if(m < n-1 || m > (n*(n-1)/2)+n || (m == 0 && n > 1)){
                System.out.println("-1");
                t--;
                continue;
            }

            long tmp = m - (n-1) > n ? n : m -(n-1);

            if(n == 1 && m == 0){
                System.out.println("0");
            }
            else if(n == 1 && m == 1){
                System.out.println("1");
            }else if(tmp == 0){
                if(n == 2){
                    System.out.println("1");
                }else{
                    System.out.println("2");
                }
            }else if( tmp <= 2){
                System.out.println(max((int) Math.ceil(2.0*(m-tmp)/n),2));
            }else{
                System.out.println((int) Math.ceil(2.0*(m-tmp)/n) + 1);
            }

            t--;
        }
    }

    static int max(int a,int b){
        if( a < b)
            return b;
        return a;
    }
}


/*
15
5 4
2
5 5
2
5 6
2
5 7
3
5 8
3
5 9
3
5 10
3
5 11
4
5 12
4
5 13
5
5 14
5
5 15
5
5 16
-1

 */