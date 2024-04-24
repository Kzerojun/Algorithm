import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	static int[][] graph;
	static int N;
	static int M;
	static int K;

	static int[][] rotations;
	static int solution = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		init();
		permuteRotations(new int[K], new boolean[K], 0);
		System.out.println(solution);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		graph = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		rotations = new int[K][3];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			rotations[i][0] = Integer.parseInt(st.nextToken()) - 1;
			rotations[i][1] = Integer.parseInt(st.nextToken()) - 1;
			rotations[i][2] = Integer.parseInt(st.nextToken());
		}
	}

	static void permuteRotations(int[] order, boolean[] visited, int depth) {
		if (depth == K) {
			int[][] arr = new int[N][M];
			copyGraph(arr);
			for (int index : order) {
				rotate(rotations[index][0], rotations[index][1], rotations[index][2], arr);
			}

			for (int i = 0; i < N; i++) {
				int tmpSol = 0;
				for (int j = 0; j < M; j++) {
					tmpSol += arr[i][j];
				}
				solution = Math.min(tmpSol, solution);
			}
		}

		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				order[depth] = i;
				permuteRotations(order, visited, depth + 1);
				visited[i] = false;
			}
		}
	}

	static void rotate(int r, int c, int s, int[][] arr) {
		int x1 = r - s;
		int y1 = c - s;
		int x2 = r + s;
		int y2 = c + s;

		int[][] tmp = new int[N][M];
		while (s-- > 0) {
			for (int i = y1; i < y2; i++) {
				tmp[x1][i + 1] = arr[x1][i];
			}
			for (int i = x1; i < x2; i++) {
				tmp[i + 1][y2] = arr[i][y2];
			}

			for (int i = y2; i > y1; i--) {
				tmp[x2][i - 1] = arr[x2][i];
			}

			for (int i = x2; i > x1; i--) {
				tmp[i - 1][y1] = arr[i][y1];
			}
			x1++;
			y1++;
			x2--;
			y2--;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmp[i][j] != 0) {
					arr[i][j] = tmp[i][j];
				}
			}
		}
	}

	static void copyGraph(int[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = graph[i][j];
			}
		}
	}


}