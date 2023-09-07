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
    static boolean[][] visit;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[]args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        visit = new boolean[N][M];

        for(int i = 0; i<N; i++){
            String input = br.readLine();
            for(int j = 0; j<M; j++){
               graph[i][j] = Character.getNumericValue(input.charAt(j));

            }
        }

        visit[0][0]=true;
        bfs(0,0);

        System.out.println(graph[N-1][M-1]);

        br.close();


    }

    static void bfs(int x, int y){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x,y));

        while(!queue.isEmpty()){
            Node tmp = queue.poll();
            for(int i = 0; i< 4; i++){
                int nextx= tmp.x + dx[i];
                int nexty= tmp.y + dy[i];

                if(nextx >=0 && nexty>=0 && nextx<N && nexty<M){
                    if(!visit[nextx][nexty] && graph[nextx][nexty]==1){
                        queue.offer(new Node(nextx,nexty));
                        visit[nextx][nexty]= true;
                        graph[nextx][nexty] = graph[tmp.x][tmp.y]+1;

                    }
                }
            }


        }
    }
}

class Node{
    int x;
    int y;
    Node(int x ,int y){
        this.x = x;
        this.y = y;
    }
}