import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	static int[][] graph;
	static int N, M;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};

	static int[] parent;

	static List<Bridge> bridges;
	static int cost = 0;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int groupNumber = 1;
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && graph[i][j] == 1) {
					makeGroup(new Point(i, j), visited, groupNumber);
					groupNumber++;
				}
			}
		}

		bridges = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (graph[i][j] != 0) {
//					System.out.println("시작위치"+i+" "+j);
					visited[i][j] = true;
					makeBridge(new Point(i, j), graph[i][j]);
				}
			}
		}

		parent = new int[groupNumber];
		for (int i = 1; i < groupNumber; i++) {
			parent[i] = i;
		}

		calculate();

		for (int i = 1; i < groupNumber; i++) {
			find(i);
		}

		boolean check = true;
		for (int i = 1; i < groupNumber; i++) {
			if (parent[i] != 1) {
				check = false;
			}
		}

		if (check) {
			System.out.println(cost);
		}else {
			System.out.println(-1);
		}

	}

	static void calculate() {
		PriorityQueue<Bridge> pq = new PriorityQueue<>((o1,o2)->{
			return o1.cost - o2.cost;
		});

		pq.addAll(bridges);

		while (!pq.isEmpty()) {
			Bridge now = pq.poll();


			if (find(now.fromIsland) == find(now.toIsland)) {
				continue;
			}

			union(now.fromIsland, now.toIsland);
			cost += now.cost;
		}
	}

	static void union(int x, int y) {
		int a = find(x);
		int b = find(y);

		if (a == b) {
			return;
		}

		if (a < b) {
			parent[b] = a;
		}else {
			parent[a] = b;
		}
	}

	static int find(int number) {
		if(parent[number]==number) return number;
		return parent[number] = find(parent[number]);
	}

	public static void makeBridge(Point start, int startGroupNumber) {

		for (int i = 0; i < 4; i++) {
			int nx = start.x;
			int ny = start.y;
			int cost = 0;
			// 현재 방향으로 쭉 진행
			while (true) {
				nx += dx[i];
				ny += dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || graph[nx][ny] == startGroupNumber) {
					break; // 조건에 맞지 않으면 해당 방향의 진행 중단
				}

				if (graph[nx][ny] != 0) {
					if (cost >=2) {
						bridges.add(new Bridge(startGroupNumber, graph[nx][ny], cost));
					}
					break;
				}

				cost+=1;
			}
		}

	}


	public static void makeGroup(Point start, boolean[][] visited, int groupNumber) {
		Queue<Point> q = new LinkedList<>();
		graph[start.x][start.y] = groupNumber;
		visited[start.x][start.y] = true;
		q.add(start);

		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || graph[nx][ny] == 0 || visited[nx][ny]) {
					continue;
				}

				visited[nx][ny] = true;
				graph[nx][ny] = groupNumber;
				q.add(new Point(nx, ny));
			}


		}

	}
}

class Bridge {

	int fromIsland;
	int toIsland;
	int cost;

	Bridge(int fromIsland, int toIsland, int cost) {
		this.fromIsland = fromIsland;
		this.toIsland = toIsland;
		this.cost = cost;
	}
}

class Point {

	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;

	}
}