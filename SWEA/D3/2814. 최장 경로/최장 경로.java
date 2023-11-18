import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static int[][] graph;
    static boolean[] visited;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            graph = new int[N + 1][N + 1];
            visited = new boolean[N + 1];
            max = 0;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[x][y] = graph[y][x] = 1;
            }

            for (int i = 1; i <= N; i++) {
                visited[i] = true;
                dfs(i, 1);
                visited[i] = false;
            }

            System.out.println("#" + t + " " + max);
        }
    }

    public static void dfs(int node, int count) {
        max = Math.max(max, count);

        for (int i = 1; i <= N; i++) {
            if (graph[node][i] == 1 && !visited[i]) {
                visited[i] = true;
                dfs(i, count + 1);
                visited[i] = false;
            }
        }
    }
}
