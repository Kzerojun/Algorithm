import java.io.*;
import java.util.*;

public class Main {

    static int M;
    static int N;
    static int[][] graph;
    static int[][] dp;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        graph = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] =-1;
            }
        }

        System.out.println(dfs(0,0));
    }

    static int dfs(int x, int y) {
        if (x == M - 1 && y == N - 1) {
            return 1;
        }


        if(dp[x][y]!=-1){
            return dp[x][y];
        }else {
            dp[x][y] = 0;
            for (int i = 0; i < 4; i++) {
                int ddx = x + dx[i];
                int ddy = y + dy[i];
                if (ddx >= 0 && ddy >= 0 && ddx < M && ddy < N) {
                    if (graph[x][y] > graph[ddx][ddy]) {
                        dp[x][y] += dfs(ddx, ddy);
                    }
                }
            }
        }



        return dp[x][y];
    }
}