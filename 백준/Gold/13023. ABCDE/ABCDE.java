import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	static int n, m;
	static List<List<Integer>> nodes;
	static boolean result;

	public static void main(String[] args) throws IOException {
		init();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		nodes = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			nodes.add(new ArrayList<>());
		}

		// 간선 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			nodes.get(from).add(to);
			nodes.get(to).add(from);
		}

		result = false;

		for (int i = 0; i < n; i++) {
			boolean[] visited = new boolean[n];
			visited[i] = true;
			dfs(i, 0, visited);
			if (result) break;
		}
		System.out.println(result ? 1 : 0);
	}

	private static void dfs(int index, int length, boolean[] visited) {
		if (result) return;

		if (length == 4) { 
			result = true;
			return;
		}

		for (int next : nodes.get(index)) {
			if (!visited[next]) {
				visited[next] = true;
				dfs(next, length + 1, visited);
				visited[next] = false; 
			}
		}
	}
}
