import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] dp;
    static int[] trace;

    public static void main(String[] args) throws IOException {
        init();
        simulate(N);
        printResult();
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        trace = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            dp[i] = -1; // 초기화
        }
        dp[1] = 0; // 1은 연산 횟수가 0
    }

    public static int simulate(int x) {
        if (dp[x] != -1) {
            return dp[x];
        }

        dp[x] = simulate(x - 1) + 1;
        trace[x] = x - 1;

        if (x % 2 == 0) {
            int temp = simulate(x / 2) + 1;
            if (dp[x] > temp) {
                dp[x] = temp;
                trace[x] = x / 2;
            }
        }

        if (x % 3 == 0) {
            int temp = simulate(x / 3) + 1;
            if (dp[x] > temp) {
                dp[x] = temp;
                trace[x] = x / 3;
            }
        }

        return dp[x];
    }

    public static void printResult() {
        System.out.println(dp[N]);

        StringBuilder sb = new StringBuilder();
        int current = N;
        while (current > 0) {
            sb.append(current).append(" ");
            current = trace[current];
        }
        System.out.println(sb);
    }
}