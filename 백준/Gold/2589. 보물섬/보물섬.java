import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	static int N, M;
	static int[][] graph;
	static boolean[][][][] visited;

	static int result;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				char ch = str.charAt(j);
				if (ch == 'W') {
					graph[i][j] = 1;
				}
			}
		}

		result = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (graph[i][j] == 0) {
					int sol = move(i, j);
					result = Math.max(result, sol);
				}


			}
		}

		System.out.println(result);
	}

	private static int move(int x, int y) {
		Queue<Location> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];

		q.add(new Location(x, y, 0));
		visited[x][y] = true;
		int sol = Integer.MIN_VALUE;

		while (!q.isEmpty()) {
			Location now = q.poll();

			sol = Math.max(now.moveCount, sol);

			for (int i = 0; i < 4; i++) {
				int nx = dx[i] + now.x;
				int ny = dy[i] + now.y;

				if(nx < 0 || ny<0 || nx>=N || ny>=M || visited[nx][ny]) continue;
				if(graph[nx][ny]==1) continue;

				visited[nx][ny] = true;
				q.add(new Location(nx, ny, now.moveCount + 1));
			}
		}

		return sol;
	}
}

class Location {

	int x;
	int y;
	int moveCount;

	Location(int x, int y, int moveCount) {
		this.x = x;
		this.y = y;
		this.moveCount = moveCount;
	}
}