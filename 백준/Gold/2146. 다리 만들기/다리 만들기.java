import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	static int N;
	static int[][] graph;
	static int[][] group;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	static int[][] check;

	static int minLength = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		init();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		graph = new int[N][N];
		group = new int[N][N];
		check = new int[N][N];


		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				check[i][j] = Integer.MAX_VALUE;
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[][] visited = new boolean[N][N];
		int groupNumber = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (graph[i][j] == 1 && !visited[i][j]) {
					makeGroupNumber(i, j, visited, groupNumber++);
				}
			}
		}


		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (group[i][j] != 0) {
					makeBridge(i, j);
				}
			}
		}

		System.out.println(minLength);
	}

	private static void makeGroupNumber(int x, int y, boolean[][] visited, int groupNumber) {
		Queue<Location> q = new LinkedList<>();
		q.add(new Location(x, y));
		visited[x][y] = true;
		group[x][y] = groupNumber;

		while (!q.isEmpty()) {
			Location now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}

				if (visited[nx][ny]) {
					continue;
				}

				if (graph[nx][ny] != 1) {
					continue;
				}

				visited[nx][ny] = true;
				group[nx][ny] = groupNumber;
				q.add(new Location(nx, ny));
			}
		}
	}

	private static void makeBridge(int x, int y) {
		Queue<Result> q = new LinkedList<>();
		q.add(new Result(x, y, 0));
		check[x][y] = 0;

		while (!q.isEmpty()) {
			Result now = q.poll();

			if (check[now.x][now.y] < now.moveCount) {
				continue;
			}

			if (group[now.x][now.y] != group[x][y] && group[now.x][now.y] != 0) {
				minLength = Math.min(minLength, now.moveCount - 1);
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}

				if (check[nx][ny] <= now.moveCount + 1) {
					continue;
				}

				if (group[nx][ny] == group[x][y]) {
					continue;
				}

				check[nx][ny] = now.moveCount+1;
				q.add(new Result(nx, ny, now.moveCount + 1));
			}
		}
	}


}

class Result extends Location {

	int moveCount;

	Result(int x, int y, int moveCount) {
		super(x, y);
		this.moveCount = moveCount;
	}
}

class Location {

	int x;
	int y;

	Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
}