import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N, M;
	static List<List<Node>> nodes;

	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nodes = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			nodes.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			nodes.get(from).add(new Node(to, weight));
			nodes.get(to).add(new Node(from, weight));
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			sb.append(bfs(A, B)).append("\n");
		}

		System.out.print(sb);
	}

	private static int bfs(int start, int target) {
		Queue<Node> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		queue.add(new Node(start, 0));
		visited[start] = true;

		while (!queue.isEmpty()) {
			Node current = queue.poll();

			if (current.to == target) {
				return current.weight;
			}

			for (Node neighbor : nodes.get(current.to)) {
				if (!visited[neighbor.to]) {
					visited[neighbor.to] = true;
					queue.add(new Node(neighbor.to, current.weight + neighbor.weight));
				}
			}
		}

		return -1;
	}
}

class Node {
	int to;
	int weight;

	Node(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}
}
