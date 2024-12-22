import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	static int N, M;
	static List<List<Integer>> nodes;

	static int[] parent;
	static int[] depth;

	public static void main(String[] args) throws IOException {
		init();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		parent = new int[N + 1];
		depth = new int[N + 1];

		nodes = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			nodes.add(new ArrayList<>());
			parent[i] = i;
		}

		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			nodes.get(from).add(to);
			nodes.get(to).add(from);
		}

		M = Integer.parseInt(br.readLine());

		dfs(1, 0);

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			calculate(A,B);
		}
	}

	private static void dfs(int node, int before) {
		if (parent[node] != node) {
			return;
		}

		parent[node] = before;
		depth[node] = depth[before] + 1;

		for (int next : nodes.get(node)) {
			if (next != before) {
				dfs(next, node);
			}
		}
	}

	private static void calculate(int A, int B) {
		int aDepth = depth[A];
		int bDepth = depth[B];

		if (aDepth < bDepth) {
			int diffrence = bDepth - aDepth;

			for (int i = 0; i < diffrence; i++) {
				B = parent[B];
			}


		} else if(aDepth > bDepth){
			int diffrence = aDepth - bDepth;

			for (int i = 0; i < diffrence; i++) {
				A = parent[A];
			}
		}


		while (true) {
			if (isSame(A, B)) {
				break;
			}

			A = parent[A];
			B = parent[B];
		}
		System.out.println(A);
	}

	private static boolean isSame(int A, int B) {
		return A == B;
	}

}