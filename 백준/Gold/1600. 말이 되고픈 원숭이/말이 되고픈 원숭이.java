import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	static int K;
	static int W, H;
	static int[][] graph;

	static int[] dx = {0, -1, 1, 0};
	static int[] dy = {1, 0, 0, -1};

	static int[] ddx = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] ddy = {-2, -1, 1, 2, 2, 1, -1, -2};

	public static void main(String[] args) throws IOException {
		init();
		move();
	}

	static void move() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0, K));
		boolean[][][] visited = new boolean[W][H][K+1];

		while (!q.isEmpty()) {
			Node now = q.poll();

			if (now.x == W - 1 && now.y == H - 1) {
				System.out.println(now.moveCount);
				return;
			}

			if (now.canMoveCount > 0) {
				for (int i = 0; i < 8; i++) {
					int nx = ddx[i] + now.x;
					int ny = ddy[i] + now.y;

					if (nx >= 0 && ny >= 0 && nx < W && ny < H && graph[nx][ny] == 0 && !visited[nx][ny][now.canMoveCount-1]) {
						visited[nx][ny][now.canMoveCount-1] = true;
						q.add(new Node(nx, ny, now.moveCount + 1, now.canMoveCount-1));
					}
				}
			}

			for (int i = 0; i < 4; i++) {
				int nx = dx[i] + now.x;
				int ny = dy[i] + now.y;

				if (nx >= 0 && ny >= 0 && nx < W && ny < H && graph[nx][ny] == 0 && !visited[nx][ny][now.canMoveCount]) {
					visited[nx][ny][now.canMoveCount] = true;
					q.add(new Node(nx, ny, now.moveCount + 1, now.canMoveCount));
				}
			}
		}

		System.out.println(-1);
	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		graph = new int[W][H];

		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < H; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

	}
}

class Node {

	int x;
	int y;
	int moveCount;

	int canMoveCount;

	Node(int x, int y, int moveCount, int canMoveCount) {
		this.x = x;
		this.y = y;
		this.moveCount = moveCount;
		this.canMoveCount = canMoveCount;
	}
}