import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	static int N, M;
	static int[][] graph;
	static int result;

	static boolean[][][] visited;

	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};

	//동 서 남 북

	static int startX, startY, startDirection;
	static int goalX, goalY, goalDirection;

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
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[N][M][4];

		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken()) - 1;
		startY = Integer.parseInt(st.nextToken()) - 1;
		startDirection = Integer.parseInt(st.nextToken()) - 1;

		st = new StringTokenizer(br.readLine());
		goalX = Integer.parseInt(st.nextToken()) - 1;
		goalY = Integer.parseInt(st.nextToken()) - 1;
		goalDirection = Integer.parseInt(st.nextToken()) - 1;

		result = Integer.MAX_VALUE;

		move();

		System.out.println(result);

	}

	private static void move() {
		Robot robot = new Robot(startX, startY, startDirection, 0);
		Queue<Robot> q = new LinkedList<>();
		q.add(robot);
		visited[robot.x][robot.y][robot.direction] = true;

		while (!q.isEmpty()) {
			Robot now = q.poll();

			if (now.x == goalX && now.y == goalY) {
				if (now.direction == goalDirection) {
					result = Math.min(result, now.moveCount);
				}
			}


			//우회전
			int nextDirection;
			if (now.direction == 0) {
				nextDirection = 2;
			} else if (now.direction == 1) {
				nextDirection = 3;
			} else if (now.direction == 2) {
				nextDirection = 1;
			}else {
				nextDirection = 0;
			}

			if (!visited[now.x][now.y][nextDirection]) {
				visited[now.x][now.y][nextDirection] = true;
				q.add(new Robot(now.x, now.y, nextDirection, now.moveCount + 1));
			}


			//좌회전
			if (now.direction == 0) {
				nextDirection = 3;
			} else if (now.direction == 1) {
				nextDirection = 2;
			} else if (now.direction == 2) {
				nextDirection = 0;
			}else {
				nextDirection = 1;
			}

			if (!visited[now.x][now.y][nextDirection]) {
				visited[now.x][now.y][nextDirection] = true;
				q.add(new Robot(now.x, now.y, nextDirection, now.moveCount + 1));
			}

			for (int k = 1; k <= 3; k++) {
				int nx = now.x + dx[now.direction] * k;
				int ny = now.y + dy[now.direction] * k;

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || graph[nx][ny] == 1) {
					break;
				}

				if (visited[nx][ny][now.direction]) {
					continue;
				}

				visited[nx][ny][now.direction] = true;
				q.add(new Robot(nx, ny, now.direction, now.moveCount + 1));
			}
		}


	}


}

class Robot {

	int x;
	int y;
	int direction;
	int moveCount;

	Robot(int x, int y, int direction, int moveCount) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.moveCount = moveCount;
	}
}