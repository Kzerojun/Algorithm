import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{

	static int[] priority;
	static int N;

	static List<List<Integer>> nodes;

	static int[] costs;

	static int[] result;




	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate()  throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		nodes = new ArrayList<>();
		costs = new int[N + 1];
		priority = new int[N + 1];
		result = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			nodes.add(new ArrayList<>());
		}

		for (int i = 1; i <= N; i++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			int cost = Integer.parseInt(st.nextToken());

			costs[i] = cost;

			while (true) {
				int nodeNumber = Integer.parseInt(st.nextToken());

				if (nodeNumber == -1) {
					break;
				}

				nodes.get(nodeNumber).add(i);
				priority[i]++;
			}
		}

		cal();

		for (int i = 1; i <= N; i++) {
			System.out.println(result[i]);
		}


	}

	private static void cal() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->{
			return o1.cost - o2.cost;
		});

		for (int i = 1; i <= N; i++) {
			if (priority[i] == 0) {
				pq.add(new Node(i, costs[i]));
			}
		}

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			result[now.number] = Math.max(result[now.number], now.cost);

			for (int next : nodes.get(now.number)) {

				priority[next]--;

				if (priority[next] == 0) {
					pq.add(new Node(next, now.cost + costs[next]));
				}
			}
		}





	}
}

class Node{

	int number;
	int cost;

	Node(int number, int cost) {
		this.number = number;
		this.cost = cost;
	}

}