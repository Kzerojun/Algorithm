import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int M , N, K;

    static int[][] graph;
    static boolean[][] visit;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    public static void main(String[]args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        visit = new boolean[N][M];
        int placecount = 0;
        ArrayList<Integer> areas = new ArrayList<Integer>();

        for(int i = 0; i< K; i++){
            st = new StringTokenizer(br.readLine());
            int leftx = Integer.parseInt(st.nextToken());
            int lefty = Integer.parseInt(st.nextToken());
            int rightx = Integer.parseInt(st.nextToken());
            int righty = Integer.parseInt(st.nextToken());

            for(int j = lefty; j<righty; j++){
                for(int k = leftx; k<rightx; k++){
                    graph[j][k] =1;
                }
            }
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(!visit[i][j]&&graph[i][j]==0){
                    areas.add(bfs(i,j));
                    placecount++;
                }
            }
        }
        System.out.println(placecount);

        Collections.sort(areas);
        for(int x :areas){
            System.out.print(x + " ");
        }
    }

    static int bfs(int x, int y){
        Queue<Node2583> queue = new LinkedList<>();
        queue.offer(new Node2583(x,y));
        int aresSize = 1;
        visit[x][y] = true;

        while(!queue.isEmpty()){
            Node2583 current = queue.poll();
            for(int i = 0; i<4; i++){
                int ddx = current.x + dx[i];
                int ddy = current.y + dy[i];
                if(ddx>=0 && ddy>=0 && ddx<N && ddy<M){
                    if(!visit[ddx][ddy] && graph[ddx][ddy] == 0){
                        queue.offer(new Node2583(ddx,ddy));
                        visit[ddx][ddy] = true;
                        aresSize++;
                    }
                }
            }

        }

        return aresSize;

    }
}

class Node2583{
    int x;
    int y;

    Node2583(int x, int y){
        this.x = x;
        this.y = y;

    }
}
