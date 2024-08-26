import java.util.*;
import java.io.*;

public class Solution {

	static BufferedReader br;
	static int T;
	static int N;
	static int[][] graph;
	// 인접한 8방향
	static int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static boolean[][] visited;
	static int result;
	static List<Location> zeroMines;

	public static void main(String[] args) throws IOException {
		init();
		start();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
	}

	private static void start() throws IOException {
		for (int t = 1; t <= T; t++) {
			initEachCase();
			simulate();
			System.out.println("#" + t + " " + result);
		}
	}

	// 각 테스트 정보 입력하기
	private static void initEachCase() throws IOException {
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				char ch = str.charAt(j);
				if (ch == '.') {
					graph[i][j] = -1;
				} else if (ch == '*') {
					graph[i][j] = -2;
				}
			}
		}

		result = 0;
		visited = new boolean[N][N];
		zeroMines = new ArrayList<>();
	}

	private static void simulate() {
		findZeroCell();
		for (Location location : zeroMines) {
			if(!visited[location.x][location.y]) {
				clickCell(location);
				result++;
			}

		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (graph[i][j] == -1 && !visited[i][j]) {
					clickCell(new Location(i, j));
					result++;
				}
			}
		}
	}

	private static void findZeroCell() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (graph[i][j] == -1) {
					calculate(i, j);
				}

			}
		}
	}

	private static void calculate(int x, int y) {

		int mineCount = 0;

		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
				if (graph[nx][ny] == -2) {
					mineCount++;
				}
			}
		}

		if (mineCount == 0) {
			zeroMines.add(new Location(x, y));
		}
	}

	private static void clickCell(Location location) {
		Queue<Location> q = new LinkedList<>();
		q.add(location);
		visited[location.x][location.y] = true;

		while (!q.isEmpty()) {
			Location now = q.poll();
			int mineCount = 0;

			for (int i = 0; i < 8; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
					if (graph[nx][ny] == -2) {
						mineCount++;
					}
				}
			}

			graph[now.x][now.y] = mineCount;

			if (mineCount == 0) {
				for (int i = 0; i < 8; i++) {
					int nx = now.x + dx[i];
					int ny = now.y + dy[i];

					if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny] && graph[nx][ny] == -1) {
						visited[nx][ny] = true;
						q.add(new Location(nx, ny));
					}
				}
			}
		}
	}

	static class Location {
		int x;
		int y;

		Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
