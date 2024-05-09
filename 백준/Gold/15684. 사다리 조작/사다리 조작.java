import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	//N개의 세로선과 M개의 가로선
	//인접 세로선마다 가로선 놓을수 있다.
	//놓을수 있는 위치의 개수 H개
	static int N, M, H;
	static int[][] graph;
	static int answer;

	static boolean finish = false;

	public static void main(String[] args) throws IOException {
		init();
		start();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		graph = new int[H+1][N+1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a][b] = 1;
			graph[a][b+1] = 2;
		}
	}

	private static void start() {
		for (int i = 0; i <= 3; i++) {
			answer = i;
			dfs(1, 0);
			if (finish) {
				break;
			}
		}

		if (finish) {
			System.out.println(answer);
		} else {
			System.out.println(-1);
		}
	}

	private static void dfs(int x, int count) {
		if (finish) {
			return;
		}
		if (answer == count) {
			if(check()){ finish = true;}
			return;
		}

		for (int i = x; i < H + 1; i++) {
			for (int j = 1; j < N; j++) {
				if (graph[i][j] == 0 && graph[i][j + 1] == 0) {
					graph[i][j] = 1;
					graph[i][j+1] = 2;
					dfs(i, count + 1);
					graph[i][j] = 0;
					graph[i][j+1] = 0;
				}
			}
		}
	}

	private static boolean check() {
		for (int i = 1; i <= N; i++) {
			int x = 1;
			int y = i;
			for (int j = 0; j < H; j++) {
				if (graph[x][y] == 1) {
					y++;
				}
				else if (graph[x][y] == 2) {
					y--;
				}
				x++;
			}
			if (y != i) {
				return false;
			}
		}
		return true;
	}
}