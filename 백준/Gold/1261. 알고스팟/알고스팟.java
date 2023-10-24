import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] graph;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        dist = new int[N][M];

        for (int i=0; i<N; i++) {
            String row=br.readLine();
            for (int j=0; j<M; j++) {
                graph[i][j] = row.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();

        System.out.println(dist[N-1][M-1]);
    }

    static void bfs() {
        PriorityQueue<Node1261> pq=new PriorityQueue<>(((o1, o2) -> {
            return o1.wall - o2.wall;
        }));

        pq.offer(new Node1261(0 , 0 , 0));
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Node1261 Node1261=pq.poll();

            for(int i= 0 ; i<4 ; ++i){
                int nx= Node1261.x + dx[i];
                int ny= Node1261.y + dy[i];

                if(nx>=N || ny>=M || nx< 0 || ny< 0) continue;

                if(graph[nx][ny]==1 && Node1261.wall+1 <dist[nx][ny]){
                    dist[nx][ny]=Node1261.wall+1 ;
                    pq.offer(new Node1261(nx , ny , Node1261.wall+1 ));
                } else if(graph[nx][ny]==0 && Node1261.wall <dist[nx][ny]){
                    dist[nx][ny]=Node1261.wall ;
                    pq.offer(new Node1261(nx , ny , Node1261.wall));
                }
            }
        }
    }
}

class Node1261 {
    int x,y ;
    int wall ;

    public Node1261(int x,int y,int wall){
        this.x=x ;
        this.y=y ;
        this.wall=wall ;
    }
    
}
