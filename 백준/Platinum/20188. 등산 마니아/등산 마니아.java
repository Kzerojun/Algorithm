import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] trees;
	static List<List<Integer>> nodes;

	public static void main(String[] args) throws Exception {
		init();
		int result = simulate();
		printResult(result);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nodes = new ArrayList<>();
		trees = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			nodes.add(new ArrayList<>());
			trees[i] = -1;
		}

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			nodes.get(from).add(to);
			nodes.get(to).add(from);
		}

	}

	private static int simulate() {
		int result = 0;

		dfs(1, -1);

		for (int i = 2; i <= N; i++) {
			int rest = N - trees[i];
			result += comb(N) - comb(rest);
		}

		return result;
	}
	static int comb(int target) {
		return (target * (target - 1)) /2;
	}

	private static void dfs(int child, int parent) {
		if (trees[child] != -1) {
			return;
		}

		trees[child] = 1;

		for (int next : nodes.get(child)) {
			if (next != parent) {
				dfs(next, child);
				trees[child] += trees[next];
			}
		}
	}


	private static void printResult(int result) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(result + " ");
		bw.flush();
		bw.close();
	}

}
