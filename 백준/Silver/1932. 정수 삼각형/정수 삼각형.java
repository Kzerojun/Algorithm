import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    static int[] dx = {1, 1};
    static int[] dy = {0, 1};
    static int[][] graph;
    static int[][] dp;
    static int sol = Integer.MIN_VALUE;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int counts = st.countTokens();
            for (int j = 0; j < counts; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = graph[0][0];
        move();


        for (int i = 0; i < n; i++) {
            sol = Math.max(sol, dp[n - 1][i]);
        }

        System.out.println(sol);
    }

    static void move() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0));

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int i = 0; i < 2; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;
                if(nx>=n || ny>= n) continue;

                if(dp[nx][ny]==0){
                    dp[nx][ny] = dp[now.x][now.y] + graph[nx][ny];
                    q.offer(new Point(nx, ny));
                }

                if (dp[nx][ny] != 0) {
                    if (dp[nx][ny] < dp[now.x][now.y] + graph[nx][ny]) {
                        dp[nx][ny] = dp[now.x][now.y] + graph[nx][ny];
                        q.offer(new Point(nx, ny));
                    }
                }
            }
        }


    }




}
class Point{
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;

    }
}