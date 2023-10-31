import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] cost;
    static int[][] dp;

    static final int RED = 0;
    static final int GREEN = 1;
    static final int BLUE = 2;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        cost = new int[N][3];
        dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][RED] = Integer.parseInt(st.nextToken());
            cost[i][GREEN] = Integer.parseInt(st.nextToken());
            cost[i][BLUE] = Integer.parseInt(st.nextToken());
        }

        dp[0][RED] = cost[0][RED];
        dp[0][GREEN] = cost[0][GREEN];
        dp[0][BLUE] = cost[0][BLUE];

        for(int i = 1; i < N; i++) {
            dp[i][RED] = Math.min(dp[i-1][GREEN], dp[i-1][BLUE]) + cost[i][RED];
            dp[i][GREEN] = Math.min(dp[i-1][RED], dp[i-1][BLUE]) + cost[i][GREEN];
            dp[i][BLUE] = Math.min(dp[i-1][RED], dp[i-1][GREEN]) + cost[i][BLUE];
        }

        System.out.print(Math.min(Math.min(dp[N-1][RED], dp[N-1][GREEN]), dp[N-1][BLUE]));
    }
}
