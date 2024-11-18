import java.io.*;
import java.util.*;

class Main {
	static int T, n, m, t, s, g, h;
	static List<List<Road>> graph;
	static int[] dist;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int testcase = 0; testcase < T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			// 그래프 초기화
			graph = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				graph.add(new ArrayList<>());
			}

			// 간선 정보 입력
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				graph.get(a).add(new Road(b, d));
				graph.get(b).add(new Road(a, d));
			}

			// 목적지 후보 입력
			List<Integer> destinations = new ArrayList<>();
			for (int i = 0; i < t; i++) {
				destinations.add(Integer.parseInt(br.readLine()));
			}

			// 결과 저장
			List<Integer> results = new ArrayList<>();
			for (int dest : destinations) {
				long route1 = dijkstra(s, g) + dijkstra(g, h) + dijkstra(h, dest);
				long route2 = dijkstra(s, h) + dijkstra(h, g) + dijkstra(g, dest);
				long direct = dijkstra(s, dest);

				if (Math.min(route1, route2) == direct) {
					results.add(dest);
				}
			}

			// 결과 정렬 및 출력
			Collections.sort(results);
			for (int result : results) {
				sb.append(result).append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	private static int dijkstra(int start, int end) {
		// 메모리 재사용을 위한 초기화
		dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		PriorityQueue<Road> pq = new PriorityQueue<>((o1,o2)->{
			return o1.weight - o2.weight;
		});

		pq.add(new Road(start, 0));

		while (!pq.isEmpty()) {
			Road current = pq.poll();
			int now = current.to;

			if (dist[now] < current.weight) {
				continue;
			}

			for (Road next : graph.get(now)) {
				if (dist[next.to] > dist[now] + next.weight) {
					dist[next.to] = dist[now] + next.weight;
					pq.add(new Road(next.to, dist[next.to]));
				}
			}
		}

		return dist[end];
	}
}

class Road {
	int to, weight;

	Road(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}
}
