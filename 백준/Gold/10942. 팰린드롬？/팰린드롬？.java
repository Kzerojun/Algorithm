import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		int[][] dp = new int[N][N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i][i] = 1;
		}

		for (int i = 0; i < N - 1; i++) {
			if (arr[i] == arr[i + 1]) {
				dp[i][i + 1] = 1;
			}
		}

		for (int k = 2; k < N; k++) {
			for (int i = 0; i + k < N; i++) {
				int j = i + k;
				if (arr[i] == arr[j] && dp[i + 1][j - 1] == 1) {
					dp[i][j] = 1;
				}
			}
		}

		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken())-1;
			int E = Integer.parseInt(st.nextToken())-1;

			bw.write(dp[S][E] + "\n");
		}

		bw.flush();
		bw.close();
	}
}
