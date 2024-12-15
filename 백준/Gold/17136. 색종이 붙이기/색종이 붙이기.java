import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	static int[][] graph;
	static int[] papers;
	static boolean[][] visited;
	static boolean check;
	static int result;

	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		graph = new int[10][10];
		visited = new boolean[10][10];
		papers = new int[6];

		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= 5; i++) {
			papers[i] = 5;
		}

		check = false;
		result = Integer.MAX_VALUE;

		dfs(0, 0);

		if (!check) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
	}

	private static void dfs(int index, int count) {
		if (count >= result) {
			return;
		}

		if (index >= 100) {
			check = true;
			result = count;
			return;
		}

		int x = index / 10;
		int y = index % 10;

		if (graph[x][y] == 0 || visited[x][y]) {
			dfs(index + 1, count);
			return;
		}

		for (int size = 5; size >= 1; size--) {
			if (checkCanPaper(x, y, size)) {
				papers[size]--;
				updateVisit(x, y, size, true);
				dfs(index + 1, count + 1);
				updateVisit(x, y, size, false);
				papers[size]++;
			}
		}
	}

	private static boolean checkCanPaper(int x, int y, int size) {
		if (papers[size] <= 0) {
			return false;
		}

		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (i >= 10 || j >= 10 || graph[i][j] == 0 || visited[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	private static void updateVisit(int x, int y, int size, boolean mark) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				visited[i][j] = mark;
			}
		}
	}
}
