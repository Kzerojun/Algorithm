import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	static List<List<Integer>> nodes;
	static int N;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		init();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		nodes = new ArrayList<>();
		dp = new int[N + 1][2];

		for (int i = 0; i <= N; i++) {
			nodes.add(new ArrayList<>());
			Arrays.fill(dp[i], -1);
		}

		for (int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			nodes.get(from).add(to);
			nodes.get(to).add(from);
		}

		int result = Math.min(dp(1, 0, 0), dp(1, 1, 0));
		System.out.println(result);
	}

	private static int dp(int index, int check, int parent) {
		//리프 노드 일때 얼답이라면 1 아니라면 0
		if (nodes.get(index).size() == 1 && index != 1) {
			return check == 1 ? 1 : 0;
		}

		if (dp[index][check] != -1) {
			return dp[index][check];
		}

		dp[index][check] = check == 1 ? 1 : 0;

		for (int next : nodes.get(index)) {
			if (next != parent) {
				//내가 얼리어답터가 아니면 다음 친구는 무조건 얼리어 답터
				if (check == 0) {
					int result = dp(next, 1, index);
					dp[index][check] += result;
				} else {

					//내가 얼답이라면 다음 친구는 얼답이어도 아니어도 된다.
					dp[index][check] += Math.min(dp(next, 0, index), dp(next, 1, index));
				}
			}
		}

		return dp[index][check];
	}
}
