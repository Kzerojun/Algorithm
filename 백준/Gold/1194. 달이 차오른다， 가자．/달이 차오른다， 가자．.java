import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	static char[][] graph;

	static int N, M;

	// 행 열 열쇠-> 비트마스킹 6자리 abcdef
	static boolean[][][] visited;

	static Node start;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int result;

	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new char[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				char ch = str.charAt(j);
				graph[i][j] = ch;

				if (ch == '0') {
					start = new Node(i, j, 0, 0);
				}

			}
		}

		visited = new boolean[N][M][1 << 6];
		result = Integer.MAX_VALUE;

		move();

		System.out.println(result != Integer.MAX_VALUE ? result : -1);
	}

	private static void move() {
		Queue<Node> q = new LinkedList<>();
		q.add(start);
		visited[start.x][start.y][start.bitmask] = true;

		while (!q.isEmpty()) {
			Node now = q.poll();

			if (graph[now.x][now.y] == '1') {
				result = Math.min(result, now.moveCount);
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nx = dx[i] + now.x;
				int ny = dy[i] + now.y;

				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				if (graph[nx][ny] == '#') {
					continue;
				}

				if (Character.isLowerCase(graph[nx][ny])) {
					int nextBitmask = now.bitmask | (1 << (graph[nx][ny] - 'a'));
					if (!visited[nx][ny][nextBitmask]) {
						visited[nx][ny][nextBitmask] = true;
						q.add(new Node(nx, ny, nextBitmask, now.moveCount + 1));
					}
				}

				if (Character.isUpperCase(graph[nx][ny])) {
					if ((now.bitmask & (1 << (graph[nx][ny] - 'A'))) == 0) {
						continue;
					}
				}

				if (!visited[nx][ny][now.bitmask]) {
					visited[nx][ny][now.bitmask] = true;
					q.add(new Node(nx, ny, now.bitmask, now.moveCount + 1));
				}
			}
		}
	}


}

class Node {

	int x;
	int y;
	int bitmask;

	int moveCount;

	Node(int x, int y, int bitmask, int moveCount) {
		this.x = x;
		this.y = y;
		this.bitmask = bitmask;
		this.moveCount = moveCount;
	}
}