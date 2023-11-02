import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, D;
    static int[][] graph;
    static int max = 0;
    static List<Enemy> enemies = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j] == 1){
                    enemies.add(new Enemy(i,j));
                }
            }
        }

        MakeArcher(0, 0);
        System.out.println(max);
    }


    static void MakeArcher(int index, int count) {
        if (count == 3) {
            KillEnemy();

            return;
        }

        for (int i = index; i < M; i++) {
            graph[N][i] = 2;
            MakeArcher(i + 1, count + 1);
            graph[N][i] = 0;
        }
    }



    static void KillEnemy(){
        int[][] repliMap = new int[N+1][M];
        int killCount = 0;

        for(int i = 0; i<=N; i++){
            repliMap[i] = graph[i].clone();
        }

        while(true) {
            List<Enemy> killList = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                if (repliMap[N][i] == 2) {
                    PriorityQueue<EnemyDistance> kill = new PriorityQueue<>(((o1, o2) -> {
                        if (o1.distnace == o2.distnace) {
                            return o1.y - o2.y;
                        }
                        return o1.distnace - o2.distnace;
                    }));

                    for(int x = 0; x<N; x++){
                        for(int y = 0; y<M; y++){
                            if(repliMap[x][y] == 1){
                                int distance = Math.abs((x-N)) + Math.abs((y-i));
                                if(distance <= D) {
                                    kill.add(new EnemyDistance(x, y, distance));
                                }
                            }
                        }
                    }

                    if (!kill.isEmpty()) {
                        EnemyDistance now = kill.poll();
                        killList.add(new Enemy(now.x, now.y));
                    }
                }
            }

            for(Enemy enemy : killList){
                if(repliMap[enemy.x][enemy.y] == 1) {
                    repliMap[enemy.x][enemy.y] = 0;
                    killCount++;
                }
            }

            if(!EnemyMove(repliMap)) {
                break;
            }
        }

        max = Math.max(killCount, max);
    }

    static boolean EnemyMove(int[][] repliMap){
        boolean check = false;

        for(int i = N-1; i >= 0; i--){
            for (int j = 0; j<M; j++){
                if(repliMap[i][j] ==1){
                    repliMap[i+1][j] = 1;
                    repliMap[i][j] = 0;
                    check = true;
                }
            }
        }


        return check;
    }



}
class Enemy {
    int x;
    int y;

    Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class EnemyDistance{
    int x;
    int y;
    int distnace;

    EnemyDistance(int x, int y, int distance){
        this. x= x;
        this.y = y;
        this.distnace = distance;
    }
}
