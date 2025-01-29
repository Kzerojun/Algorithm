import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] graph;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException {
        init();
        simulate();
    }
    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N + 2][M + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void simulate() {
        int time = 0;
        while (!isFinish()) {
            time++;
            move();
        }
        System.out.println(time);
    }

    private static boolean isFinish() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void move() {
        Queue<Location> q = new LinkedList<>();
        q.add(new Location(0, 0));
        int[][] visited = new int[N + 2][M + 2];
        visited[0][0] = -1;

        while (!q.isEmpty()) {
            Location now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N + 2 || ny >= M + 2) continue;
                if (visited[nx][ny] == -1) continue;

                if (graph[nx][ny] == 1) {
                    visited[nx][ny]++;
                } else {
                    visited[nx][ny] = -1;
                    q.add(new Location(nx, ny));
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (graph[i][j] == 1 && visited[i][j] >= 2) {
                    graph[i][j] = 0;
                }
            }
        }
    }
}

class Location {
    int x, y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
