import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int R, C;
	static char[][] map;
	static int[][] playerTime;
	static int[][] fireTime;
	static int pX, pY;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		init();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		playerTime = new int[R][C];
		fireTime = new int[R][C];

		Queue<Location> fireQueue = new LinkedList<>();

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				playerTime[i][j] = -1;
				fireTime[i][j] = -1;

				if (map[i][j] == 'J') {
					pX = i;
					pY = j;
				}

				if (map[i][j] == 'F') {
					fireQueue.add(new Location(i, j, 1));
					fireTime[i][j] = 1;
				}
			}
		}

		simulateFire(fireQueue);

		simulatePlayer();

		int result = Integer.MAX_VALUE;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if ((i == 0 || j == 0 || i == R - 1 || j == C - 1) && playerTime[i][j] != -1) {
					if (fireTime[i][j]==-1 || playerTime[i][j] < fireTime[i][j]) {
						result = Math.min(result, playerTime[i][j]);
					}
				}
			}
		}

		if (result == Integer.MAX_VALUE) {
			System.out.println("IMPOSSIBLE");
		} else {
			System.out.println(result);
		}
	}

	private static void simulateFire(Queue<Location> q) {
		while (!q.isEmpty()) {
			Location now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
					continue;
				}

				if (map[nx][ny] == '#') {
					continue;
				}

				if (fireTime[nx][ny] != -1) {
					continue;
				}

				if (fireTime[nx][ny] > now.moveCount + 1) {
					continue;
				}

				fireTime[nx][ny] = now.moveCount + 1;
				q.add(new Location(nx, ny, now.moveCount + 1));
			}
		}
	}

	private static void simulatePlayer() {
		Queue<Location> q = new LinkedList<>();
		q.add(new Location(pX, pY, 1));
		playerTime[pX][pY] = 1;

		while (!q.isEmpty()) {
			Location now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
					continue;
				}

				if (map[nx][ny] == '#') {
					continue;
				}

				if (playerTime[nx][ny] != -1) {
					continue;
				}

				playerTime[nx][ny] = now.moveCount + 1;
				q.add(new Location(nx, ny, now.moveCount + 1));
			}
		}
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