package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ELEVTRBL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int f = sc.nextInt();
        int s = sc.nextInt();
        int g = sc.nextInt();
        int u = sc.nextInt();
        int d = sc.nextInt();
        Data data = new Data(s,0);

        Queue<Data> queue = new LinkedList<>();
        queue.add(data);

        boolean flag = false;
        boolean[] visited = new boolean[f+1];

        while (!queue.isEmpty()){
            data = queue.poll();
            visited[data.pos] = true;
            if(data.pos == g){
                flag = true;
                System.out.println(data.level);
                break;
            }

            if(data.pos+u <= f && !visited[data.pos+u]){
                visited[data.pos+u] = true;
                queue.add(new Data(data.pos+u,data.level+1));
            }

            if(data.pos-d >= 1 && !visited[Math.max(1,data.pos-d)]){
                visited[data.pos-d] = true;
                queue.add(new Data(data.pos-d,data.level+1));
            }
        }

        if(!flag){
            System.out.println("use the stairs");
        }
    }

    static class Data{
        int pos,level;
        public Data(int pos,int level){
            this.pos = pos;
            this.level = level;
        }
    }
}
