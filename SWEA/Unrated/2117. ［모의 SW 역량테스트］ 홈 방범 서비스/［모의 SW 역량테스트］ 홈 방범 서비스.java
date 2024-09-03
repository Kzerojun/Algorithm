import java.util.*;
import java.io.*;

public class Solution {
    static BufferedReader br;
    static int T;
    static int N, M;
    static int[][] graph;
    static int sol;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        init();
        simulateEachCase();
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
    }

    private static void initEachCase() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void simulateEachCase() throws IOException {
        for (int t = 1; t <= T; t++) {
            initEachCase();
            simulate();
            System.out.println("#" + t + " " + sol);
        }
    }

    private static void simulate() {
        int k = 1;
        sol = Integer.MIN_VALUE;
        while (k < N+2) {
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    int homeCount = cal(k, x, y);
                    if (homeCount > 0) {
                        sol = Math.max(sol, homeCount);
                    }
                }
            }

            k++;
        }
    }

    private static int cal(int k, int x, int y) {
        Queue<Location> q = new LinkedList<>();
        q.add(new Location(x, y));
        boolean[][] visited = new boolean[N][N];
        visited[x][y] = true;

        int houseCount = 0;

        while (!q.isEmpty()) {
            Location now = q.poll();

            if (graph[now.x][now.y] == 1) {
                houseCount++;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])
                    continue;
                if (Math.abs(x - nx) + Math.abs(y - ny) < k ) {
                    visited[nx][ny] = true;
                    q.add(new Location(nx, ny));
                }
            }
        }

        int serviceCost = k * k + ((k - 1) * (k - 1));
        int homeMoney = houseCount * M;

        return (homeMoney - serviceCost >= 0) ? houseCount : -1;
    }
}

class Location {
    int x;
    int y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
