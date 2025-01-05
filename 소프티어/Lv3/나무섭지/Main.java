import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int M, N;
	static char[][] graph;
	static int[] dx = {0, 0, -1, 1}; // 상, 하, 좌, 우
	static int[] dy = {1, -1, 0, 0};

	static List<Location> ghosts;
	static Location namu;
	static Location exit;

	static boolean result;

	static int[][] ghostMoveTimes;

	public static void main(String[] args) throws IOException {
		init();
		if (result) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		graph = new char[M][N];
		ghostMoveTimes = new int[M][N];

		ghosts = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				graph[i][j] = str.charAt(j);
				ghostMoveTimes[i][j] = -1; // 초기값 설정

				if (graph[i][j] == 'D') {
					exit = new Location(i, j, 0);
				} else if (graph[i][j] == 'N') {
					namu = new Location(i, j, 0);
				} else if (graph[i][j] == 'G') {
					ghosts.add(new Location(i, j, 0));
				}
			}
		}

		setGhosts(); // 유령의 이동 시간을 설정
		result = simulate(); // 남우의 탈출 가능 여부를 시뮬레이션
	}

	private static void setGhosts() {
		Queue<Location> q = new LinkedList<>(ghosts);

		for (Location ghost : ghosts) {
			ghostMoveTimes[ghost.x][ghost.y] = 0; // 유령의 초기 위치 설정
		}

		while (!q.isEmpty()) {
			Location now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = dx[i] + now.x;
				int ny = dy[i] + now.y;

				if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
				if (ghostMoveTimes[nx][ny] != -1) continue;

				ghostMoveTimes[nx][ny] = now.moveCount + 1;
				q.add(new Location(nx, ny, now.moveCount + 1));
			}
		}
	}

	private static boolean simulate() {
		Queue<Location> q = new LinkedList<>();
		boolean[][] visited = new boolean[M][N];
		visited[namu.x][namu.y] = true;
		q.add(namu);

		while (!q.isEmpty()) {
			Location now = q.poll();

			if (now.x == exit.x && now.y == exit.y) {
				return true; // 출구 도달
			}

			for (int i = 0; i < 4; i++) {
				int nx = dx[i] + now.x;
				int ny = dy[i] + now.y;

				if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
				if (graph[nx][ny] == '#') continue; // 벽
				if (visited[nx][ny]) continue;

				int nextMoveTime = now.moveCount + 1;
				if (ghostMoveTimes[nx][ny] <= nextMoveTime) continue;

				visited[nx][ny] = true;
				q.add(new Location(nx, ny, nextMoveTime));
			}
		}

		return false; // 탈출 불가능
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
