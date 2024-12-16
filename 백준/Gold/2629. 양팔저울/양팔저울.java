import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	static boolean[][] dp;
	static int[] weights;
	static int N;

	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		weights = new int[N];
		dp = new boolean[N + 1][15_001];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}

		dp(0, 0);

		int K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < K; i++) {
			int newWeight = Integer.parseInt(st.nextToken());

			if (newWeight > 15000) {
				sb.append("N").append(" ");
			} else {
				if (dp[N][newWeight]) {
					sb.append("Y").append(" ");
				} else {
					sb.append("N").append(" ");
				}
			}

		}

		System.out.println(sb.toString());
	}

	private static void dp(int index, int weight) {
		if (dp[index][weight]) {
			return;
		}

		dp[index][weight] = true;

		if (index == N) {
			return;
		}

		dp(index + 1, weight);
		dp(index + 1, weight + weights[index]);
		dp(index + 1, Math.abs(weight - weights[index]));
	}
}
