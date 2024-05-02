import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	static int N, M, K;
	static int[][] graph;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		init();
		move();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		graph = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				graph[i][j] = line.charAt(j) - '0';
			}
		}
	}

	private static void move() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[N][M][K+1];
		q.add(new Node(0, 0, K, 1));
		visited[0][0][K] = true;

		while (!q.isEmpty()) {
			Node now = q.poll();

			if (now.x == N - 1 && now.y == M - 1) {
				System.out.println(now.moveCount);
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}

				if (graph[nx][ny] == 1 && now.canMoveCount > 0 && !visited[nx][ny][now.canMoveCount - 1]) {
					visited[nx][ny][now.canMoveCount - 1] = true;
					q.add(new Node(nx, ny, now.canMoveCount - 1, now.moveCount + 1));
				}

				if (graph[nx][ny] == 0 && !visited[nx][ny][now.canMoveCount]) {
					visited[nx][ny][now.canMoveCount] = true;
					q.add(new Node(nx, ny, now.canMoveCount, now.moveCount + 1));
				}
			}
		}

		System.out.println(-1);
	}

}

class Node {

	int x;
	int y;
	int canMoveCount;
	int moveCount;

	Node(int x, int y, int canMoveCount, int moveCount) {
		this.x = x;
		this.y = y;
		this.canMoveCount = canMoveCount;
		this.moveCount = moveCount;
	}
}