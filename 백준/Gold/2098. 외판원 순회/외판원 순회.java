
import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] W;
	static int[][] dp;
	static int INF = 16_000_000;

	public static void main(String[] args) throws IOException {
		init();
		int result = dfs(0, 1);
		System.out.println(result);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		W = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 행 city 열 비트마스킹
		dp = new int[N][1 << N];

		for (int[] arr : dp) {
			Arrays.fill(arr, -1);
		}
	}

	private static int dfs(int city, int visit) {

		if (visit == (1 << N) - 1) {
			if (W[city][0] != 0) {
				return W[city][0];
			} else {
				return INF;
			}
		}
		
		if(dp[city][visit]!=-1) {
			return dp[city][visit];
		}

		dp[city][visit] = INF;

		for (int i = 0; i < N; i++) {
			if (W[city][i] != 0 && (visit & 1 << i) == 0)
				dp[city][visit] = Math.min(dp[city][visit], dfs(i, (1 << i | visit)) + W[city][i]);

		}

		return dp[city][visit];

	}
}
