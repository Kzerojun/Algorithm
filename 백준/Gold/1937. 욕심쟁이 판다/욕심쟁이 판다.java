import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	static int[][] graph;
	static int[][] dp;
	static int n;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		graph = new int[n][n];
		dp = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}

		sol();
	}

	private static void sol() {
		int result = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result = Math.max(result,move(i,j));
			}
		}

		System.out.println(result);
	}

	private static int move(int x, int y) {
		if (dp[x][y] != -1) {
			return dp[x][y];
		}

		dp[x][y] = 1;

		for (int i = 0; i < 4; i++) {
			int nx = dx[i] + x;
			int ny = dy[i] + y;

			if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
				continue;
			}

			if (graph[nx][ny] <= graph[x][y]) {
				continue;
			}

			int next = move(nx, ny);
			dp[x][y] = Math.max(dp[x][y], next + 1);
		}

		return dp[x][y];
	}
}

class Location {

	int x;
	int y;

	int moveCount;

	Location(int x, int y, int moveCount) {
		this.x = x;
		this.y = y;
		this.moveCount = moveCount;
	}
}