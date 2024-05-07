import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	static int N,M;
	static int[] parent;

	static List<Node> bridges;

	static int[] sol = new int[2];


	public static void main(String[] args) throws IOException {
		init();
		calculate();

	}

	private static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new int[N+1];

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		bridges = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			bridges.add(new Node(from, to, weight));
		}

		st = new StringTokenizer(br.readLine());

		sol[0] = Integer.parseInt(st.nextToken());
		sol[1] = Integer.parseInt(st.nextToken());



	}

	static void calculate() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
			return o2.weight - o1.weight;
		});

		pq.addAll(bridges);

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (find(now.from) == find(now.to)) {
				continue;
			}

			union(now.from, now.to);
			
			if (find(sol[0]) == find(sol[1])) {  // 공장이 있는 두 섬이 연결되었다면
				System.out.println(now.weight);  // 현재 다리의 중량을 출력하고 종료
				return;
			}

		}
	}

	static void union(int x, int y) {
		int a = find(x);
		int b = find(y);

		if(a == b) return;

		if (a < b) {
			parent[b] = a;
		}else {
			parent[a] = b;
		}
	}

	static int find(int number) {
		if(parent[number]== number) return number;
		return parent[number] = find(parent[number]);
	}
}

class Node {

	int from;
	int to;
	int weight;

	Node(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}
