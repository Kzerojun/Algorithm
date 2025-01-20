import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] T;
    private static int[] P;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        initialize();
        int result = simulate();
        System.out.print(result);
    }


    private static void initialize() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(bf.readLine());
        T = new int[N + 1];
        P = new int[N + 1];
        dp = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
    }


    private static int simulate() {
        int[] MAX = new int[2];

        for (int i = 1; i <= N; i++) {
            int next = i + T[i];

            if (MAX[1] <= dp[i]) {
                MAX[0] = i;
                MAX[1] = dp[i];
            }

            if (next <= N + 1) {
                if (next > MAX[0]) {
                    dp[next] = Math.max(dp[next], MAX[1] + P[i]);
                } else {
                    dp[next] = Math.max(dp[next], dp[i] + P[i]);
                }
            }
        }

        int result = 0;
        for (int i : dp) {
            result = Math.max(result, i);
        }
        return result;
    }
}