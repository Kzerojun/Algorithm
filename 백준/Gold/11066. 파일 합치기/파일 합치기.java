import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	static int[][] dp;

	static int[] prefixSum;
	static int[] files;

	public static void main(String[] args) throws IOException{
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			int K = Integer.parseInt(br.readLine());
			files = new int[K];
			prefixSum = new int[K+1];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < K; j++) {
				files[j] = Integer.parseInt(st.nextToken());
				prefixSum[j+1] = prefixSum[j] + files[j];
			}

			dp = new int[K][K];

			for (int j = 0; j < K; j++) {
				Arrays.fill(dp[j],-1);
			}

			int result = calculate(0,K-1);

			System.out.println(result);

		}

	}

	private static int calculate(int i, int j) {

		if (i == j) {
			return 0;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		dp[i][j] = Integer.MAX_VALUE;

		for (int k = i; k < j; k++) {
			int left = calculate(i, k);
			int right = calculate(k + 1, j);
			int sum = sum(i, j);
			dp[i][j] = Math.min(dp[i][j], left + right+sum);
		}

		return dp[i][j];
	}
	
	static int sum(int i, int j) {
		return prefixSum[j+1] - prefixSum[i];
	}
}