import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] tomato;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int N;
    static int M;
    public static void main(String[]args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        tomato = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int date = bfs();

        for(int i = 0; i< N; i ++){
            for(int j = 0; j<M; j++){
                if(tomato[i][j]==0){
                    System.out.println(-1);
                    return;
                }

            }
        }

        System.out.println(date);

    }

    static int bfs(){
        Queue<Pair1> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomato[i][j] == 1) {
                    queue.offer(new Pair1(i, j, 0));
                }
            }
        }

        int days = 0;


        while(!queue.isEmpty()){
            Pair1 tmp = queue.poll();

            for(int i = 0; i< 4; i++){
                int nextx = tmp.x +dx[i];
                int nexty = tmp.y +dy[i];
                days = tmp.days;

                if(nextx>=0 && nexty>=0 && nextx<N && nexty <M && tomato[nextx][nexty]==0) {
                    tomato[nextx][nexty]=1;
                    queue.offer(new Pair1(nextx,nexty,days+1));
                }
            }
        }

        return days;
    }
}

class Pair1{
    int x;
    int y;
    int days;
    Pair1(int x, int y,int days){
        this.x = x;
        this.y =y;
        this.days = days;
    }
}