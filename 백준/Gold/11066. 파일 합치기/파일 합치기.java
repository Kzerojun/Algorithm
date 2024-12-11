import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	static int[] files;
	static int[][] dp;
	static int[] prefixSum;

	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			int K = Integer.parseInt(br.readLine());
			files = new int[K];
			dp = new int[K][K];
			prefixSum = new int[K + 1];

			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < K; i++) {
				files[i] = Integer.parseInt(st.nextToken());
				prefixSum[i + 1] = files[i] + prefixSum[i];
			}

			for (int i = 0; i < K; i++) {
				Arrays.fill(dp[i], -1);
			}

			System.out.println(sol(0, K - 1));

		}

	}

	private static int sol(int i, int j) {
		if (i == j) {
			return 0;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		dp[i][j] = Integer.MAX_VALUE;

		for (int k = i; k < j; k++) {
			int left = sol(i, k);
			int right = sol(k + 1, j);
			int sum = sum(i, j );

			dp[i][j] = Math.min(dp[i][j], left + right + sum);
		}

		return dp[i][j];
	}

	private static int sum(int i, int j) {
		return prefixSum[j + 1] - prefixSum[i];
	}
}