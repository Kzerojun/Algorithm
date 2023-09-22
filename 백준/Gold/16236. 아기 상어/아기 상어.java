import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static int shark = 2;
    static Node16236 sharkLocation;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N;
    static int distance = 0;
    static int eat = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());

                if (num == 9) {
                    sharkLocation = new Node16236(i, j, 0);
                    graph[i][j] = 0;
                    continue;
                }
                graph[i][j] = num;
            }
        }

            bfs(sharkLocation);
            // System.out.println("지금 현재 거리총합:" + distance);
            // System.out.println("현재 샤크 사이즈: "+shark);


        System.out.println(distance);
    }

    static void bfs(Node16236 sharkStart) {
        while(true){


        PriorityQueue<Node16236> pq = new PriorityQueue<>((o1,o2)->{
            if(o1.distance!=o2.distance){
                return o1.distance - o2.distance;
            } else if(o1.x!=o2.x){
                return o1.x - o2.x;
            } else{
                return o1.y - o2.y;
            }
        });
        boolean[][] visit = new boolean[N][N];

        pq.offer(sharkStart);
        visit[sharkStart.x][sharkStart.y] = true;
        //System.out.println("상어 시작 위치: " + sharkStart.x + ", " + sharkStart.y);

        boolean ck = false;

        while(!pq.isEmpty()){
            Node16236 tmp = pq.poll();


            if(graph[tmp.x][tmp.y]<shark && graph[tmp.x][tmp.y]!=0){
                graph[tmp.x][tmp.y] = 0;
                eat++;
                ck = true;
                distance += tmp.distance;
                sharkLocation.x = tmp.x;
                sharkLocation.y = tmp.y;
                if(eat == shark){
                    shark++;
                    eat = 0;
                }
                break;
            }


            for(int  i = 0; i<4; i++){
                int ddx = tmp.x + dx[i];
                int ddy = tmp.y + dy[i];

                if(ddx>=0 && ddy>=0 && ddx<N && ddy<N){
                    if(graph[ddx][ddy]<=shark&&!visit[ddx][ddy]){
                        visit[ddx][ddy] = true;
                        pq.offer(new Node16236(ddx,ddy,tmp.distance+1));
                        //System.out.println("상어 현재 위치: " + ddx + ", " + ddy +"거리: "+ (tmp.distance+1));
                    }
                }
            }


        }
            if (!ck) // 큐가 비워질 때까지 먹이를 먹은적이 없다면, 더 이상 먹은 물고기가 없으므로 탈출
                break;
    }

    }


}

class Node16236 {
    int x;
    int y;
    int distance;

    Node16236(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}