import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{

	static int N,M;
	static int[][] graph;

	static List<Location> cheeses;
	static int time;
	static int before;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};


	public static void main(String[] args) throws IOException {
		init();

	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		cheeses = new ArrayList<>();
		graph = new int[N][M];

		before = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());

				if (graph[i][j] == 1) {
					before++;
				}
			}
		}

		time = 0;



		while (!isFinish()) {
			time ++;
			simulate();
		}

		System.out.println(time);
		System.out.println(before);

	}

	private static void simulate() {
		int[][] cloneGraph = graph.clone();

		Queue<Location> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		visited[0][0] = true;
		q.add(new Location(0, 0));

		while (!q.isEmpty()) {
			Location now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = dx[i] + now.x;
				int ny = dy[i] + now.y;

				if(nx < 0 || ny<0 || nx>=N || ny>=M) continue;
				if(visited[nx][ny]) continue;

				if (graph[nx][ny] == 1) {
					cloneGraph[nx][ny] = 0;
					visited[nx][ny] = true;
				}else {
					visited[nx][ny] = true;
					q.add(new Location(nx, ny));
				}


			}
		}

		graph = cloneGraph;


	}

	private static boolean isFinish() {

		boolean check = true;
		int size = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (graph[i][j] == 1) {
					check = false;
					size++;
				}
			}
		}
		if (!check) {
			before = size;
		}


		return check;
	}
}

class Location{

	int x;
	int y;

	Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
