import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;
    static int tc;
    static int[][] testCases;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < tc; i++) {
            int n = testCases[i][0]; // 서쪽
            int m = testCases[i][1]; // 동쪽

            System.out.println(simulate(m, n));
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp = new int[30][30];

        tc = Integer.parseInt(br.readLine());
        testCases = new int[tc][2];

        for (int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            testCases[i][0] = Integer.parseInt(st.nextToken()); // 서쪽
            testCases[i][1] = Integer.parseInt(st.nextToken()); // 동쪽
        }
    }

    static int simulate(int n, int r) {
        if (dp[n][r] > 0) {
            return dp[n][r];
        }

        if (n == r || r == 0) {
            return dp[n][r] = 1;
        }

        return dp[n][r] = simulate(n - 1, r - 1) + simulate(n - 1, r);
    }
}