import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] heights;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        heights = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                heights[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxSafeArea = 0;
        for (int i = 0; i <= 100; i++) {
            // 각 높이별로 BFS를 수행하여 안전한 영역의 개수를 구함
            int safeArea = getSafeArea(i);
            maxSafeArea = Math.max(maxSafeArea, safeArea);
        }

        System.out.println(maxSafeArea);
    }

    // BFS로 안전한 영역 개수 계산
    static int getSafeArea(int heightLimit) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int safeArea = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && heights[i][j] > heightLimit) {
                    bfs(i, j, heightLimit, dx, dy);
                    safeArea++;
                }
            }
        }

        // visited 배열 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        return safeArea;
    }

    static void bfs(int x, int y, int heightLimit, int[] dx, int[] dy) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = current[0] + dx[dir];
                int ny = current[1] + dy[dir];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N &&
                        !visited[nx][ny] && heights[nx][ny] > heightLimit) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }
}
