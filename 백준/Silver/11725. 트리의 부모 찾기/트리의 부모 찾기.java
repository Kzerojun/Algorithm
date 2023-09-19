import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static ArrayList<Integer>[] graph;
    static int[] solution;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        solution = new int[N+1];

        for(int i = 1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i =0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }
        bfs();

        for(int i = 2; i<solution.length; i++){
            System.out.println(solution[i]);
        }

    }

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);


        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            for(int neighbor : graph[tmp]){
                if(solution[neighbor]==0){
                    solution[neighbor] = tmp;
                    queue.offer(neighbor);
                }
            }

        }
    }
}
