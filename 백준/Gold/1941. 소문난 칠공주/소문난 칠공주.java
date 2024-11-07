import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {

	static char[][] graph;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};

	static int sol;

	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		graph = new char[5][5];

		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 5; j++) {
				graph[i][j] = str.charAt(j);
			}
		}

		sol = 0;

		makeCombination(new boolean[5][5], new ArrayList<>(), 0);

		System.out.println(sol);

	}

	private static void makeCombination(boolean[][] visited, List<Location> combinations,
			int index) {
		if (combinations.size() == 7) {
			boolean result = isPossible(combinations);

			if (result) {
				sol++;
			}

			return;
		}

		for (int i = index; i < 25; i++) {
			int x = i / 5;
			int y = i % 5;

			if (!visited[x][y]) {
				visited[x][y] = true;
				combinations.add(new Location(x, y));
				makeCombination(visited, combinations, i + 1);
				visited[x][y] = false;
				combinations.remove(combinations.size() - 1);
			}
		}
	}

	private static boolean isPossible(List<Location> locations) {
		int SCount = 0;
		boolean[][] canMove = new boolean[5][5];
		boolean[][] visited = new boolean[5][5];

		for (Location loc : locations) {
			canMove[loc.x][loc.y] = true;
			if (graph[loc.x][loc.y] == 'S') {
				SCount++;
			}
		}

		if (SCount < 4) {
			return false;
		}

		Queue<Location> q = new LinkedList<>();
		Location start = locations.get(0);
		q.add(start);
		visited[start.x][start.y] = true;

		int moveCount = 0;

		while (!q.isEmpty()) {
			Location now = q.poll();
			moveCount++;

			for (int i = 0; i < 4; i++) {
				int nx = dx[i] + now.x;
				int ny = dy[i] + now.y;

				if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) {
					continue;
				}

				if (canMove[nx][ny] && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new Location(nx, ny));

				}
			}
		}

		return moveCount == 7;
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