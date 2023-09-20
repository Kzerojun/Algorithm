import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] graph;
    
    static int[][][] visit;

    static int solution = Integer.MAX_VALUE;
    static int N;
    static int M;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static public void main(String[]args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        visit = new int[N][M][2]; //벽을 부순 경우 , 안부순 경우
        
        for(int i = 0; i<N; i++){
            String number = br.readLine();
            for(int j = 0; j<M; j++){
                char ch = number.charAt(j);
                graph[i][j] = Character.getNumericValue(ch);
            }
        }
        bfs();
        
    }
    

    static void bfs(){
        Queue<Node2206> queue = new LinkedList<>();

        queue.offer(new Node2206(0,0,1,0));
        visit[0][0][0] = 1;
        visit[0][0][1] = 1;

        int count = 0;
        boolean found = false;

        while(!queue.isEmpty()){
            for(int i = 0; i< queue.size(); i++){
                Node2206 current = queue.poll();

                if (current.x == N-1 && current.y == M-1) {
                    solution = Math.min(solution, current.distance);
                    continue;
                }

                for(int j = 0; j<4; j++){
                    int ddx = current.x + dx[j];
                    int ddy = current.y + dy[j];
                    if(ddx>=0 && ddy>=0 && ddx<N && ddy<M){
                        if(graph[ddx][ddy]==0){ //벽을 안뚫는 경우
                            if(visit[ddx][ddy][current.wall] == 0){
                                visit[ddx][ddy][current.wall] = current.distance+1;
                                queue.offer(new Node2206(ddx,ddy, current.distance+1, current.wall ));
                            }
                        }else{ // 벽을 뚫는 경우
                            if(current.wall == 0 && visit[ddx][ddy][1]==0){
                                visit[ddx][ddy][1] = current.distance+1;
                                queue.offer(new Node2206(ddx,ddy,current.distance+1,1));
                            }
                        }
                    }
                }


            }

        }

        if (solution == Integer.MAX_VALUE) {
            System.out.println(-1); // 도달할 수 없는 경우
        } else {
            System.out.println(solution);
        }

    }

}

class Node2206{
    int x;
    int y;
    int wall;
    int distance;
    
    Node2206(int x, int y, int distance, int wall){
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.wall = wall;
    }
}
