import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	static int N;
	static boolean[][] visited;

	static boolean result;

	static String[][] graph;
	static List<Location> teachers;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};


	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		graph = new String[N][N];
		visited = new boolean[N][N];
		teachers = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				String str = st.nextToken();
				graph[i][j] = str;

				if (str.equals("T")) {
					teachers.add(new Location(i, j));
				}
			}
		}

		result = false;
		makeWall(0,new ArrayList<>());

		if (result) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}


	}

	private static void makeWall(int index, List<Location> walls) {
		if (walls.size() == 3) {
			String[][] tmp = new String[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					tmp[i][j] = graph[i][j];
				}
			}

			for (Location location : walls) {
				tmp[location.x][location.y] = "O";
			}

			if (isPossible(tmp)) {
				result = true;
			}
			return;
		}

		for (int i = index; i < N * N; i++) {
			int row = i / N;
			int col = i % N;

			if (!visited[row][col] && graph[row][col].equals("X")) {
				visited[row][col] = true;
				walls.add(new Location(row, col));
				makeWall(i + 1, walls);
				visited[row][col] = false;
				walls.remove(walls.size() - 1);
			}
		}
	}



	private static boolean isPossible(String[][] tmp) {

		for (Location teacher : teachers) {
			for (int i = 0; i < 4; i++) {
				int nx = teacher.x;
				int ny = teacher.y;

				while (true) {
					nx = dx[i] + nx;
					ny = dy[i] + ny;

					if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
						break;
					}

					if (tmp[nx][ny].equals("O")) {
						break;
					}

					if (tmp[nx][ny].equals("S")) {
						return false;
					}


				}
			}
		}

		return true;
	}


}

class Location {
	int x;
	int y;

	Location(int x, int y) {
		this.x =x;
		this.y = y;
	}
}