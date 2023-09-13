import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] graph;
    static int[][] replication;
    static int N;
    static int M;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0); // 벽을 0개부터 3개까지 추가해보며 시뮬레이션
        System.out.println(max);
    }

    static void dfs(int wall) {
        if (wall == 3) {
            // 벽을 3개 추가한 후 바이러스 확산을 시뮬레이션
            replication = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    replication[i][j] = graph[i][j];
                }
            }
            bfs();
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 0) {
                    graph[i][j] = 1; // 벽 추가
                    dfs(wall + 1);
                    graph[i][j] = 0; // 벽 제거
                }
            }
        }
    }

    static void bfs() {
        Queue<Node> queue = new LinkedList<>();

        // 초기 바이러스 위치 큐에 추가
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (replication[i][j] == 2) {
                    queue.offer(new Node(i, j));
                }
            }
        }

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            int x = tmp.x;
            int y = tmp.y;

            for (int i = 0; i < 4; i++) {
                int ddx = x + dx[i];
                int ddy = y + dy[i];

                if (ddx >= 0 && ddy >= 0 && ddx < N && ddy < M && replication[ddx][ddy] == 0) {
                    replication[ddx][ddy] = 2;
                    queue.offer(new Node(ddx, ddy));
                }
            }
        }

        // 안전 영역 크기 계산
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (replication[i][j] == 0) {
                    count++;
                }
            }
        }

        max = Math.max(max, count);
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}