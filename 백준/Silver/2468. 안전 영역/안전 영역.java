import java.io.*;
import java.util.*;

class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N;
    static int[][] heightMap;
    static boolean[][] visited;
    static int max = -1; // 높이의 최소값으로 초기화
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        heightMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                max = Math.max(max, num);
                heightMap[i][j] = num;
            }
        }

        for (int h = 0; h <= max; h++) {
            visited = new boolean[N][N];
            int safeAreas = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && heightMap[i][j] > h) {
                        bfs(i, j, h);
                        safeAreas++;
                    }
                }
            }
            result = Math.max(result, safeAreas);
        }

        System.out.println(result);
    }

    static void bfs(int x, int y, int h) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ddx = tmp.x + dx[i];
                int ddy = tmp.y + dy[i];

                if (ddx >= 0 && ddx < N && ddy >= 0 && ddy < N && !visited[ddx][ddy] && heightMap[ddx][ddy] > h) {
                    queue.offer(new Node(ddx, ddy));
                    visited[ddx][ddy] = true;
                }
            }
        }
    }
}