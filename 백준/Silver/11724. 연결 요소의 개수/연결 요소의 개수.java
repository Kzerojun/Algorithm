import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] graph;
    static boolean[] visit;
    static int count;
    public static void main(String[]ars)throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N+1][N+1];
        visit = new boolean[N+1];
        count = 0;
        for(int i = 0; i< M; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x][y]=graph[y][x] = 1;

        }

        for(int i = 1; i<=N; i++){
            if(!visit[i]){
                bfs(i);
                count++;
            }
        }

        System.out.println(count);

    }

    static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        visit[start] = true;
        queue.offer(start);

        while(!queue.isEmpty()){
            int tmp = queue.poll();

            for(int i = 0; i<=N; i++){
                if(!visit[i]&& graph[tmp][i]==1){
                    queue.offer(i);
                    visit[i]= true;
                }
            }


        }


    }
}
