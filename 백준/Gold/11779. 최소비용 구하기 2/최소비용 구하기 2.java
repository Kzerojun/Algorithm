import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	static int n,m;
	static List<List<Info>> city;
	static int start, goal;


	public static void main(String[] args) throws IOException {
		init();
		simulate();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		city = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			city.add(new ArrayList<>());
		}
		StringTokenizer st;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			city.get(from).add(new Info(to, cost));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		goal = Integer.parseInt(st.nextToken());

	}

	private static void simulate() {
		PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> {
			return o1.cost - o2.cost;
		});

		int[] costs = new int[n+1];
		int[] track = new int[n+1];

		Arrays.fill(costs, 1_000_000_01);
		Arrays.fill(track, -1);
		costs[start] = 0;
		pq.add(new Info(start, 0));

		while (!pq.isEmpty()) {
			Info now = pq.poll();

			if (costs[now.to] < now.cost) {
				continue;
			}

			for (Info next : city.get(now.to)) {
				if (costs[next.to] > now.cost + next.cost) {
					track[next.to] = now.to;
					costs[next.to] = now.cost + next.cost;
					pq.add(new Info(next.to,now.cost+next.cost));
				}
			}
		}


		System.out.println(costs[goal]);

		List<Integer> path = new ArrayList<>();
		int current = goal;
		while (current != -1) {
			path.add(current);
			current = track[current];
		}

		Collections.reverse(path);

		System.out.println(path.size());
		StringBuilder sb = new StringBuilder();
		for (int city : path) {
			sb.append(city).append(" ");
		}
		System.out.println(sb.toString().trim());
	}
}

class Info{
	int to;
	int cost;

	Info(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}
}