import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

	static int N;
	static int M;

	static int[][] graph;

	static List<Point> wind;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		wind = new ArrayList<>();
		wind.add(new Point(N - 2, 0));
		wind.add(new Point(N - 2, 1));
		wind.add(new Point(N - 1, 0));
		wind.add(new Point(N - 1, 1));

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			windMove(wind, command - 1, count);
		}

		int sol = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sol += graph[i][j];
			}
		}

		System.out.println(sol);


	}

	static void windMove(List<Point> wind, int command, int count) {
		Queue<Point> q = new LinkedList<>(wind);
		List<Point> increasePoint = new LinkedList<>();

		while (!q.isEmpty()) {
			Point now = q.poll();

			int nx = now.x;
			int ny = now.y;
			int moveCount = 0 ;
			while (moveCount++ < count) {
				nx = (nx + dx[command] + N) % N;
				ny = (ny + dy[command] + N) % N;
			}

			increasePoint.add(new Point(nx, ny));
			graph[nx][ny]++;
		}

		copyWater(increasePoint);


	}

	static void copyWater(List<Point> increasePoint) {
		Queue<Point> q = new LinkedList<>(increasePoint);

		while (!q.isEmpty()) {
			Point now = q.poll();
			for (int i = 1; i < 8; i += 2) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || graph[nx][ny]==0) {
					continue;
				}

				graph[now.x][now.y]++;
			}
		}
//
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(graph[i][j] + " ");
//			}
//			System.out.println();
//		}
//
//		System.out.println();
		makeWind(increasePoint);

	}

	static void makeWind(List<Point> increasePoint) {
		boolean[][] visited = new boolean[N][N];

		for (Point point : increasePoint) {
			visited[point.x][point.y] = true;
		}

		List<Point> renewWind = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (graph[i][j] >= 2 && !visited[i][j]) {
					graph[i][j] -= 2;
					renewWind.add(new Point(i, j));
				}
			}
		}
		wind = renewWind;
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

