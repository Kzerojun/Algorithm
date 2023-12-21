import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N;
    static int[][] graph;

    static int[] dx = {1, 1, 1};
    static int[] dy = {-1, 0, 1};
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        graph = new int[N][3];
        dp = new int[2][N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 3; i++) {
            dp[0][0][i] = graph[0][i];
            dp[1][0][i] = graph[0][i];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[1][i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx<0 || ny<0 || nx>=N || ny>=3) continue;
                    dp[0][nx][ny] = Math.max(dp[0][nx][ny], dp[0][i][j] + graph[nx][ny]);
                    dp[1][nx][ny] = Math.min(dp[1][nx][ny], dp[1][i][j] + graph[nx][ny]);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            max = Math.max(dp[0][N - 1][i],max);
            min = Math.min(dp[1][N - 1][i],min);
        }

        System.out.println(max + " " + min);

    }






}

