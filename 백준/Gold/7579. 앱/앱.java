import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N][10001];

		st = new StringTokenizer(br.readLine());
		int[] memories = new int[N];

		for (int i = 0; i < N; i++) {
			int memory = Integer.parseInt(st.nextToken());
			memories[i] = memory;
		}

		int[] costs = new int[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int cost = Integer.parseInt(st.nextToken());
			costs[i] = cost;
		}
		int solution = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int cost = costs[i];
			int memory = memories[i];
			for (int j = 0; j < 10001; j++) {
				if(i==0) {
					if(j>=cost) {
						dp[i][j] = memory;
					}
				}else {
					if(j>=cost) {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost] + memory);
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}

				if (dp[i][j] >= M) {
					solution = Math.min(j, solution);
				}
			}
		}

		System.out.println(solution);

	}
}