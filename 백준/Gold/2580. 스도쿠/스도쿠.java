import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	static boolean isClear;
	static int[][] graph;
	static List<Location> locations;

	static int[][] result;

	public static void main(String[] args) throws IOException {
		init();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		locations = new ArrayList<>();

		graph = new int[9][9];

		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j] == 0) {
					locations.add(new Location(i, j));
				}
			}
		}

		result = new int[9][9];
		isClear = false;

		dfs(0);
	}

	private static void dfs(int index) {

		if (isClear) {
			return;
		}

		if (index == locations.size()) {
			printResult();
			isClear = true;
			return;
		}

		Location now = locations.get(index);
		List<Integer> canPossibleNumbers = findPossibleNumber(now.x, now.y);

		for (int number : canPossibleNumbers) {
			graph[now.x][now.y] = number;
			dfs(index + 1);
			graph[now.x][now.y] = 0;
		}

	}

	private static void printResult() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
	}


	private static List<Integer> findPossibleNumber(int row, int col) {
		HashSet<Integer> set = new HashSet<>();

		for (int i = 0; i < 9; i++) {
			set.add(graph[i][col]);
		}

		for (int j = 0; j < 9; j++) {
			set.add(graph[row][j]);
		}

		row = row / 3 * 3;
		col = col / 3 * 3;

		for (int i = row; i < row + 3; i++) {
			for (int j = col; j < col + 3; j++) {
				set.add(graph[i][j]);
			}
		}

		List<Integer> possibleNumbers = new ArrayList<>();
		for (int i = 1; i <= 9; i++) {
			if (!set.contains(i)) {
				possibleNumbers.add(i);
			}
		}

		return possibleNumbers;
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