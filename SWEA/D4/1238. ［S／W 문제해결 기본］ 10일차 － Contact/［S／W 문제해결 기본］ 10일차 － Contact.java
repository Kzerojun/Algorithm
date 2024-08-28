import java.util.*;
import java.io.*;

public class Solution {
	static BufferedReader br;
	static int n, startNumber;
	static List<List<Integer>> nodes;
	static boolean[] visited;
	static List<Node> callNodes;

	public static void main(String[] args) throws IOException {
		init();
		startEachCase();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	private static void startEachCase() throws IOException {
		for (int t = 1; t <= 10; t++) {
			initEachCase();
			simulate();

			callNodes.sort((o1, o2) -> {
				if (o2.moveCount == o1.moveCount) {
					return o2.number - o1.number;
				}
				return o2.moveCount - o1.moveCount;
			});

			Node result = callNodes.get(0);
			System.out.println("#" + t + " " + result.number);
		}
	}

	private static void initEachCase() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		startNumber = Integer.parseInt(st.nextToken());
		visited = new boolean[101];
		nodes = new ArrayList<>();
		callNodes = new ArrayList<>();

		for (int i = 0; i <= 100; i++) {
			nodes.add(new ArrayList<>());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n/2; i++) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			nodes.get(from).add(to);
		}

	}

	private static void simulate() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
			return o1.moveCount - o2.moveCount;
		});

		Node startNode = new Node(startNumber, 0);
		pq.add(startNode);
		callNodes.add(startNode);
		visited[startNumber] = true;

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			for (int next : nodes.get(now.number)) {
				if (!visited[next]) {
					Node nextNode = new Node(next, now.moveCount + 1);
					visited[next] = true;
					pq.add(nextNode);
					callNodes.add(nextNode);
				}
			}
		}
	}

}

class Node {
	int number;
	int moveCount;

	Node(int number, int moveCount) {
		this.number = number;
		this.moveCount = moveCount;
	}
}
