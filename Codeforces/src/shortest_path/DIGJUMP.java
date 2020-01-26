package shortest_path;
import java.util.*;

public class DIGJUMP{
    static class Model{
        int a,level;
    }
    private static int bfs(Set<Integer>[] graph, int start,String s){
        Queue<Model> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        Model m = new Model();
        m.a = start;
        m.level = 0;
        queue.add(m);
        while (!queue.isEmpty()){
            Model x = queue.poll();
            if(x.a == s.length() - 1)
                return x.level;
            visited[x.a] = true;
            if(graph[s.charAt(x.a)-'0'] != null)
                for(Integer next : graph[s.charAt(x.a)-'0']){
                    if(!visited[next]){
                        if(next == s.length() - 1)
                            return x.level+1;
                        Model model = new Model();
                        model.a = next;
                        model.level = x.level + 1;
                        queue.add(model);
                    }
                }
            graph[s.charAt(x.a)-'0'].clear();
            if(x.a +1 < visited.length && !visited[x.a+1]){
                if(x.a +1 == s.length() - 1)
                    return x.level+1;
                Model model = new Model();
                model.a = x.a +1;
                model.level = x.level + 1;
                queue.add(model);
            }

            if(x.a - 1 >= 0 && !visited[x.a - 1]){
                if(x.a -1 == s.length() - 1)
                    return x.level+1;
                Model model = new Model();
                model.a = x.a -1;
                model.level = x.level + 1;
                queue.add(model);
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        Set<Integer>[] graph = new HashSet[10];
        for(int i = 0; i < s.length() ; i++){
            if(graph[s.charAt(i)-'0'] == null){
                graph[s.charAt(i)-'0'] = new HashSet<Integer>();
            }
            graph[s.charAt(i)-'0'].add(i);
        }
        System.out.println(bfs(graph,0,s));
    }
}