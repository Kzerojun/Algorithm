import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N, M;
	static List<List<Integer>> graph;
	static int[] inDegree;
	static int[] semester; 

	public static void main(String[] args) throws IOException {
		init();
		calculateSemesters();
		printResults();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		inDegree = new int[N + 1];
		semester = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			graph.get(from).add(to);
			inDegree[to]++;
		}
	}

	private static void calculateSemesters() {
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
				semester[i] = 1;
			}
		}

		while (!queue.isEmpty()) {
			int current = queue.poll();

			for (int next : graph.get(current)) {
				inDegree[next]--;

				if (inDegree[next] == 0) {
					queue.add(next);
					semester[next] = semester[current] + 1;
				}
			}
		}
	}

	private static void printResults() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(semester[i]).append(" ");
		}
		System.out.println(sb.toString().trim());
	}
}
