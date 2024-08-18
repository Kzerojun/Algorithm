import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	static BufferedReader br;
	static int N, M,K;
	static int[][] graph;

	// 상, 하, 좌, 우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(move());
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		graph = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				graph[i][j] = str.charAt(j) - '0';
			}
		}
	}

	private static int move() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0, 1, 0));

		boolean[][][] visited = new boolean[N][M][K+1];
		visited[0][0][0] = true;

		while (!q.isEmpty()) {
			Point now = q.poll();

			if (now.x == N - 1 && now.y == M - 1) {
				return now.moveCount;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

				if (graph[nx][ny] == 1 && now.destory < K && !visited[nx][ny][now.destory+1]) {
					visited[nx][ny][now.destory+1] = true;
					q.add(new Point(nx, ny, now.moveCount + 1, now.destory+1));
				}

				if (graph[nx][ny] == 0 && !visited[nx][ny][now.destory]) {
					visited[nx][ny][now.destory] = true;
					q.add(new Point(nx, ny, now.moveCount + 1, now.destory));
				}
			}
		}

		return -1;
	}
}

class Point {
	int x, y;
	int moveCount, destory;

	Point(int x, int y, int moveCount, int destory) {
		this.x = x;
		this.y = y;
		this.moveCount = moveCount;
		this.destory = destory;
	}
}
