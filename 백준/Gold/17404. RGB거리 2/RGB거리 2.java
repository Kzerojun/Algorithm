import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] cost = new int[N][3];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = Integer.MAX_VALUE;
		for (int first = 0; first < 3; first++) {
			int[][] dp = new int[N][3];
			for (int i = 0; i < 3; i++) {
				if (i == first) {
					dp[0][i] = cost[0][i];
				} else {
					dp[0][i] = 1001;
				}

			}

			for (int i = 1; i < N; i++) {
				dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
				dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
				dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
			}

			for (int i = 0; i < 3; i++) {
				if (i != first) {
					answer = Math.min(answer, dp[N - 1][i]);
				}
			}
		}

		System.out.println(answer);
	}
}
