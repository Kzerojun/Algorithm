import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	static int N, M;
	static List<Node> nodes;

	static List<Line> lines;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nodes = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			nodes.add(new Node(i, x, y));
		}

		parent = new int[N];

		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;

			union(a, b);
		}

		lines = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				double distance = calDistance(i, j);
				lines.add(new Line(i, j, distance));
			}
		}

		sol();
	}

	private static void sol() {
		PriorityQueue<Line> pq = new PriorityQueue<>((o1, o2) -> {
			return Double.compare(o1.weight,o2.weight);
		});

		pq.addAll(lines);

		double cost = 0;
		while (!pq.isEmpty()) {
			Line line = pq.poll();

			if (find(line.to) == find(line.from)) {
				continue;
			}

			union(line.to, line.from);
			cost += line.weight;
		}

		System.out.printf("%.2f",cost);
	}

	private static double calDistance(int from, int to) {
		Node a = nodes.get(from);
		Node b = nodes.get(to);

		return Math.sqrt(Math.pow(b.y-a.y,2)+Math.pow(b.x-a.x,2));
	}


	private static void union(int a, int b) {
		int x = find(a);
		int y = find(b);

		if (x == y) {
			return;
		}

		if (x < y) {
			parent[x] = y;
		}else {
			parent[y] = x;
		}
	}

	private static int find(int number) {
		if(parent[number]==number)return number;
		return parent[number] = find(parent[number]);
	}
}


class Line{
	int from;
	int to ;

	double weight;

	Line(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}
class Node {

	int number;

	int x;
	int y;

	Node(int number, int x, int y) {
		this.number = number;
		this.x = x;
		this.y = y;
	}
}
